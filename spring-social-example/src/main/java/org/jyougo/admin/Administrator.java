package org.jyougo.admin;

import org.springframework.social.connect.ConnectionKey;

public enum Administrator {
    Yuci(new ConnectionKey("orcid", "0000-0001-8160-1147"), "ROLE_ADMIN");
    private ConnectionKey key;
    private String role;
    Administrator(ConnectionKey key, String role) {
        this.key = key;
        this.role = role;
    }
    public ConnectionKey getKey() {
        return key;
    }
    public String getRole() {
        return role;
    }
}
