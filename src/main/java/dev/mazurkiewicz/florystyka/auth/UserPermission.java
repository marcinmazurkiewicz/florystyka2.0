package dev.mazurkiewicz.florystyka.auth;

public enum UserPermission {
    QUESTION_READ("question:read"),
    QUESTION_WRITE("question:write"),
    SOLUTION_READ("solution:read"),
    SOLUTION_WRITE("solution:write"),
    TEST_READ("test:read"),
    TEST_WRITE("test:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");


    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
