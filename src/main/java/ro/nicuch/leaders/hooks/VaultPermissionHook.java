package ro.nicuch.leaders.hooks;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VaultPermissionHook {
    private static Permission vaultPerms;

    public static void setup() {
        //noinspection ConstantConditions
        vaultPerms = Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();
    }

    public static boolean hasPermission(Player player, String perm) {
        return vaultPerms.has(player, perm);
    }

    public static boolean hasPermission(CommandSender sender, String perm) {
        return vaultPerms.has(sender, perm);
    }
}
