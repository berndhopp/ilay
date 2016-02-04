package de.ilay.password;

public interface HashedPasswordAuthentifyingUser<CREDENTIALS extends PasswordCredentials> {
    String getPasswordHash();
    void setPasswordHash(String passwordHash);
}
