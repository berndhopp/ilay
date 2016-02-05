package de.ilay.vaadin.impl;

import de.ilay.password.PasswordCredentials;

public class SampleCredentials implements PasswordCredentials<String> {
    private String email;
    private String password;

    public SampleCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserIdentifier() {
        return email;
    }
}
