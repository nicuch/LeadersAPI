package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.utils.PlaceholderUtils;

import java.math.BigDecimal;

public class RequirementComparator extends Requirement {
    private final String type;
    private final String input;
    private final String output;

    public RequirementComparator(String name, String type, String input, String output) {
        super(RequirmentType.COMPARATOR, name);
        this.type = type;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        String inputString = PlaceholderUtils.setPlaceholders(player, this.input, true);
        String outputString = PlaceholderUtils.setPlaceholders(player, this.output, true);

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
