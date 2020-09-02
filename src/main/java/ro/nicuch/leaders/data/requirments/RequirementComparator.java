package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.utils.PlaceholderUtils;

import java.math.BigDecimal;

public class RequirementComparator extends RequirementString {
    private final String type;

    public RequirementComparator(String name, String type, String input, String output) {
        super(RequirmentType.COMPARATOR, name, input, output);
        this.type = type;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        String inputString = PlaceholderUtils.setPlaceholders(player, super.getInput(), true);
        String outputString = PlaceholderUtils.setPlaceholders(player, super.getOutput(), true);

        BigDecimal inputDecimal = new BigDecimal(inputString);
        BigDecimal outputDecimal = new BigDecimal(outputString);

        switch (type) {
            case ">":
                return inputDecimal.compareTo(outputDecimal) > 0;
            case "<":
                return inputDecimal.compareTo(outputDecimal) < 0;
            case ">=":
                return inputDecimal.compareTo(outputDecimal) >= 0;
            case "<=":
                return inputDecimal.compareTo(outputDecimal) <= 0;
            case "!=":
                return inputDecimal.compareTo(outputDecimal) != 0;
            case "==":
            default:
                return inputDecimal.compareTo(outputDecimal) == 0;
        }
    }
}
