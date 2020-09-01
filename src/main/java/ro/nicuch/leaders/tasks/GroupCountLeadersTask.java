package ro.nicuch.leaders.tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ro.nicuch.leaders.api.LeadersTask;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.api.TaskDescription;
import ro.nicuch.leaders.data.GroupRankData;
import ro.nicuch.leaders.utils.IncrementalInteger;
import ro.nicuch.leaders.utils.SortingUtil;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class GroupCountLeadersTask implements LeadersTask {
    private final TaskDescription taskDescription;
    private final Map<Integer, GroupRankData> rankData = new TreeMap<>();
    private final Map<String, Integer> onlineData = new TreeMap<>();
    private final ReentrantLock taskLock = new ReentrantLock();

    public GroupCountLeadersTask(TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public TaskDescription getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public GroupRankData getRankData(int rank) {
        this.taskLock.lock();
        try {
            if (!this.rankData.containsKey(rank))
                return new GroupRankData(this.taskDescription.getNoDisplayName(), this.taskDescription.getNoDisplayValue(), this.taskDescription.getNoDisplayValue(), null);
            return this.rankData.get(rank);
        } finally {
            this.taskLock.unlock();
        }
    }

    public int getPlayerGroupRank(Player player) {
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
    public Map<Integer, RankData> getRanksData() {
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
        String rationalPlaceholder = ChatColor.translateAlternateColorCodes('&', this.taskDescription.getRationalPlaceholder());
        String rationalRequirement = ChatColor.translateAlternateColorCodes('&', this.taskDescription.getRationalRequirement());

        Map<String, IncrementalInteger> incementalMap = new HashMap<>();

        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName() == null)
                continue;
            if (excludedPlayers.contains(player.getName()))
                continue; // exclude
            if (this.taskDescription.getTaskType().isRational())
                if (!PlaceholderAPI.setPlaceholders(player, rationalPlaceholder).equals(PlaceholderAPI.setPlaceholders(player, rationalRequirement)))
                    continue;
            String placeholderGroup = PlaceholderAPI.setPlaceholders(player, placeholder);
            if (incementalMap.containsKey(placeholderGroup))
                incementalMap.get(placeholderGroup).increment(1);
            else
                incementalMap.put(placeholderGroup, new IncrementalInteger(1));
        }

        LinkedList<RankData> unsortedList = new LinkedList<>();
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName() == null)
                continue;
            if (excludedPlayers.contains(player.getName()))
                continue; // exclude
            if (this.taskDescription.getTaskType().isRational())
                if (!PlaceholderAPI.setPlaceholders(player, this.taskDescription.getRationalPlaceholder()).equals(this.taskDescription.getRationalRequirement()))
                    continue;
            String placeholderGroup = PlaceholderAPI.setPlaceholders(player, placeholder);
            int count = incementalMap.get(placeholderGroup).get();
            String displayName = PlaceholderAPI.setPlaceholders(player, displayNamePlaceholder);
            String displayValue = PlaceholderAPI.setPlaceholders(player, displayValuePlaceholder.replace("%count%", "" + count));
            GroupRankData playerRankData = new GroupRankData(displayName, displayValue, "" + count, player);
            unsortedList.add(playerRankData);
        }
        this.taskLock.lock();
        try {
            int count = 0;
            int cacheSize = this.taskDescription.getCacheSize();
            this.rankData.clear();
            this.onlineData.clear();
            List<RankData> rankedList = new ArrayList<>();
            for (RankData sortedRankData : SortingUtil.sortPlayerData(this.taskDescription, unsortedList)) {
                if (!(sortedRankData instanceof GroupRankData))
                    continue;
                GroupRankData groupRankData = (GroupRankData) sortedRankData;
                if (!rankedList.contains(sortedRankData)) {
                    rankedList.add(sortedRankData);
                    count++;
                }
                if (groupRankData.getPlayer().isOnline())
                    this.onlineData.put(groupRankData.getPlayer().getName(), count);
                if (count >= cacheSize)
                    continue;
                this.rankData.put(count, groupRankData);
            }
        } finally {
            this.taskLock.unlock();
        }
        unsortedList.clear();
    }
}
