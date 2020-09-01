package ro.nicuch.leaders.enums;

public enum ComparatorType {
    STRING_COMPARATOR("string"),
    INTEGER_COMPARATOR("integer"),
    DOUBLE_COMPARATOR("double"),
    LONG_COMPARATOR("long"),
    DATE_COMPARATOR("date");

    final String name;

    ComparatorType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static ComparatorType fromName(String name) {
        for (ComparatorType comparatorType : ComparatorType.values())
            if (comparatorType.getName().equalsIgnoreCase(name))
                return comparatorType;
        return null;
    }
}
