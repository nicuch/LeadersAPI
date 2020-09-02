package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;

public class RequirementHasPermission extends Requirement {
    private final String permission;

    public RequirementHasPermission(String name, String permission) {
        super(RequirmentType.HAS_PERMISSION, name);
        this.permission = permission;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        return false; //TODO
    }
}
