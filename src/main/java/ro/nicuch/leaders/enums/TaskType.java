package ro.nicuch.leaders.enums;

public enum TaskType {
    PLAYERS_SORT("players"),
    GROUPS_COUNT("groups-count"),
    GROUPS_INCREMENT("groups-increment"),
    GROUPS_SORT("groups-sort");

    final String name;

    TaskType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TaskType fromName(String name) {
        for (TaskType taskType : TaskType.values())
            if (taskType.getName().equalsIgnoreCase(name))
                return taskType;
        return null;
    }
}
