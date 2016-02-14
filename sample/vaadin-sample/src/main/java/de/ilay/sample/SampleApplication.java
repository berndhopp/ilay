package de.ilay.sample;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import com.google.inject.Inject;

@UIScope
public class SampleApplication extends VerticalLayout{

    @Inject
    SampleApplication(MainViewContainer mainViewContainer){
        addComponent(mainViewContainer);
    }
}
