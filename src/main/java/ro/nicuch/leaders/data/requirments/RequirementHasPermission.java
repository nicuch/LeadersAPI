package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.hooks.VaultPermissionHook;

public class RequirementHasPermission extends AbstractRequirement {
    private final String permission;

    public RequirementHasPermission(String name, String permission) {
        super(RequirmentType.HAS_PERMISSION, name);
        this.permission = permission;
    }

    public final String getPermission() {
        return this.permission;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        if (!player.isOnline())
            return false;
        Player onlinePlayer = player.getPlayer();
        if (onlinePlayer == null)
            return false;
        return VaultPermissionHook.hasPermission(onlinePlayer, this.permission);
    }
}
