package de.ilay.vaadin.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import com.vaadin.guice.annotation.UIScope;

import de.ilay.sample.api.AuthenticationEngine;
import de.ilay.sample.api.AuthorizationEngine;
import de.ilay.sample.service.AuthorizationService;
import de.ilay.sample.service.AuthenticationService;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class IlayVaadinModule<CREDENTIALS, PERMISSION, USER> extends AbstractModule{

    private final Class<USER> userClass;
    private final Class<? extends AuthorizationEngine<PERMISSION, USER>> authorizationEngineClass;
    private final Class<? extends AuthenticationEngine<CREDENTIALS, USER>> authenticationEngineClass;

    public IlayVaadinModule(
            Class<USER> userClass,
            Class<? extends AuthorizationEngine<PERMISSION, USER>> authorizationEngineClass,
            Class<? extends AuthenticationEngine<CREDENTIALS, USER>> authenticationEngineClass
    ) {
        this.userClass = checkNotNull(userClass);
        this.authorizationEngineClass = checkNotNull(authorizationEngineClass);
        this.authenticationEngineClass = checkNotNull(authenticationEngineClass);
    }

    @Override
    protected void configure() {
        Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<AuthorizedComponent<PERMISSION>>() {},
            SetInclusionAnnotation.class
        );

        Multibinder.newSetBinder(
                binder(),
                new TypeLiteral<AuthorizedView<PERMISSION>>() {},
                SetInclusionAnnotation.class
        );

        bind(new TypeLiteral<VaadinSessionConnector<USER>>(){}).toInstance(new VaadinSessionConnector<USER>(userClass));
        bind(new TypeLiteral<AuthorizationEngine<PERMISSION, USER>>(){}).to(authorizationEngineClass);
        bind(new TypeLiteral<AuthenticationEngine<CREDENTIALS, USER>>(){}).to(authenticationEngineClass);
    }

    @Provides
    @UIScope
    public AuthenticationService<CREDENTIALS, USER> getAuthenticationService(
            AuthenticationEngine<CREDENTIALS, USER> authenticationEngine,
            VaadinSessionConnector<USER> sessionConnector
    ){
        return new AuthenticationService<CREDENTIALS, USER>(authenticationEngine, sessionConnector);
    }

    @Provides
    @UIScope
    public VisibilityManager<PERMISSION, USER> getVisibilityManager(
            VaadinSessionConnector<USER> sessionConnector,
            AuthorizationService<PERMISSION, USER> authorizationService,
            Set<AuthorizedComponent<PERMISSION>> authorizedComponents
    ){
        return new VisibilityManager<PERMISSION, USER>(sessionConnector, authorizationService, authorizedComponents);
    }
}
