package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;

public abstract class AbstractRequirement {
    private final RequirmentType requirementType;
    private final String requirementName;
    private boolean inverted = false;

    public AbstractRequirement(RequirmentType requirementType, String requirementName) {
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

    @SuppressWarnings("unchecked")
    public final <T extends AbstractRequirement> T setInverted(boolean inverted) {
        this.inverted = inverted;
        return (T) this;
    }

    public abstract boolean checkRequirement(OfflinePlayer player);
}
