package com.server.cms.type;

public enum Role {

    USER("ROLE_USER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
