package br.com.falzoni.falzoni_java_api.domain.enums;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}
