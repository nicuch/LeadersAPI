package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.utils.PlaceholderUtils;

import java.util.regex.Pattern;

public class RequirementRegexMatches extends AbstractRequirement {
    private final String input;
    private final String regex;

    public RequirementRegexMatches(String name, String input, String regex) {
        super(RequirmentType.STRING_EQUALS, name);
        this.input = input;
        this.regex = regex;
    }

    public final String getInput() {
        return this.input;
    }

    public final String getRegex() {
        return this.regex;
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        String inputString = PlaceholderUtils.setPlaceholders(player, this.input, true);
        String regexString = PlaceholderUtils.setPlaceholders(player, this.regex, true);
        return Pattern.matches(regexString, inputString);
    }
}
