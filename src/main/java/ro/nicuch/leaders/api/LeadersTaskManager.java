package ro.nicuch.leaders.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LeadersTaskManager {
    private final static Map<String, LeadersTask> leaderTasks = new HashMap<>(2, 0.95f);

    public static void registerLeaderTask(@NotNull LeadersTask leadersTask, @NotNull Plugin plugin) {
        leaderTasks.put(leadersTask.getTaskDescription().getId(), leadersTask);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, leadersTask, 0L, leadersTask.getTaskDescription().getUpdateTime() * 20L);
    }

    @Nullable
    public static LeadersTask getLeaderTask(@NotNull String id) {
        return leaderTasks.get(id);
    }

    public static boolean isLeaderTask(@NotNull String id) {
        return leaderTasks.containsKey(id);
    }

    @Nullable
    public static LeadersTask unregisterLeaderTask(@NotNull String id) {
        return leaderTasks.remove(id);
    }

    @NotNull
    public static Set<String> getLeaderTaskNames() {
        return leaderTasks.keySet();
    }
}
