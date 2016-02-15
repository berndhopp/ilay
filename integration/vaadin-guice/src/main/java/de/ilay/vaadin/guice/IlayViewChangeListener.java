package de.ilay.vaadin.guice;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.GuiceViewChangeListener;
import com.vaadin.guice.annotation.UIScope;

import de.ilay.sample.service.AuthorizationService;

import java.util.Set;

@UIScope
@GuiceViewChangeListener
class IlayViewChangeListener<PERMISSION> implements com.vaadin.navigator.ViewChangeListener {

    private final Set<? extends AuthorizedView<PERMISSION>> authorizedViews;
    private final AuthorizationService<PERMISSION, ?> authorizationService;

    @Inject
    public IlayViewChangeListener(
            Set<? extends AuthorizedView<PERMISSION>> authorizedViews,
            AuthorizationService<PERMISSION, ?> authorizationService
    ) {
        this.authorizedViews = authorizedViews;
        this.authorizationService = authorizationService;
    }

    public boolean beforeViewChange(ViewChangeEvent event) {
        if (!authorizedViews.contains(event.getNewView())) {
            return true;
        }

        @SuppressWarnings("unchecked")
        AuthorizedView<PERMISSION> authorizedView = (AuthorizedView<PERMISSION>) event.getNewView();

        @SuppressWarnings("unchecked")
        PERMISSION permission = authorizedView.getNeededPermission();

        return authorizationService.isPermitted(permission);
    }

    public void afterViewChange(ViewChangeEvent event) {
    }
}

