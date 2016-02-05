package de.ilay.vaadin.impl;

import de.ilay.service.AuthenticationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SampleAuthenticationService extends AuthenticationService<SampleCredentials, SampleUser> {

    @Inject
    SampleAuthenticationService(SampleAuthenticationEngine authenticationEngine, SampleVaadinSessionConnector sessionConnector) {
        super(authenticationEngine, sessionConnector);
    }
}
