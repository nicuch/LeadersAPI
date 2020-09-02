package ro.nicuch.leaders.api;

import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.TaskType;

import java.util.Set;

public class TaskDescription {
    private final String id, placeholder,
            displayName, displayValue,
            dateFormat,
            noDisplayName, noDisplayValue,
            incrementPlaceholder;
    private final boolean reverseOrder;
    private final int updateTime, cacheSize;
    private final Set<String> excludedPlayers;
    private final TaskType taskType;
    private final ComparatorType comparatorType;
    private final TaskRequirement requirements;

    protected TaskDescription(String id, String placeholder, String displayName, String displayValue, String noDisplayName, String noDisplayValue, boolean reverseOrder, int updateTime, ComparatorType comparatorType, Set<String> excludedPlayers, int cacheSize, String dateFormat, TaskType taskType, String incrementPlaceholder, TaskRequirement requirements) {
        this.id = id;
        this.placeholder = placeholder;
        this.displayName = displayName;
        this.displayValue = displayValue;
        this.noDisplayName = noDisplayName;
        this.noDisplayValue = noDisplayValue;
        this.reverseOrder = reverseOrder;
        this.updateTime = updateTime;
        this.comparatorType = comparatorType;
        this.excludedPlayers = excludedPlayers;
        this.cacheSize = cacheSize;
        this.dateFormat = dateFormat;
        this.taskType = taskType;
        this.incrementPlaceholder = incrementPlaceholder;
        this.requirements = requirements;
    }

    public final String getId() {
        return this.id;
    }

    public final String getPlaceholder() {
        return this.placeholder;
    }

    public final String getDisplayname() {
        return this.displayName;
    }

    public final String getDisplayValue() {
        return this.displayValue;
    }

    public final String getNoDisplayName() {
        return this.noDisplayName;
    }

    public final String getNoDisplayValue() {
        return this.noDisplayValue;
    }

    public final boolean isReverseOrder() {
        return this.reverseOrder;
    }

    public final int getUpdateTime() {
        return this.updateTime;
    }

    public final int getCacheSize() {
        return this.cacheSize;
    }

    public final ComparatorType getComparatorType() {
        return this.comparatorType;
    }

    public final Set<String> getExcludedPlayers() {
        return this.excludedPlayers;
    }

    public final boolean excludePlayer(String player) {
        if (this.excludedPlayers.contains(player))
            return false;
        this.excludedPlayers.add(player);
        return true;
    }

    public final boolean includePlayer(String player) {
        if (!this.excludedPlayers.contains(player))
            return false;
        this.excludedPlayers.remove(player);
        return true;
    }

    public final String getDateFormat() {
        return this.dateFormat;
    }

    public final TaskType getTaskType() {
        return this.taskType;
    }

    public final String getIncrementPlaceholder() {
        return this.incrementPlaceholder;
    }

    public final TaskRequirement getTaskRequirements() {
        return this.requirements;
    }

}
