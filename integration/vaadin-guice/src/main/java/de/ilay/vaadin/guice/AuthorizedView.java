package de.ilay.vaadin.guice;

import com.vaadin.navigator.View;

@SetInclusionAnnotation
public interface AuthorizedView<PERMISSION> extends View{
    PERMISSION getNeededPermission();
}
