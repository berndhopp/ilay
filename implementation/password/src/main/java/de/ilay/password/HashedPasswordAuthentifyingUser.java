package de.ilay.password;

public interface HashedPasswordAuthentifyingUser {
    String getPasswordHash();
    void setPasswordHash(String passwordHash);
}
