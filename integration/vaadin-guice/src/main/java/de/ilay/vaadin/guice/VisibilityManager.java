package de.ilay.vaadin.guice;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;

import de.ilay.api.AuthenticationListener;
import de.ilay.service.AuthorizationService;
import de.ilay.vaadin.VaadinSessionConnector;

import java.util.Set;

@UIScope
public class VisibilityManager<PERMISSION, USER>  {

    private final VaadinSessionConnector<USER> sessionConnector;
    private final AuthorizationService<PERMISSION, USER> authorizationService;
    private final Set<AuthorizationDependentComponent<PERMISSION>> components;

    @Inject
    VisibilityManager(
            VaadinSessionConnector<USER> sessionConnector,
            AuthorizationService<PERMISSION, USER> authorizationService,
            Set<AuthorizationDependentComponent<PERMISSION>> components){
        this.sessionConnector = sessionConnector;
        this.authorizationService = authorizationService;
        this.components = components;
    }

    public void start(){
        sessionConnector.addSessionAuthenticationListener(new AuthenticationListener<USER>() {
            public void onLogin(USER user) {
                reEvaluateVisibility();
            }
            public void onLogout(USER user) {
                reEvaluateVisibility();
            }
        });

        reEvaluateVisibility();
    }

    private void reEvaluateVisibility(){
        for (AuthorizationDependentComponent<PERMISSION> component : components) {
            boolean componentIsPermitted = authorizationService.isPermitted(component.getNeededPermission());
            component.setVisible(componentIsPermitted);
        }
    }
}
