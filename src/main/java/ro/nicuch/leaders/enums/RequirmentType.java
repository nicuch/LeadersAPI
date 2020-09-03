package ro.nicuch.leaders.enums;

public enum RequirmentType {
    HAS_PERMISSION("has-permission"),
    HAS_ITEM("has-item"),
    IS_NEAR("is-near"),
    STRING_EQUALS("string-equals"),
    STRING_EQUALS_IGNORECASE("string-equals-ignorecase"),
    STRING_CONTAINS("string-contains"),
    REGEX_MATCHES("regex-matches"),
    COMPARATOR("comparator");

    final String name;

    RequirmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static RequirmentType fromName(String name) {
        if (name.equals("==") || name.equals("!=") ||
                name.equals("<") || name.equals("<=") ||
                name.equals(">") || name.equals(">="))
            return COMPARATOR;
        boolean inverted = name.startsWith("!");
        if (inverted)
            name = name.substring(0, 1);
        for (RequirmentType requirmentType : RequirmentType.values())
            if (requirmentType.getName().equalsIgnoreCase(name))
                return requirmentType;
        return STRING_EQUALS;
    }
}
