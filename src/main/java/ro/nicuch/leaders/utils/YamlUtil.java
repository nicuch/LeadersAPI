package ro.nicuch.leaders.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import ro.nicuch.leaders.api.TaskDescription;
import ro.nicuch.leaders.api.TaskDescriptionBuilder;
import ro.nicuch.leaders.api.TaskRequirement;
import ro.nicuch.leaders.api.TaskRequirementBuilder;
import ro.nicuch.leaders.data.requirments.*;
import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.RequirmentType;
import ro.nicuch.leaders.enums.TaskType;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class YamlUtil {

    public static TaskDescription readYamlConfiguration(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String id = null;
        if (config.isString("id"))
            id = config.getString("id");
        if (id == null) throw new IllegalArgumentException("ID for " + file.getName() + " wasn't set!");
        int cacheSize = config.getInt("cache-size", 30);
        String displayName = config.getString("display-name", "%player_name%");
        String displayValue = config.getString("display-value", "unknown");
        TaskType taskType = TaskType.fromName(config.getString("task-type", "players"));
        TaskRequirement taskRequirement;
        if (config.isConfigurationSection("requirements"))
            taskRequirement = readTaskRequirementFromConfigurationSection(config.getConfigurationSection("requirements"));
        else
            taskRequirement = TaskRequirementBuilder.empty();
        Set<String> excludedPlayers = new HashSet<>(config.isList("excluded-players") ? config.getStringList("excluded-players") : new ArrayList<>());
        ComparatorType comparatorType = ComparatorType.fromName(config.getString("comparator-type", "string"));
        String dateFormat = config.getString("date-format", "dd.MM.yyyy - HH:mm:ss");
        String incrementPlaceholder = config.getString("increment-placeholder", "1");
        String noDisplayName = config.getString("error-display-name", "unknown");
        String noDisplayValue = config.getString("error-display-value", "unknown");
        String placeholder = config.getString("placeholder", "");
        boolean reverse = config.getString("order-mode", "ascending").equalsIgnoreCase("ascending");
        int updateTime = config.getInt("update-interval", 30);
        TaskDescriptionBuilder taskDescriptionBuilder = new TaskDescriptionBuilder(id)
                .setCacheSize(cacheSize)
                .setDisplayName(displayName)
                .setDisplayValue(displayValue)
                .setTaskType(taskType)
                .setTaskRequirements(taskRequirement)
                .setExcludedPlayers(excludedPlayers)
                .setComparatorType(comparatorType)
                .setDateFormat(dateFormat)
                .setIncrementPlaceholder(incrementPlaceholder)
                .setNoDisplayName(noDisplayName)
                .setNoDisplayValue(noDisplayValue)
                .setPlaceholder(placeholder)
                .setReverse(reverse)
                .setUpdateTime(updateTime);
        return taskDescriptionBuilder.build();
    }

    public static TaskRequirement readTaskRequirementFromConfigurationSection(ConfigurationSection configurationSection) {
        TaskRequirementBuilder taskRequirementBuilder = new TaskRequirementBuilder();
        for (String key : configurationSection.getKeys(false)) {
            ConfigurationSection section = configurationSection.getConfigurationSection(key);
            String typeString = section.getString("type", "string-equals");
            RequirmentType type = RequirmentType.fromName(typeString);
            boolean inverted = typeString.startsWith("!");
            String input;
            String output;
            switch (type) {
                case HAS_PERMISSION:
                    input = section.getString("permission", "has.permission");
                    taskRequirementBuilder.addHasPermissionRequirement(new RequirementHasPermission(key, input).setInverted(inverted));
                    break;
                case REGEX_MATCHES:
                    input = section.getString("input", "");
                    output = section.getString("regex", "");
                    taskRequirementBuilder.addRegexMatchesRequirement(new RequirementRegexMatches(key, input, output).setInverted(inverted));
                    break;
                case HAS_ITEM:
                    ItemStack itemStack = section.getItemStack("item", new ItemStack(Material.BEDROCK));
                    taskRequirementBuilder.addHasItemRequirement(new RequirementHasItem(key, itemStack).setInverted(inverted));
                    break;
                case COMPARATOR:
                    input = section.getString("input", "");
                    output = section.getString("output", "");
                    taskRequirementBuilder.addComparatorRequirement(new RequirementComparator(key, typeString, input, output));
                    break;
                case IS_NEAR:
                    String[] args = section.getString("location", "0,0,0,world").split(",");
                    Location location;
                    try {
                        location = new Location(Bukkit.getWorld(args[3]), new BigDecimal(args[0]).doubleValue(), new BigDecimal(args[1]).doubleValue(), new BigDecimal(args[2]).doubleValue());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        location = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
                    }
                    double distance = section.getDouble("distance", 100);
                    taskRequirementBuilder.addIsNearRequirement(new RequirementIsNear(key, location, distance).setInverted(inverted));
                    break;
                case STRING_CONTAINS:
                    input = section.getString("input", "");
                    output = section.getString("output", "");
                    taskRequirementBuilder.addStringContainsRequirement(new RequirementStringContains(key, input, output).setInverted(inverted));
                    break;
                case STRING_EQUALS_IGNORECASE:
                    input = section.getString("input", "");
                    output = section.getString("output", "");
                    taskRequirementBuilder.addStringEqualsIgnoreCaseRequirement(new RequirementStringEqualsIgnoreCase(key, input, output).setInverted(inverted));
                    break;
                case STRING_EQUALS:
                default:
                    input = section.getString("input", "");
                    output = section.getString("output", "");
                    taskRequirementBuilder.addStringEquialsRequirement(new RequirementStringEquals(key, input, output).setInverted(inverted));
                    break;
            }
        }
        return taskRequirementBuilder.build();
    }
}
