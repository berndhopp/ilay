package de.ilay.vaadin;

import com.vaadin.server.VaadinSession;

import de.ilay.api.SessionConnector;

import java.util.Optional;

/**
 * Connects users to the current {@see VaadinSession}
 * @see ViewChangeListener is the main entry point for navigation based authorization
 * @param <USER>
 */
public class VaadinSessionConnector<USER> implements SessionConnector<USER> {

    private final Class<USER> userType;

    public VaadinSessionConnector(Class<USER> userType) {
        if(userType == null) throw new IllegalArgumentException("userType cannot be null");
        this.userType = userType;
    }

    public Optional<USER> getCurrent() {
        final VaadinSession vaadinSession = VaadinSession.getCurrent();

        if(vaadinSession == null){
            throw new IllegalStateException("cannot call VaadinSessionConnector.getCurrent() when no VaadinSession is available");
        }

        return Optional.ofNullable(vaadinSession.getAttribute(userType));
    }

    public void setCurrent(USER currentUser) {
        if(currentUser == null) throw new IllegalArgumentException("currentUser cannot be null");

        final VaadinSession vaadinSession = VaadinSession.getCurrent();

        if(vaadinSession == null){
            throw new IllegalStateException("cannot call VaadinSessionConnector.getCurrent() when no VaadinSession is available");
        }

        vaadinSession.setAttribute(userType, currentUser);
    }
}
