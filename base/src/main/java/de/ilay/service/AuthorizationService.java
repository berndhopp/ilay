package de.ilay.service;

import de.ilay.api.AuthorizationEngine;
import de.ilay.api.CurrentUserProvider;

import java.util.Optional;

/**
 * A service for user-authorization. Instances of this class decide whether a certain permission is
 * granted in the current context.
 * @param <PERMISSION> the types of permission that are to be evaluated
 * @param <USER> the types of users an AuthorizationService applies to
 */
public class AuthorizationService<PERMISSION, USER> {

    private final AuthorizationEngine<PERMISSION, USER> authorizationEngine;
    private final CurrentUserProvider<USER> currentUserProvider;

    /**
     * @param authorizationEngine the {@see AuthorizationEngine} used to evaluate permissions
     * @param currentUserProvider the {@see CurrentUserProvider} used to get the current user that a permission is to be evaluated against
     */
    public AuthorizationService(
            AuthorizationEngine<PERMISSION, USER> authorizationEngine,
            CurrentUserProvider<USER> currentUserProvider
    ) {
        if(authorizationEngine == null) throw new IllegalArgumentException("authorizationEngine cannot be null");
        if(currentUserProvider == null) throw new IllegalArgumentException("currentUserProvider cannot be null");
        this.authorizationEngine = authorizationEngine;
        this.currentUserProvider = currentUserProvider;
    }

    /**
     * decides whether a certain PERMISSION is granted in the current context, that is for the currently logged in user
     * @param permission the permission which is to be evaluated
     * @return true if the permission was granted, otherwise false
     */
    public boolean isPermitted(PERMISSION permission) {
        if (permission == null) throw new IllegalArgumentException("permission cannot be null");

        final Optional<USER> currentUser = currentUserProvider.getCurrent();

        return currentUser.isPresent()
                ? authorizationEngine.hasPermission(currentUser.get(), permission)
                : authorizationEngine.isPermittedWithoutLogin(permission);
    }
}
