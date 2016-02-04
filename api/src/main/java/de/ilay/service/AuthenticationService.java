package de.ilay.service;

import de.ilay.api.AuthenticationEngine;
import de.ilay.api.SessionConnector;
import de.ilay.api.User;

import java.util.Optional;

/**
 * a class that handles login and logout
 * @param <CREDENTIALS> the type of credentials to use for login
 * @param <USER> the type of users to be authenticated
 */
public class AuthenticationService<CREDENTIALS, USER extends User<CREDENTIALS>> {
    private final AuthenticationEngine<CREDENTIALS, USER> authenticationEngine;
    private final SessionConnector<USER> sessionConnector;

    /**
     * @param authenticationEngine to authenticate users
     * @param sessionConnector to connect authenticated users to sessions
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
     * @param credentials the credentials to use for login
     * @return a user that was identified and authenticated by the given credentials, otherwise Optional.empty()
     */
    public Optional<USER> login(CREDENTIALS credentials) {
        if (credentials == null) throw new IllegalArgumentException("credentials cannot be null");

        final Optional<USER> user = authenticationEngine.loadUser(credentials);

        if (user.isPresent()) {
            sessionConnector.setCurrent(user.get());
        }

        return user;
    }

    /**
     * log out the current user and disconnect him from the current session
     */
    public void logout() {
        final Optional<USER> currentUser = sessionConnector.getCurrent();

        if(!currentUser.isPresent()){
            throw new IllegalStateException("cannot logout when no user is logged in");
        }

        sessionConnector.setCurrent(null);
    }
}
