package ro.nicuch.leaders.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ro.nicuch.leaders.LeadersPlugin;

import java.util.List;

public class LeadersCommand implements TabExecutor {
    private final LeadersPlugin plugin;

    public LeadersCommand(LeadersPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

    public enum PermissionKey {
        ALL("*"),
        COMMAND_ALL("command.*"),
        COMMAND_RELOAD("command.reload");

        final String perm;

        PermissionKey(String perm) {
            this.perm = perm;
        }

        public final String getPermission() {
            return "leadersapi." + this.perm;
        }
    }
}
