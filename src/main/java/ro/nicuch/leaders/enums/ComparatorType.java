package ro.nicuch.leaders.enums;

public enum ComparatorType {
    STRING_COMPARATOR("string"),
    NUMBER_COMPARATOR("number-comparator"),
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
        return STRING_COMPARATOR;
    }
}
