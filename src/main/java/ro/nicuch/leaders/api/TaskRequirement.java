package ro.nicuch.leaders.api;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.data.requirments.AbstractRequirement;

import java.util.Set;

public class TaskRequirement {
    private final Set<AbstractRequirement> abstractRequirements;

    protected TaskRequirement(Set<AbstractRequirement> abstractRequirements) {
        this.abstractRequirements = abstractRequirements;
    }

    public final boolean checkRequirements(OfflinePlayer player) {
        for (AbstractRequirement abstractRequirement : this.abstractRequirements)
            if (abstractRequirement.isInverted()) {
                if (abstractRequirement.checkRequirement(player))
                    return false;
            } else {
                if (!abstractRequirement.checkRequirement(player))
                    return false;
            }
        return true;
    }
}
