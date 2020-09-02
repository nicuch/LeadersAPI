package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;

public abstract class RequirementString extends Requirement {
    private final String input;
    private final String output;

    public RequirementString(RequirmentType requirementType, String requirementName, String input, String output) {
        super(requirementType, requirementName);
        this.input = input;
        this.output = output;
    }

    public final String getInput() {
        return this.input;
    }

    public final String getOutput() {
        return this.output;
    }

    @Override
    public abstract boolean checkRequirement(OfflinePlayer player);
}
