package ro.nicuch.leaders.data.requirments;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.utils.PlaceholderUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequirementRegexMatches extends Requirement {
    private final String input;
    private final Pattern pattern;

    public RequirementRegexMatches(String name, String input, String regex) {
        super(RequirmentType.STRING_EQUALS, name);
        this.input = input;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean checkRequirement(OfflinePlayer player) {
        String inputString = PlaceholderUtils.setPlaceholders(player, this.input, true);
        Matcher matcher = this.pattern.matcher(inputString);
        return matcher.matches();
    }
}
