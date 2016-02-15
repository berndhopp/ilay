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
    public USER login(CREDENTIALS credentials, final InsufficientCredentialsCallback<CREDENTIALS> callback) throws AuthenticationException, UserNotFoundException {
        if (credentials == null) throw new IllegalArgumentException("credentials cannot be null");
        if (callback == null)
            throw new IllegalArgumentException("insufficientCredentialsCallback cannot be null");

        
        /*
        * the idea here is:
        * - try to login
        * -- if a AuthenticationException is thrown, try to heal it
        * --- if you cannot heal it, throw it upwards
        * --- if you can heal it, try to login again
        * ---- if another AuthenticationException is thrown, throw it upwards
        * -- if a UserNotFoundException is thrown, try to heal it
        * --- if you cannot heal it, throw it upwards
        * --- if you can heal it, try to login again
        * ---- if another UserNotFoundException is thrown, throw it upwards
        * - if the login succeeds, return the user
        * */
        try {
            return login(credentials);
        } catch (AuthenticationException e) {
            if (callback.healAuthenticationNotPossible(credentials, e)) {
                return login(credentials, constrainCallback(callback, true, false));
            } else {
                throw e;
            }
        } catch (UserNotFoundException e) {
            if (callback.healUserNotFound(credentials, e)) {
                return login(credentials, constrainCallback(callback, false, true));
            } else {
                throw e;
            }
        }
    }

    private InsufficientCredentialsCallback<CREDENTIALS> constrainCallback(final InsufficientCredentialsCallback<CREDENTIALS> callback, final boolean allowHealUserNotFound, final boolean allowAuthenticationNotPossible){
        return new InsufficientCredentialsCallback<CREDENTIALS>() {
            public boolean healAuthenticationNotPossible(CREDENTIALS credentials, AuthenticationException e) {
                return allowAuthenticationNotPossible && callback.healAuthenticationNotPossible(credentials, e);
            }

            public boolean healUserNotFound(CREDENTIALS credentials, UserNotFoundException e) {
                return allowHealUserNotFound && callback.healUserNotFound(credentials, e);
            }
        };
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
