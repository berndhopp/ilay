package de.ilay.vaadin.impl;

import com.google.inject.Singleton;

import com.vaadin.navigator.View;

import de.ilay.api.AuthorizationEngine;
import de.ilay.vaadin.ViewPermission;
import de.ilay.vaadin.views.AdminView;
import de.ilay.vaadin.views.PublicView;
import de.ilay.vaadin.views.UserView;

@Singleton
public class SampleAuthorizationEngine implements AuthorizationEngine<ViewPermission, SampleUser>{

    @Override
    public boolean hasPermission(SampleUser sampleUser, ViewPermission viewPermission) {

        final View newView = viewPermission.getViewChangeEvent().getNewView();

        if(newView instanceof AdminView){
            return sampleUser.isAdmin();
        }

        //here we have a logged in user, so all other views are accessible
        return true;
    }

    @Override
    public boolean isPermittedWithoutLogin(ViewPermission viewPermission) {
        return viewPermission.getViewChangeEvent().getNewView() instanceof PublicView;
    }
}
