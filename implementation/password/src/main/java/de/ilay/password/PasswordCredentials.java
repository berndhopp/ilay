package de.ilay.password;

public interface PasswordCredentials<USER_IDENTIFIER> {
    String getPassword();

    USER_IDENTIFIER getUserIdentifier();
}
