package de.ilay.api;

import java.util.Optional;

/**
 * A class to do authentication for a specific user-type and it's credentials
 * @param <CREDENTIALS> the type of credentials to use to authenticate
 * @param <USER> the type of users to be authenticated
 */
public interface AuthenticationEngine<CREDENTIALS, USER> {

    /**
     * tries to authenticate a certain user with the credentials given
     * @param credentials the credentials to authenticate the user
     * @return the user that had been authenticated by the given credentials, or
     * Optional.empty() if no user could be authenticated
     */
    Optional<USER> authenticateUser(CREDENTIALS credentials);
}
