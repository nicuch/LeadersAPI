package ro.nicuch.leaders.api;

import ro.nicuch.leaders.data.requirments.*;

import java.util.HashSet;
import java.util.Set;

public class TaskRequirementBuilder {
    private final Set<AbstractRequirement> abstractRequirements = new HashSet<>();

    public final TaskRequirementBuilder addComparatorRequirement(RequirementComparator requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addHasItemRequirement(RequirementHasItem requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addHasPermissionRequirement(RequirementHasPermission requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addIsNearRequirement(RequirementIsNear requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addRegexMatchesRequirement(RequirementRegexMatches requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addStringContainsRequirement(RequirementStringContains requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addStringEquialsRequirement(RequirementStringEquals requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirementBuilder addStringEqualsIgnoreCaseRequirement(RequirementStringEqualsIgnoreCase requirement) {
        this.abstractRequirements.add(requirement);
        return this;
    }

    public final TaskRequirement build() {
        return new TaskRequirement(this.abstractRequirements);
    }

    public static TaskRequirement empty() {
        return new TaskRequirementBuilder().build();
    }
}
