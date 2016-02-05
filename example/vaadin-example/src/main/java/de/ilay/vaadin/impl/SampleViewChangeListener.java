package de.ilay.vaadin.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.vaadin.guice.annotation.GuiceViewChangeListener;
import com.vaadin.navigator.ViewChangeListener;

import de.ilay.vaadin.ViewPermission;

@GuiceViewChangeListener
@Singleton
public class SampleViewChangeListener implements ViewChangeListener{

    @Inject
    private SampleAuthorizationService sampleAuthorizationService;

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return sampleAuthorizationService.isPermitted(new ViewPermission(event));
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {
    }
}
