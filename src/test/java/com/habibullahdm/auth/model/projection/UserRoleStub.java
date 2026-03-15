package com.habibullahdm.auth.model.projection;

public record UserRoleStub(
        String userId,
        String username,
        String email,
        Boolean isActive,
        String roleName
)
implements UserRoleProjection{
    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }
}
