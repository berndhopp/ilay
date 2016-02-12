package de.ilay.vaadin;

import com.vaadin.navigator.ViewChangeListener;

/**
 * A permission needed to access a certain view with certain parameters
 * @see ViewChangeListener is the main entry point for navigation based authorization
 */
public class ViewPermission {

    private final ViewChangeListener.ViewChangeEvent viewChangeEvent;

    public ViewPermission(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        this.viewChangeEvent = viewChangeEvent;
    }

    /**
     * @return the {@see IlayViewChangeListener.ViewChangeEvent}
     */
    public ViewChangeListener.ViewChangeEvent getViewChangeEvent() {
        return viewChangeEvent;
    }
}
