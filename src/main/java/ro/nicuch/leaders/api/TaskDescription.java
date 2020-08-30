package ro.nicuch.leaders.api;

import java.util.Set;

public class TaskDescription {
    private final String id, placeholder, displayName, displayValue, comparatorType, dateFormat, noDisplayName, noDisplayValue, taskType, incrementPlaceholder;
    private final boolean reverseOrder;
    private final int updateTime, cacheSize;
    private final Set<String> excludedPlayers;

    protected TaskDescription(String id, String placeholder, String displayName, String displayValue, String noDisplayName, String noDisplayValue, boolean reverseOrder, int updateTime, String comparatorType, Set<String> excludedPlayers, int cacheSize, String dateFormat, String taskType, String incrementPlaceholder) {
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
    }

    public String getId() {
        return this.id;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public String getDisplayname() {
        return this.displayName;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }

    public String getNoDisplayName() {
        return this.noDisplayName;
    }

    public String getNoDisplayValue() {
        return this.noDisplayValue;
    }

    public boolean isReverseOrder() {
        return this.reverseOrder;
    }

    public int getUpdateTime() {
        return this.updateTime;
    }

    public int getCacheSize() {
        return this.cacheSize;
    }

    public String getComparatorType() {
        return this.comparatorType;
    }

    public Set<String> getExcludedPlayers() {
        return this.excludedPlayers;
    }

    public boolean excludePlayer(String player) {
        if (this.excludedPlayers.contains(player))
            return false;
        this.excludedPlayers.add(player);
        return true;
    }

    public boolean includePlayer(String player) {
        if (!this.excludedPlayers.contains(player))
            return false;
        this.excludedPlayers.remove(player);
        return true;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getIncrementPlaceholder() {
        return this.incrementPlaceholder;
    }
}
