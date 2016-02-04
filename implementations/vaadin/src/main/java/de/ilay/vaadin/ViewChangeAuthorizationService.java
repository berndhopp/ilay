package de.ilay.vaadin;

import de.ilay.api.AuthorizationAwareUser;
import de.ilay.api.AuthorizationEngine;
import de.ilay.service.AuthorizationService;

/**
 * a {@see AuthorizationService} for the navigation between views
 * @see ViewChangeListener is the main entry point for navigation based authorization
 * @param <USER> the type of users
 */
public class ViewChangeAuthorizationService<USER extends AuthorizationAwareUser<?, ViewPermission>>
        extends AuthorizationService<ViewPermission, USER> {
    /**
     * @param authorizationEngine the {@see AuthorizationEngine} used to evaluate permissions
     * @param sessionConnector the {@see VaadinSessionConnector} used to get the current user that a permission is to be evaluated against
     */
    public ViewChangeAuthorizationService(
            AuthorizationEngine<ViewPermission, USER> authorizationEngine,
            VaadinSessionConnector<USER> sessionConnector
    ) {
        super(authorizationEngine, sessionConnector);
    }
}
