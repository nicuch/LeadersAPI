package ro.nicuch.leaders;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ro.nicuch.leaders.api.LeadersAPI;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.api.TaskDescriptionBuilder;
import ro.nicuch.leaders.tasks.GroupIncrementLeadersTask;

public class LeadersPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        TaskDescriptionBuilder taskDescriptionBuilder = new TaskDescriptionBuilder("test")
                .setDisplayName("%vault_rank%")
                .setDisplayValue("%count%")
                .setPlaceholder("%vault_rank%")
                .setIncrementPlaceholder("%vault_eco_balance_fixed%")
                .setUpdateTime(30)
                .setComparatorType("integer");
        LeadersAPI.registerLeaderTask(new GroupIncrementLeadersTask(taskDescriptionBuilder.build()), this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void event(PlayerMoveEvent event) {
        if (event.getTo() == null)
            return;
        if (event.getFrom().getBlock().equals(event.getTo().getBlock()))
            return;
        RankData playerRankData = LeadersAPI.getLeaderTask("test").getRankData(1);
        event.getPlayer().sendMessage("Rank: 2 / group: " + playerRankData.getDisplayName() + " count: " + playerRankData.getDisplayValue());
    }
}
