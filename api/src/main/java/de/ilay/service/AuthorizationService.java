package de.ilay.service;

import de.ilay.api.AuthenticationEngine;
import de.ilay.api.AuthorizationAwareUser;
import de.ilay.api.AuthorizationEngine;
import de.ilay.api.SessionConnector;

import java.util.Optional;

public class AuthorizationService<CREDENTIALS, PERMISSION, USER extends AuthorizationAwareUser<CREDENTIALS, PERMISSION>>
        extends AuthenticationService<CREDENTIALS, USER> {

    private final AuthorizationEngine<PERMISSION, USER> authorizationEngine;

    public AuthorizationService(
            AuthenticationEngine<CREDENTIALS, USER> authenticationEngine,
            AuthorizationEngine<PERMISSION, USER> authorizationEngine,
            SessionConnector<USER> sessionConnector
    ) {
        super(authenticationEngine, sessionConnector);
        this.authorizationEngine = authorizationEngine;
    }

    public boolean isPermitted(PERMISSION permission) {
        if (permission == null) throw new IllegalArgumentException("permission cannot be null");

        final Optional<USER> currentUser = getCurrent();

        return currentUser.isPresent()
                ? authorizationEngine.hasPermission(currentUser.get(), permission)
                : authorizationEngine.isPermittedWithoutLogin(permission);
    }
}
