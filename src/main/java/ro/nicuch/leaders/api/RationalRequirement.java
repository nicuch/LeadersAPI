package ro.nicuch.leaders.api;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.data.requirments.Requirement;

import java.util.Set;

public class RationalRequirement {
    private final Set<Requirement> requirements;

    protected RationalRequirement(Set<Requirement> requirements) {
        this.requirements = requirements;
    }

    public final boolean checkRequirements(OfflinePlayer player) {
        for (Requirement requirement : this.requirements)
            if (requirement.isInverted()) {
                if (requirement.checkRequirement(player))
                    return false;
            } else {
                if (!requirement.checkRequirement(player))
                    return false;
            }
        return true;
    }
}
