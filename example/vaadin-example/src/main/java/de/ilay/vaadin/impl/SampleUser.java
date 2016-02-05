package de.ilay.vaadin.impl;

import de.ilay.password.bcrypt.BCryptHashedPasswordAuthentifyingUser;

public class SampleUser extends BCryptHashedPasswordAuthentifyingUser {

    private final String email;
    private boolean isAdmin;

    public SampleUser(String email, String password, boolean isAdmin){
        this.email = email;
        this.isAdmin = isAdmin;
        setPassword(password);
    }

    private String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
