package ro.nicuch.leaders.api;

import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.TaskType;

import java.util.HashSet;
import java.util.Set;

public class TaskDescriptionBuilder {
    private final String id;
    private String displayName = "%player_name%",
            displayValue = "none", placeholder = "",
            noDisplayName = "none", noDisplayValue = "none",
            dateFormat = "yyyy.MM.dd G 'at' HH:mm:ss z",
            incrementPlaceholder = "";
    private boolean reverseOrder = false;
    private int updateTime = 30, cacheSize = 100;
    private Set<String> excludedPlayers = new HashSet<>();
    private TaskType taskType = TaskType.PLAYERS_SORT;
    private ComparatorType comparatorType = ComparatorType.STRING_COMPARATOR;
    private TaskRequirement requirements = new TaskRequirementBuilder().build();

    public TaskDescriptionBuilder(String id) {
        this.id = id;
    }

    public final TaskDescriptionBuilder setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public final TaskDescriptionBuilder setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public final TaskDescriptionBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public final TaskDescriptionBuilder setNoDisplayName(String noDisplayName) {
        this.noDisplayName = noDisplayName;
        return this;
    }

    public final TaskDescriptionBuilder setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
        return this;
    }

    public final TaskDescriptionBuilder setNoDisplayValue(String noDisplayValue) {
        this.noDisplayValue = noDisplayValue;
        return this;
    }

    public final TaskDescriptionBuilder setReverse(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
        return this;
    }

    public final TaskDescriptionBuilder setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public final TaskDescriptionBuilder setComparatorType(ComparatorType comparatorType) {
        this.comparatorType = comparatorType;
        return this;
    }

    public final TaskDescriptionBuilder setExcludedPlayers(Set<String> excludedPlayers) {
        this.excludedPlayers = excludedPlayers;
        return this;
    }

    public final TaskDescriptionBuilder setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }

    public final TaskDescriptionBuilder setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public final TaskDescriptionBuilder setIncrementPlaceholder(String incrementPlaceholder) {
        this.incrementPlaceholder = incrementPlaceholder;
        return this;
    }

    public final TaskDescriptionBuilder setTaskRequirements(TaskRequirement requirements) {
        this.requirements = requirements;
        return this;
    }

    public final TaskDescription build() {
        return new TaskDescription(this.id, this.placeholder, this.displayName
                , this.displayValue, this.noDisplayName
                , this.noDisplayValue, this.reverseOrder
                , this.updateTime, this.comparatorType
                , this.excludedPlayers, this.cacheSize
                , this.dateFormat, this.taskType
                , this.incrementPlaceholder, this.requirements);
    }
}
