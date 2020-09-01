package ro.nicuch.leaders;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ro.nicuch.leaders.api.LeadersAPI;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.api.TaskDescriptionBuilder;
import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.TaskType;
import ro.nicuch.leaders.tasks.PlayersLeadersTask;

import java.util.Collections;

public class LeadersPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        TaskDescriptionBuilder taskDescriptionBuilder = new TaskDescriptionBuilder("test")
                .setDisplayName("%player_name%")
                .setDisplayValue("%vault_eco_balance_formatted%")
                .setPlaceholder("%vault_eco_balance%")
                .setUpdateTime(30)
                .setComparatorType(ComparatorType.DOUBLE_COMPARATOR)
                .setTaskType(TaskType.PLAYERS_SORT)
                .setExcludedPlayers(Collections.singleton("nicuch"));
        LeadersAPI.registerLeaderTask(new PlayersLeadersTask(taskDescriptionBuilder.build()), this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void event(PlayerToggleSneakEvent event) {
        if (!event.getPlayer().isOp())
            return;
        if (event.isSneaking())
            return;
        RankData rank1 = LeadersAPI.getLeaderTask("test").getRankData(1);
        event.getPlayer().sendMessage("Rank: 1 / group: " + rank1.getDisplayName() + " count: " + rank1.getDisplayValue());
        RankData rank2 = LeadersAPI.getLeaderTask("test").getRankData(2);
        event.getPlayer().sendMessage("Rank: 2 / group: " + rank2.getDisplayName() + " count: " + rank2.getDisplayValue());
        RankData rank3 = LeadersAPI.getLeaderTask("test").getRankData(3);
        event.getPlayer().sendMessage("Rank: 3 / group: " + rank3.getDisplayName() + " count: " + rank3.getDisplayValue());
        RankData rank4 = LeadersAPI.getLeaderTask("test").getRankData(4);
        event.getPlayer().sendMessage("Rank: 4 / group: " + rank4.getDisplayName() + " count: " + rank4.getDisplayValue());
        RankData rank5 = LeadersAPI.getLeaderTask("test").getRankData(5);
        event.getPlayer().sendMessage("Rank: 5 / group: " + rank5.getDisplayName() + " count: " + rank5.getDisplayValue());
    }
}
