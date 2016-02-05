package de.ilay.vaadin.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.ilay.service.AuthorizationService;
import de.ilay.vaadin.ViewPermission;

@Singleton
public class SampleAuthorizationService extends AuthorizationService<ViewPermission, SampleUser> {

    @Inject
    public SampleAuthorizationService(SampleAuthorizationEngine authorizationEngine, SampleVaadinSessionConnector currentUserProvider) {
        super(authorizationEngine, currentUserProvider);
    }
}
