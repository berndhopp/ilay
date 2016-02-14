package de.ilay.sample.auth;

import de.ilay.groups.GroupPermission;
import de.ilay.vaadin.guice.IlayVaadinModule;

public class SampleIlayVaadinModule extends IlayVaadinModule<Credentials, GroupPermission<SampleGroup>, User> {
    public SampleIlayVaadinModule() {
        super(User.class, AuthorizationEngine.class, AuthenticationEngine.class);
    }
}
