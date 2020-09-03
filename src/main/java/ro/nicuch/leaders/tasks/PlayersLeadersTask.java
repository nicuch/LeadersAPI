package ro.nicuch.leaders.tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ro.nicuch.leaders.api.LeadersTask;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.api.TaskDescription;
import ro.nicuch.leaders.data.PlayerRankData;
import ro.nicuch.leaders.enums.TaskType;
import ro.nicuch.leaders.utils.SortingUtil;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class PlayersLeadersTask implements LeadersTask {
    private final TaskDescription taskDescription;
    private final Map<Integer, PlayerRankData> rankData = new TreeMap<>();
    private final Map<String, Integer> onlineData = new TreeMap<>();
    private final ReentrantLock taskLock = new ReentrantLock();

    public PlayersLeadersTask(TaskDescription taskDescription) {
        if (taskDescription.getTaskType() != TaskType.PLAYERS_SORT)
            throw new IllegalArgumentException("Illegal task type for task description!");
        this.taskDescription = taskDescription;
    }

    @Override
    public @NotNull TaskDescription getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public @NotNull PlayerRankData getRankData(int rank) {
        this.taskLock.lock();
        try {
            if (!this.rankData.containsKey(rank))
                return new PlayerRankData(this.taskDescription.getNoDisplayName(), this.taskDescription.getNoDisplayValue(), this.taskDescription.getNoDisplayValue(), null);
            return this.rankData.get(rank);
        } finally {
            this.taskLock.unlock();
        }
    }

    public int getPlayerRank(Player player) {
        this.taskLock.lock();
        try {
            if (!this.onlineData.containsKey(player.getName()))
                return 0;
            return this.onlineData.get(player.getName());
        } finally {
            this.taskLock.unlock();
        }
    }

    @Override
    public @NotNull Map<Integer, RankData> getRanksData() {
        this.taskLock.lock();
        try {
            return Collections.unmodifiableMap(this.rankData);
        } finally {
            this.taskLock.unlock();
        }
    }

    @Override
    public void run() {
        Set<String> excludedPlayers = this.taskDescription.getExcludedPlayers();
        String displayNamePlaceholder = ChatColor.translateAlternateColorCodes('&', this.taskDescription.getDisplayname());
        String displayValuePlaceholder = ChatColor.translateAlternateColorCodes('&', this.taskDescription.getDisplayValue());
        String placeholder = ChatColor.stripColor(this.taskDescription.getPlaceholder());
        LinkedList<RankData> unsortedList = new LinkedList<>();
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName() == null)
                continue;
            if (excludedPlayers.contains(player.getName()))
                continue; // exclude
            if (!this.taskDescription.getTaskRequirements().checkRequirements(player))
                continue;
            String displayName = PlaceholderAPI.setPlaceholders(player, displayNamePlaceholder);
            String displayValue = PlaceholderAPI.setPlaceholders(player, displayValuePlaceholder);
            PlayerRankData playerRankData = new PlayerRankData(displayName, displayValue, PlaceholderAPI.setPlaceholders(player, placeholder), player);
            unsortedList.add(playerRankData);
        }
        this.taskLock.lock();
        try {
            int count = 0;
            int cacheSize = this.taskDescription.getCacheSize();
            this.rankData.clear();
            this.onlineData.clear();
            for (RankData sortedRankData : SortingUtil.sortPlayerData(this.taskDescription, unsortedList)) {
                if (!(sortedRankData instanceof PlayerRankData))
                    continue;
                PlayerRankData playerRankData = (PlayerRankData) sortedRankData;
                count++;
                if (playerRankData.getPlayer().isOnline())
                    this.onlineData.put(playerRankData.getPlayer().getName(), count);
                if (count >= cacheSize)
                    continue;
                this.rankData.put(count, playerRankData);
            }
        } finally {
            this.taskLock.unlock();
        }
        unsortedList.clear();
    }
}
