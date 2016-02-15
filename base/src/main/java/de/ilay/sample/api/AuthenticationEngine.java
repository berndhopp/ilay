package de.ilay.sample.api;

import de.ilay.sample.exception.AuthenticationException;
import de.ilay.sample.exception.UserNotFoundException;

/**
 * A class to do authentication for a specific user-type and it's credentials
 *
 * @param <CREDENTIALS> the type of credentials to use to authenticate
 * @param <USER>        the type of users to be authenticated
 */
public interface AuthenticationEngine<CREDENTIALS, USER> {
    /**
     * tries to authenticate a certain user with the credentials given
     *
     * @param credentials the credentials to authenticate the user
     * @return the user that had been authenticated by the given credentials
     * @throws AuthenticationException if the given credentials identify a user but are not
     *                                 sufficient to authenticate that user ( e.g. passwords don't
     *                                 match )
     * @throws UserNotFoundException   if the given credentials do not identify a user known on the
     *                                 system
     */
    USER authenticateUser(CREDENTIALS credentials) throws AuthenticationException, UserNotFoundException;
}
