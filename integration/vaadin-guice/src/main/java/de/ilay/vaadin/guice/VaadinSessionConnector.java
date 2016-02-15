package de.ilay.vaadin.guice;

import com.vaadin.server.VaadinSession;

import de.ilay.sample.api.AuthenticationListener;
import de.ilay.sample.api.SessionConnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Connects users to the current {@see VaadinSession}
 *
 * @see IlayViewChangeListener is the main entry point for navigation based authorization
 */
public class VaadinSessionConnector<USER> extends SessionConnector<USER> {

    private final String AUTHENTICATION_LISTENERS_KEY;
    private final Class<USER> userType;

    public VaadinSessionConnector(Class<USER> userType) {
        if (userType == null) throw new IllegalArgumentException("userType cannot be null");
        this.userType = userType;
        this.AUTHENTICATION_LISTENERS_KEY = "authentication_listeners_" + userType.getSimpleName();
    }

    public Optional<USER> getCurrent() {
        return Optional.ofNullable(getVaadinSession().getAttribute(userType));
    }

    @Override
    public void addSessionAuthenticationListener(AuthenticationListener<USER> authenticationListener) {
        if (authenticationListener == null)
            throw new IllegalArgumentException("authenticationListener cannot be null");

        getSessionAuthenticationListeners().add(authenticationListener);
    }

    private VaadinSession getVaadinSession() {
        final VaadinSession vaadinSession = VaadinSession.getCurrent();

        if (vaadinSession == null) {
            throw new IllegalStateException("cannot call VaadinSessionConnector when no VaadinSession is available");
        }

        return vaadinSession;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Collection<AuthenticationListener<USER>> getSessionAuthenticationListeners() {
        final VaadinSession vaadinSession = getVaadinSession();

        Collection<AuthenticationListener<USER>> authenticationListeners = (Collection<AuthenticationListener<USER>>) vaadinSession.getAttribute(AUTHENTICATION_LISTENERS_KEY);

        if (authenticationListeners == null) {
            authenticationListeners = new ArrayList<AuthenticationListener<USER>>();
            vaadinSession.setAttribute(AUTHENTICATION_LISTENERS_KEY, authenticationListeners);
        }

        return authenticationListeners;
    }

    @Override
    protected void setInternal(USER user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        getVaadinSession().setAttribute(userType, user);
    }

    @Override
    protected void unSetInternal() {
        getVaadinSession().setAttribute(userType, null);
    }
}

