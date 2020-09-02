package ro.nicuch.leaders.data.requirments;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ro.nicuch.leaders.enums.RequirmentType;

public class RequirementIsNear extends AbstractRequirement {
    private final Location location;
    private final double distance;

    public RequirementIsNear(String name, Location location, double distance) {
        super(RequirmentType.IS_NEAR, name);
        this.location = location;
        this.distance = distance;
    }

    public final Location getLocation() {
        return this.location;
    }

    public final double getDistance() {
        return this.distance;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        if (!player.isOnline())
            return false;
        Player onlinePlayer = player.getPlayer();
        if (onlinePlayer == null) // might not be null, still checking to be sure
            return false;
        Location playerLocation = onlinePlayer.getLocation();
        if (playerLocation.getWorld() == null && this.location.getWorld() == null) // getWorld() throws NPE when world is not loaded
            return false;
        if (!playerLocation.getWorld().equals(this.location.getWorld())) // location.distance doesn't work between different worlds
            return false;
        return onlinePlayer.getLocation().distance(this.location) <= distance;
    }
}
