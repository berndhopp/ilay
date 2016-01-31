package de.ilay.password.bcrypt;

import de.ilay.password.PasswordCredentials;

class CredentialsImpl implements PasswordCredentials<Integer> {

    private final String password;
    private final int userId;

    CredentialsImpl(String password, int userId) {
        this.password = password;
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserIdentifier() {
        return userId;
    }
}
