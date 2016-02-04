package de.ilay.password.bcrypt;

class UserImpl extends BCryptHashedPasswordAuthentifyingUser<CredentialsImpl> {
    private String passwordHash;

    @Override
    protected boolean passwordAppliesToConventions(String password) {
        return password.length() > 5;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
