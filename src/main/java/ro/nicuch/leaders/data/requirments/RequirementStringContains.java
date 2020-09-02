package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.utils.PlaceholderUtils;

public class RequirementStringContains extends RequirementString {

    public RequirementStringContains(String name, String input, String output) {
        super(RequirmentType.STRING_EQUALS, name, input, output);
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        String inputString = PlaceholderUtils.setPlaceholders(player, super.getInput(), true);
        String outputString = PlaceholderUtils.setPlaceholders(player, super.getOutput(), true);
        return inputString.contains(outputString);
    }
}
