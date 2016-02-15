package de.ilay.password.bcrypt;

import de.ilay.password.HashedPasswordAuthenticationEngine;
import de.ilay.password.PasswordCredentials;

public abstract class BCryptHashedPasswordAuthenticationEngine
        <
                USER_IDENTIFIER,
                CREDENTIALS extends PasswordCredentials<USER_IDENTIFIER>,
                USER extends BCryptHashedPasswordAuthentifyingUser
                >
        extends HashedPasswordAuthenticationEngine<USER_IDENTIFIER, CREDENTIALS, USER> {
    @Override
    protected boolean passwordsMatch(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }
}
