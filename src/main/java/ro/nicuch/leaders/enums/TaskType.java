package ro.nicuch.leaders.enums;

public enum TaskType {
    PLAYERS_SORT("players", false),
    RATIONAL_PLAYERS("rational-players", true),
    GROUPS_COUNT("groups-count", false),
    GROUPS_INCREMENT("groups-increment", false),
    GROUPS_SORT("groups-sort", false),
    RATIONAL_GROUPS_COUNT("rational-groups-count", true),
    RATIONAL_GROUPS_INCREMENT("rational-groups-increment", true),
    RATIONAL_GROUPS_SORT("rational-groups-sort", true);

    final String name;
    final boolean rational;

    TaskType(String name, boolean rational) {
        this.name = name;
        this.rational = rational;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRational() {
        return this.rational;
    }

    public static TaskType fromName(String name) {
        for (TaskType taskType : TaskType.values())
            if (taskType.getName().equalsIgnoreCase(name))
                return taskType;
        return null;
    }
}
