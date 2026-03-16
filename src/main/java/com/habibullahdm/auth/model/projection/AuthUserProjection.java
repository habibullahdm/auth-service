package com.habibullahdm.auth.model.projection;

public interface AuthUserProjection {

    String getUserId();

    String getUsername();

    String getPassword();

    Boolean getIsActive();

    String getRoleName();
}
