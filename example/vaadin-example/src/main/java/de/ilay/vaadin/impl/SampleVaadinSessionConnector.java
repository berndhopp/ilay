package de.ilay.vaadin.impl;

import com.google.inject.Singleton;

import de.ilay.vaadin.VaadinSessionConnector;

@Singleton
public class SampleVaadinSessionConnector extends VaadinSessionConnector<SampleUser> {

    SampleVaadinSessionConnector(){
        super(SampleUser.class);
    }
}
