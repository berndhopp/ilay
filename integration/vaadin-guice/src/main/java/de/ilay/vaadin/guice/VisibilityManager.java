package de.ilay.vaadin.guice;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;

import de.ilay.sample.api.AuthenticationListener;
import de.ilay.sample.service.AuthorizationService;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@UIScope
public class VisibilityManager<PERMISSION, USER>  {

    private final VaadinSessionConnector<USER> sessionConnector;
    private final AuthorizationService<PERMISSION, USER> authorizationService;
    private final Set<AuthorizedComponent<PERMISSION>> components;

    @Inject
    VisibilityManager(
            VaadinSessionConnector<USER> sessionConnector,
            AuthorizationService<PERMISSION, USER> authorizationService,
            Set<AuthorizedComponent<PERMISSION>> components){
        this.sessionConnector = checkNotNull(sessionConnector);
        this.authorizationService = checkNotNull(authorizationService);
        this.components = checkNotNull(components);
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
        for (AuthorizedComponent<PERMISSION> component : components) {
            boolean componentIsPermitted = authorizationService.isPermitted(component.getNeededPermission());
            component.setVisible(componentIsPermitted);
        }
    }
}
