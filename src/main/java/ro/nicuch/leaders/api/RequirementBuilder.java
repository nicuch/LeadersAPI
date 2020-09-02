package ro.nicuch.leaders.api;

import ro.nicuch.leaders.data.requirments.*;

import java.util.HashSet;
import java.util.Set;

public class RequirementBuilder {
    private final Set<Requirement> requirements = new HashSet<>();

    public RequirementBuilder addComparatorRequirement(RequirementComparator requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addHasItemRequirement(RequirementHasItem requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addHasMoneyRequirement(RequirementHasMoney requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addHasPermissionRequirement(RequirementHasPermission requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addIsNearRequirement(RequirementIsNear requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addRegexMatchesRequirement(RequirementRegexMatches requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addStringContainsRequirement(RequirementStringContains requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addStringEquialsRequirement(RequirementStringEquals requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RequirementBuilder addStringEqualsIgnoreCaseRequirement(RequirementStringEqualsIgnoreCase requirement) {
        this.requirements.add(requirement);
        return this;
    }

    public RationalRequirement build() {
        return new RationalRequirement(this.requirements);
    }
}
