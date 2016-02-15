package de.ilay.sample.service;

import de.ilay.sample.Exception.AuthenticationException;
import de.ilay.sample.Exception.UserNotFoundException;
import de.ilay.sample.api.AuthenticationEngine;
import de.ilay.sample.api.InsufficientCredentialsCallback;
import de.ilay.sample.api.SessionConnector;

/**
 * a class that handles login and logout
 *
 * @param <CREDENTIALS> the type of credentials to use for login
 * @param <USER>        the type of users to be authenticated
 */
public class AuthenticationService<CREDENTIALS, USER> {
    private final AuthenticationEngine<CREDENTIALS, USER> authenticationEngine;
    private final SessionConnector<USER> sessionConnector;

    /**
     * @param authenticationEngine to authenticate users
     * @param sessionConnector     to connect authenticated users to sessions
     */
    public AuthenticationService(AuthenticationEngine<CREDENTIALS, USER> authenticationEngine,
                                 SessionConnector<USER> sessionConnector) {
        if (authenticationEngine == null)
            throw new IllegalArgumentException("authenticationEngine cannot be null");
        if (sessionConnector == null)
            throw new IllegalArgumentException("sessionConnector cannot be null");

        this.authenticationEngine = authenticationEngine;
        this.sessionConnector = sessionConnector;
    }

    /**
     * log-in with certain credentials
     *
     * @param credentials the credentials to use for login
     * @return a user that was identified and authenticated by the given credentials, otherwise
     * Optional.empty()
     */
    public USER login(CREDENTIALS credentials, InsufficientCredentialsCallback<CREDENTIALS> insufficientCredentialsCallback) throws AuthenticationException, UserNotFoundException {
        if (credentials == null) throw new IllegalArgumentException("credentials cannot be null");
        if (insufficientCredentialsCallback == null)
            throw new IllegalArgumentException("insufficientCredentialsCallback cannot be null");

        try {
            return login(credentials);
        } catch (AuthenticationException e) {
            if (insufficientCredentialsCallback.healAuthenticationNotPossible(credentials, e)) {
                return login(credentials);
            } else {
                throw e;
            }
        } catch (UserNotFoundException e) {
            if (insufficientCredentialsCallback.healUserNotFound(credentials, e)) {
                return login(credentials);
            } else {
                throw e;
            }
        }
    }

    /**
     * log-in with certain credentials
     *
     * @param credentials the credentials to use for login
     * @return a user that was identified and authenticated by the given credentials, otherwise
     * Optional.empty()
     */
    public USER login(CREDENTIALS credentials) throws AuthenticationException, UserNotFoundException {
        if (credentials == null) throw new IllegalArgumentException("credentials cannot be null");

        final USER user = authenticationEngine.authenticateUser(credentials);

        if (user == null)
            throw new IllegalStateException("AuthenticationEngine.authenticateUser must not return null");

        sessionConnector.set(user);

        return user;
    }

    /**
     * log out the current user and disconnect him from the current session
     */
    public void logout() {
        sessionConnector.unSet();
    }
}
