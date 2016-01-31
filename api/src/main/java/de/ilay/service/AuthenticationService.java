package de.ilay.service;

import de.ilay.api.AuthenticationEngine;
import de.ilay.api.CurrentUserProvider;
import de.ilay.api.SessionConnector;
import de.ilay.api.User;

import java.util.Optional;

public class AuthenticationService<CREDENTIALS, USER extends User<CREDENTIALS>> implements
        AuthenticationEngine<CREDENTIALS, USER>,
        CurrentUserProvider<USER> {

    private final AuthenticationEngine<CREDENTIALS, USER> authenticationEngine;
    private final SessionConnector<USER> sessionConnector;

    protected AuthenticationService(AuthenticationEngine<CREDENTIALS, USER> authenticationEngine,
                                    SessionConnector<USER> sessionConnector) {

        if (authenticationEngine == null)
            throw new IllegalArgumentException("authenticationEngine cannot be null");
        if (sessionConnector == null)
            throw new IllegalArgumentException("sessionConnector cannot be null");

        this.authenticationEngine = authenticationEngine;
        this.sessionConnector = sessionConnector;
    }

    public Optional<USER> login(CREDENTIALS credentials) {
        if (credentials == null) throw new IllegalArgumentException("credentials cannot be null");

        final Optional<USER> user = authenticationEngine.login(credentials);

        if (user.isPresent()) {
            sessionConnector.setCurrent(user.get());
        }

        return user;
    }

    public Optional<USER> getCurrent() {
        return sessionConnector.getCurrent();
    }

    public void logout() {
        sessionConnector.setCurrent(null);
    }
}