package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ro.nicuch.leaders.enums.RequirmentType;

public class RequirementHasItem extends Requirement {
    private final ItemStack itemStack;

    public RequirementHasItem(String name, ItemStack itemStack) {
        super(RequirmentType.HAS_PERMISSION, name);
        this.itemStack = itemStack;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        if (!player.isOnline())
            return false;
        Player onlinePlayer = player.getPlayer();
        if (onlinePlayer == null)
            return false;
        return onlinePlayer.getInventory().contains(this.itemStack);
    }
}
