package de.ilay.vaadin.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import de.ilay.vaadin.guice.AuthorizationDependentComponent;
import de.ilay.vaadin.guice.AuthorizationDependentComponentAnnotation;

public class IlayVaadinModule<PERMISSION> extends AbstractModule{
    @Override
    protected void configure() {
        Multibinder.newSetBinder(
            binder(),
            new TypeLiteral<AuthorizationDependentComponent<PERMISSION>>() {},
            AuthorizationDependentComponentAnnotation.class
        );
    }
}
