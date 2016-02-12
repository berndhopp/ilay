package de.ilay.vaadin;

import de.ilay.service.AuthorizationService;

/**
 * A {@see IlayViewChangeListener} that will not allow navigation to views that the currently logged
 * in user is not allowed to navigate to. A IlayViewChangeListener needs to be connected to a {@see Navigator}
 * per Navigator.addViewChangeListener()
 * @param <USER> the type of users
 */
public class IlayViewChangeListener<USER>
        implements com.vaadin.navigator.ViewChangeListener {

    private final AuthorizationService<ViewPermission, USER> authorizationService;

    /**
     * @param authorizationService the {@see AuthorizationEngine} to use to evaluate the {@see ViewPermission}s
     */
    public IlayViewChangeListener(AuthorizationService<ViewPermission, USER> authorizationService) {
        this.authorizationService = authorizationService;
    }

    public boolean beforeViewChange(ViewChangeEvent event) {
        final ViewPermission viewPermission = new ViewPermission(event);
        return authorizationService.isPermitted(viewPermission);
    }

    public void afterViewChange(ViewChangeEvent event) {
    }
}
