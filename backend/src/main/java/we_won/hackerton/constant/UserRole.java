package we_won.hackerton.constant;

import we_won.hackerton.Interface.EnumModel;

public enum UserRole implements EnumModel {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_ADMIN");

    private final String role_user;

    UserRole(String role_user) {
        this.role_user = role_user;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return role_user;
    }
}
