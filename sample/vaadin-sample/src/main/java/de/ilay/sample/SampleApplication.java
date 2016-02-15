package de.ilay.sample;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
public class SampleApplication extends VerticalLayout {

    @Inject
    SampleApplication(MainViewContainer mainViewContainer) {
        addComponent(mainViewContainer);
    }
}
