package team.hackerping.nanuri.user.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserStatus implements GrantedAuthority {
    BLOCKED,
    ACTIVE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}