package org.example.rest.entities;

public enum Roles {
    CITIZEN("Гражданин"),
    OFFICER("Силовик"),
    WEAKLING("Слабовик"),
    FUNCTIONARY("Чиновник"),
    OCCUPANT("Прикорытник"),
    RESIDENT("Житель"),
    REPORTER("Журналист"),
    PROPAGANDIST("Пропагандист");

    private final String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
