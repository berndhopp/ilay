package de.ilay.sample.auth;

import de.ilay.password.PasswordCredentials;

public class Credentials implements PasswordCredentials<String> {

    private String password;
    private String email;

    public Credentials(String password, String email) {
        this.password = password;
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUserIdentifier() {
        return email;
    }
}
