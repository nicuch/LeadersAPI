package ro.nicuch.leaders;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ro.nicuch.leaders.api.LeadersTaskManager;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.api.TaskRequirementBuilder;
import ro.nicuch.leaders.api.TaskDescriptionBuilder;
import ro.nicuch.leaders.data.requirments.RequirementComparator;
import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.TaskType;
import ro.nicuch.leaders.tasks.PlayersLeadersTask;

public class LeadersPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        TaskDescriptionBuilder taskDescriptionBuilder = new TaskDescriptionBuilder("test")
                .setDisplayName("%player_name%")
                .setDisplayValue("%vault_eco_balance_formatted%")
                .setPlaceholder("%vault_eco_balance%")
                .setUpdateTime(30)
                .setComparatorType(ComparatorType.NUMBER_COMPARATOR)
                .setTaskType(TaskType.PLAYERS_SORT)
                .setTaskRequirements(
                        new TaskRequirementBuilder()
                                .addComparatorRequirement(new RequirementComparator("comparator", "<=", "%vault_eco_balance%", "500").setInverted(true))
                                .build()
                );
        LeadersTaskManager.registerLeaderTask(new PlayersLeadersTask(taskDescriptionBuilder.build()), this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void event(PlayerToggleSneakEvent event) {
        if (!event.getPlayer().isOp())
            return;
        if (event.isSneaking())
            return;
        RankData rank1 = LeadersTaskManager.getLeaderTask("test").getRankData(1);
        event.getPlayer().sendMessage("Rank: 1 / name: " + rank1.getDisplayName() + " value: " + rank1.getDisplayValue());
        RankData rank2 = LeadersTaskManager.getLeaderTask("test").getRankData(2);
        event.getPlayer().sendMessage("Rank: 2 / name: " + rank2.getDisplayName() + " value: " + rank2.getDisplayValue());
        RankData rank3 = LeadersTaskManager.getLeaderTask("test").getRankData(3);
        event.getPlayer().sendMessage("Rank: 3 / name: " + rank3.getDisplayName() + " value: " + rank3.getDisplayValue());
        RankData rank4 = LeadersTaskManager.getLeaderTask("test").getRankData(4);
        event.getPlayer().sendMessage("Rank: 4 / name: " + rank4.getDisplayName() + " value: " + rank4.getDisplayValue());
        RankData rank5 = LeadersTaskManager.getLeaderTask("test").getRankData(5);
        event.getPlayer().sendMessage("Rank: 5 / name: " + rank5.getDisplayName() + " value: " + rank5.getDisplayValue());
    }
}
