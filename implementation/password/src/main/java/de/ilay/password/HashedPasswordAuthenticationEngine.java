package de.ilay.password;

import de.ilay.api.AuthenticationEngine;

import java.util.Optional;

public abstract class HashedPasswordAuthenticationEngine<USER_IDENTIFIER, CREDENTIALS extends PasswordCredentials<USER_IDENTIFIER>, USER extends HashedPasswordAuthentifyingUser>
        implements AuthenticationEngine<CREDENTIALS, USER>{

    public Optional<USER> authenticateUser(CREDENTIALS credentials) {
        Optional<USER> user = getUser(credentials.getUserIdentifier());

        if(!user.isPresent()){
            return user;
        }

        final boolean passwordMatches = passwordsMatch(credentials.getPassword(), user.get().getPasswordHash());

        return passwordMatches ? user : Optional.<USER>empty();
    }

    protected abstract boolean passwordsMatch(String plainText, String hashed);

    protected abstract Optional<USER> getUser(USER_IDENTIFIER userIdentifier);
}
