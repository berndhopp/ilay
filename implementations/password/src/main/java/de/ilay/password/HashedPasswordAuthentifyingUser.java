package de.ilay.password;

import de.ilay.api.User;

public interface HashedPasswordAuthentifyingUser<CREDENTIALS extends PasswordCredentials> extends User<CREDENTIALS> {
    String getPasswordHash();
    void setPasswordHash(String passwordHash);
}
