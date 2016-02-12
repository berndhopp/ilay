package de.ilay.vaadin.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import com.vaadin.guice.annotation.GuiceViewChangeListener;

import de.ilay.service.AuthorizationService;
import de.ilay.vaadin.VaadinSessionConnector;
import de.ilay.vaadin.guice.AuthorizationDependentComponent;
import de.ilay.vaadin.guice.AuthorizationDependentComponentAnnotation;
import de.ilay.vaadin.guice.IlayGuiceViewChangeListener;
import de.ilay.vaadin.guice.VisibilityManager;

public class IlayVaadinModule<PERMISSION, USER> extends AbstractModule{

    private final Class<? extends VaadinSessionConnector<USER>> sessionConnectorClass;
    private final Class<? extends AuthorizationService<PERMISSION, USER>> authorizationServiceClass;
    private final Class<? extends VisibilityManager<PERMISSION, USER>> visibilityManagerClass;
    private final Class<? extends IlayGuiceViewChangeListener<USER>> viewChangeListenerClass;

    public IlayVaadinModule(
            Class<? extends VaadinSessionConnector<USER>> sessionConnectorClass,
            Class<? extends AuthorizationService<PERMISSION, USER>> authorizationServiceClass,
            Class<? extends VisibilityManager<PERMISSION, USER>> visibilityManagerClass,
            Class<? extends IlayGuiceViewChangeListener<USER>> viewChangeListenerClass
    ) {
        this.viewChangeListenerClass = viewChangeListenerClass;
        if(sessionConnectorClass == null) throw new IllegalArgumentException("sessionConnectorClass cannot be null");
        if(authorizationServiceClass == null) throw new IllegalArgumentException("authorizationServiceClass cannot be null");
        if(visibilityManagerClass == null) throw new IllegalArgumentException("visibilityManagerClass cannot be null");

        this.sessionConnectorClass = sessionConnectorClass;
        this.authorizationServiceClass = authorizationServiceClass;
        this.visibilityManagerClass = visibilityManagerClass;
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<VaadinSessionConnector<USER>>() {
        }).to(sessionConnectorClass);
        bind(new TypeLiteral<AuthorizationService<PERMISSION, USER>>() {
        }).to(authorizationServiceClass);
        bind(new TypeLiteral<VisibilityManager<PERMISSION, USER>>() {
        }).to(visibilityManagerClass);

        Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<AuthorizationDependentComponent<PERMISSION>>() {},
            AuthorizationDependentComponentAnnotation.class
        );
    }
}
