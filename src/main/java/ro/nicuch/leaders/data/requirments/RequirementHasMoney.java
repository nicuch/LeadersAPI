package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;

public class RequirementHasMoney extends Requirement {
    private final double money;

    public RequirementHasMoney(String name, double money) {
        super(RequirmentType.HAS_MONEY, name);
        this.money = money;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        return false; //TODO
    }
}
