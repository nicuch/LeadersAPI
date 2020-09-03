package ro.nicuch.leaders.api;

import org.jetbrains.annotations.NotNull;
import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.TaskType;

import java.util.HashSet;
import java.util.Set;

public class TaskDescriptionBuilder {
    private final String id;
    private String displayName = "%player_name%",
            displayValue = "none", placeholder = "",
            noDisplayName = "none", noDisplayValue = "none",
            dateFormat = "dd.MM.yyyy - HH:mm:ss",
            incrementPlaceholder = "1";
    private boolean reverseOrder = false;
    private int updateTime = 30, cacheSize = 100;
    private Set<String> excludedPlayers = new HashSet<>();
    private TaskType taskType = TaskType.PLAYERS_SORT;
    private ComparatorType comparatorType = ComparatorType.STRING_COMPARATOR;
    private TaskRequirement requirements = new TaskRequirementBuilder().build();

    /**
     * Create a new TaskDescriptionBuilder
     *
     * @param id the id of the task
     * @throws IllegalArgumentException if id is empty
     */
    @NotNull
    public TaskDescriptionBuilder(@NotNull String id) {
        if (id.isEmpty())
            throw new IllegalArgumentException("Task description ID can't be empty!");
        this.id = id;
    }

    /**
     * Set the task type
     * Default: TaskType.PLAYERS_SORT
     *
     * @param taskType the task type
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setTaskType(@NotNull TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * Set the placeholder (that will get sorted)
     *
     * @param placeholder the placeholder
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setPlaceholder(@NotNull String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    /**
     * Set the placeholder for display name
     * Default value is "%player_name%"
     *
     * @param displayName the placeholder for display name
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setDisplayName(@NotNull String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Set the placeholder for "not found" display name
     * Default value is "none"
     *
     * @param noDisplayName the placeholder for no display name
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setNoDisplayName(@NotNull String noDisplayName) {
        this.noDisplayName = noDisplayName;
        return this;
    }

    /**
     * Set the placeholder for display value
     * Default value is "none"
     *
     * @param displayValue the placeholder for display value
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setDisplayValue(@NotNull String displayValue) {
        this.displayValue = displayValue;
        return this;
    }

    /**
     * Set the placeholder for "not found" display value
     * Default value is "none"
     *
     * @param noDisplayValue the placeholder for display value
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setNoDisplayValue(@NotNull String noDisplayValue) {
        this.noDisplayValue = noDisplayValue;
        return this;
    }

    /**
     * Set the task sorting to be ascending or descending
     * Default: false
     * <p>
     * true = ascend, false = descend
     *
     * @param order the order type
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setReverse(boolean order) {
        this.reverseOrder = order;
        return this;
    }

    /**
     * Set the task interval (in seconds)
     * Default: 30 seconds
     *
     * @param updateTime the time in seconds
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    /**
     * Set the comparator type
     * Default: ComparatorType.STRING_COMPARATOR
     *
     * @param comparatorType the comparator type
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setComparatorType(@NotNull ComparatorType comparatorType) {
        this.comparatorType = comparatorType;
        return this;
    }

    /**
     * Set the players that will be excluded from the task sorting
     *
     * @param excludedPlayers the excluded players
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setExcludedPlayers(@NotNull Set<String> excludedPlayers) {
        this.excludedPlayers = excludedPlayers;
        return this;
    }

    /**
     * Set the cache size
     * Default: 100
     * <p>
     * This method sets how many players are kept in memory,
     * starting from rank 1 to rank n inclusive, where n is the cache size.
     * <p>
     * Please note that all online players are kept in a different cache
     * and this method doesn't affect it.
     *
     * @param cacheSize the cache size
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }

    /**
     * Set the {@link java.text.DateFormat}
     *
     * @param dateFormat the date format
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setDateFormat(@NotNull String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    /**
     * Set the placeholder used for incrementing {@link ro.nicuch.leaders.tasks.GroupIncrementLeadersTask}
     *
     * @param incrementPlaceholder the incrementing placeholder
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setIncrementPlaceholder(@NotNull String incrementPlaceholder) {
        this.incrementPlaceholder = incrementPlaceholder;
        return this;
    }

    /**
     * Set the requirements for this task
     *
     * @param requirements the requirements
     * @return this builder
     */
    @NotNull
    public final TaskDescriptionBuilder setTaskRequirements(@NotNull TaskRequirement requirements) {
        this.requirements = requirements;
        return this;
    }

    /**
     * Build the task description
     *
     * @return the task description
     */
    @NotNull
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
