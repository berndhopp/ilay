package de.ilay.vaadin.guice;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;

import de.ilay.sample.api.AuthenticationListener;
import de.ilay.sample.service.AuthorizationService;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;


@UIScope
public class VisibilityManager<PERMISSION, USER> implements AuthenticationListener<USER> {

    private final VaadinSessionConnector<USER> sessionConnector;
    private final AuthorizationService<PERMISSION, USER> authorizationService;
    private final Set<AuthorizedComponent<PERMISSION>> components;

    @Inject
    VisibilityManager(
            VaadinSessionConnector<USER> sessionConnector,
            AuthorizationService<PERMISSION, USER> authorizationService,
            Set<AuthorizedComponent<PERMISSION>> components) {
        this.sessionConnector = checkNotNull(sessionConnector);
        this.authorizationService = checkNotNull(authorizationService);
        this.components = checkNotNull(components);
    }

    public void start() {
        sessionConnector.addSessionAuthenticationListener(this);
        init();
    }

    private void init() {
        for (AuthorizedComponent<PERMISSION> component : components) {
            boolean componentIsPermitted = authorizationService.isPermitted(component.getNeededPermission());
            component.setVisible(componentIsPermitted);
        }
    }

    public void onLogin(USER user) {
        for (AuthorizedComponent<PERMISSION> component : components) {
            boolean componentIsPermitted = authorizationService.hasPermission(user, component.getNeededPermission());
            component.setVisible(componentIsPermitted);
        }
    }

    public void onLogout(USER user) {
        for (AuthorizedComponent<PERMISSION> component : components) {
            boolean componentIsPermitted = authorizationService.isPermittedWithoutLogin(component.getNeededPermission());
            component.setVisible(componentIsPermitted);
        }
    }
}
