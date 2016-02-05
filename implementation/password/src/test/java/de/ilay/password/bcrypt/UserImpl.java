package de.ilay.password.bcrypt;

class UserImpl extends BCryptHashedPasswordAuthentifyingUser {
    private String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
