package com.jj.springbootinpractice.config;

import java.util.List;

public class Security {
    private boolean enabled;

    private final String token;

    private final List<String> roles;

    public Security(boolean enabled, String token, List<String> roles) {
        this.enabled = enabled;
        this.token = token;
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getToken() {
        return token;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "Security{" +
                "enabled=" + enabled +
                ", token='" + token + '\'' +
                ", roles=" + roles +
                '}';
    }
}
