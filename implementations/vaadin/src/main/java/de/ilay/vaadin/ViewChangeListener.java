package de.ilay.vaadin;

import de.ilay.api.AuthorizationAwareUser;

/**
 * A {@see ViewChangeListener} that will not allow navigation to views that the currently logged
 * in user is not allowed to navigate to. A ViewChangeListener needs to be connected to a {@see Navigator}
 * per Navigator.addViewChangeListener()
 * @param <USER> the type of users
 */
public class ViewChangeListener<USER extends AuthorizationAwareUser<?, ViewPermission>>
        implements com.vaadin.navigator.ViewChangeListener {

    private final ViewChangeAuthorizationService<USER> authorizationEngine;

    /**
     * @param authorizationEngine the {@see AuthorizationEngine} to use to evaluate the {@see ViewPermission}s
     */
    public ViewChangeListener(ViewChangeAuthorizationService<USER> authorizationEngine) {
        this.authorizationEngine = authorizationEngine;
    }

    public boolean beforeViewChange(ViewChangeEvent event) {
        final ViewPermission viewPermission = new ViewPermission(event);
        return authorizationEngine.isPermitted(viewPermission);
    }

    public void afterViewChange(ViewChangeEvent event) {
    }
}
