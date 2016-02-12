package de.ilay.vaadin.guice;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.GuiceViewChangeListener;

import de.ilay.service.AuthorizationService;
import de.ilay.vaadin.IlayViewChangeListener;
import de.ilay.vaadin.ViewPermission;

@GuiceViewChangeListener
public class IlayGuiceViewChangeListener<USER> extends IlayViewChangeListener<USER>{
    @Inject
    public IlayGuiceViewChangeListener(AuthorizationService<ViewPermission, USER> authorizationEngine) {
        super(authorizationEngine);
    }
}
