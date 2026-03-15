package com.habibullahdm.auth.model.projection;

public interface UserRoleProjection {

    String getUserId();

    String getUsername();

    String getEmail();

    Boolean getIsActive();

    String getRoleName();
}
