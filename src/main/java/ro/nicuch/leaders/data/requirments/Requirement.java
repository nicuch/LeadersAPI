package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;

public abstract class Requirement {
    private final RequirmentType requirementType;
    private final String requirementName;
    private boolean inverted = false;

    public Requirement(RequirmentType requirementType, String requirementName) {
        this.requirementType = requirementType;
        this.requirementName = requirementName;
    }

    public final RequirmentType getRequirementType() {
        return this.requirementType;
    }

    public final String getRequirementName() {
        return this.requirementName;
    }

    public final boolean isInverted() {
        return this.inverted;
    }

    public final void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public abstract boolean checkRequirement(OfflinePlayer player);
}
