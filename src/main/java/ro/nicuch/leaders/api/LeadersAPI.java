package ro.nicuch.leaders.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class LeadersAPI {
    private final static Map<String, LeadersTask> topsMap = new HashMap<>();

    public static void registerLeaderTask(@NotNull LeadersTask leadersTask, @NotNull Plugin plugin) {
        topsMap.put(leadersTask.getTaskDescription().getId(), leadersTask);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, leadersTask, 0L, leadersTask.getTaskDescription().getUpdateTime() * 20L);
    }

    @Nullable
    public static LeadersTask getLeaderTask(@NotNull String id) {
        return topsMap.get(id);
    }
}
