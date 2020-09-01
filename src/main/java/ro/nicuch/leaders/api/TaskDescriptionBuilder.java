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
            incrementPlaceholder = "",
            rationalPlaceholder = "none", rationalRequirement = "true";
    private boolean reverseOrder = false;
    private int updateTime = 30, cacheSize = 100;
    private Set<String> excludedPlayers = new HashSet<>();
    private TaskType taskType = TaskType.PLAYERS_SORT;
    private ComparatorType comparatorType = ComparatorType.STRING_COMPARATOR;

    public TaskDescriptionBuilder(String id) {
        this.id = id;
    }

    public TaskDescriptionBuilder setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public TaskDescriptionBuilder setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public TaskDescriptionBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public TaskDescriptionBuilder setNoDisplayName(String noDisplayName) {
        this.noDisplayName = noDisplayName;
        return this;
    }

    public TaskDescriptionBuilder setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
        return this;
    }

    public TaskDescriptionBuilder setNoDisplayValue(String noDisplayValue) {
        this.noDisplayValue = noDisplayValue;
        return this;
    }

    public TaskDescriptionBuilder setReverse(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
        return this;
    }

    public TaskDescriptionBuilder setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public TaskDescriptionBuilder setComparatorType(ComparatorType comparatorType) {
        this.comparatorType = comparatorType;
        return this;
    }

    public TaskDescriptionBuilder setExcludedPlayers(Set<String> excludedPlayers) {
        this.excludedPlayers = excludedPlayers;
        return this;
    }

    public TaskDescriptionBuilder setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }

    public TaskDescriptionBuilder setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public TaskDescriptionBuilder setIncrementPlaceholder(String incrementPlaceholder) {
        this.incrementPlaceholder = incrementPlaceholder;
        return this;
    }

    public TaskDescriptionBuilder setRationalPlaceholder(String rationalPlaceholder) {
        this.rationalPlaceholder = rationalPlaceholder;
        return this;
    }

    public TaskDescriptionBuilder setRationalRequirement(String rationalRequirement) {
        this.rationalRequirement = rationalRequirement;
        return this;
    }

    public TaskDescription build() {
        return new TaskDescription(this.id, this.placeholder, this.displayName
                , this.displayValue, this.noDisplayName
                , this.noDisplayValue, this.reverseOrder
                , this.updateTime, this.comparatorType
                , this.excludedPlayers, this.cacheSize
                , this.dateFormat, this.taskType
                , this.incrementPlaceholder, this.rationalPlaceholder, this.rationalRequirement);
    }
}
