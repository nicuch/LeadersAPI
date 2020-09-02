package ro.nicuch.leaders.enums;

public enum RequirmentType {
    HAS_PERMISSION("has-permission"),
    HAS_MONEY("has-money"),
    HAS_ITEM("has-item"),
    IS_NEAR("is-near"),
    JAVASCRIPT("javascript"),
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
        for (RequirmentType requirmentType : RequirmentType.values())
            if (requirmentType.getName().equalsIgnoreCase(name))
                return requirmentType;
        return null;
    }
}
