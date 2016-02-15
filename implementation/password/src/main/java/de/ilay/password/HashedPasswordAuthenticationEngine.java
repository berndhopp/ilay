package de.ilay.password;

import de.ilay.sample.exception.AuthenticationException;
import de.ilay.sample.exception.UserNotFoundException;
import de.ilay.sample.api.AuthenticationEngine;

public abstract class HashedPasswordAuthenticationEngine<USER_IDENTIFIER, CREDENTIALS extends PasswordCredentials<USER_IDENTIFIER>, USER extends HashedPasswordAuthentifyingUser>
        implements AuthenticationEngine<CREDENTIALS, USER> {

    public USER authenticateUser(CREDENTIALS credentials) throws UserNotFoundException, AuthenticationException {
        USER user = getUser(credentials.getUserIdentifier());

        final boolean passwordMatches = passwordsMatch(credentials.getPassword(), user.getPasswordHash());

        if (!passwordMatches) {
            throw new AuthenticationException("passwords do not match");
        }

        return user;
    }

    protected abstract boolean passwordsMatch(String plainText, String hashed);

    protected abstract USER getUser(USER_IDENTIFIER userIdentifier) throws UserNotFoundException;
}
