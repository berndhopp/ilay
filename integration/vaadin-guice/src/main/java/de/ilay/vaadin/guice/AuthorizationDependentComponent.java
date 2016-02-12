package de.ilay.vaadin.guice;

import com.google.inject.multibindings.ProvidesIntoSet;

import com.vaadin.ui.Component;

@AuthorizationDependentComponentAnnotation
public interface AuthorizationDependentComponent<PERMISSION> extends Component{
    PERMISSION getNeededPermission();
}
