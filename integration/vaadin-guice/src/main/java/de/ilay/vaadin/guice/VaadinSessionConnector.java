package de.ilay.vaadin.guice;

import com.vaadin.server.VaadinSession;

import de.ilay.sample.api.AuthenticationListener;
import de.ilay.sample.api.SessionConnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Connects users to the current {@see VaadinSession}
 * @see IlayViewChangeListener is the main entry point for navigation based authorization
 * @param <USER>
 */
public class VaadinSessionConnector<USER> extends SessionConnector<USER> {

    private static final String AUTHENTICATION_LISTENERS_KEY = "authentication_listeners";
    private final Class<USER> userType;

    public VaadinSessionConnector(Class<USER> userType) {
        if(userType == null) throw new IllegalArgumentException("userType cannot be null");
        this.userType = userType;
    }

    public Optional<USER> getCurrent() {
        return Optional.ofNullable(getVaadinSession().getAttribute(userType));
    }

    @Override
    public void addSessionAuthenticationListener(AuthenticationListener<USER> authenticationListener) {
        if(authenticationListener == null) throw new IllegalArgumentException("authtenticationListener cannot be null");

        getAuthenticationListeners().add(authenticationListener);
    }

    private VaadinSession getVaadinSession(){
        final VaadinSession vaadinSession = VaadinSession.getCurrent();

        if(vaadinSession == null){
            throw new IllegalStateException("cannot call VaadinSessionConnector when no VaadinSession is available");
        }

        return vaadinSession;
    }

    @SuppressWarnings("unchecked")
    private Collection<AuthenticationListener<USER>> getAuthenticationListeners(){
        final VaadinSession vaadinSession = getVaadinSession();

        Collection<AuthenticationListener<USER>> authenticationListeners = (Collection<AuthenticationListener<USER>>) vaadinSession.getAttribute(AUTHENTICATION_LISTENERS_KEY);

        if(authenticationListeners == null){
            authenticationListeners = new ArrayList<AuthenticationListener<USER>>();
            vaadinSession.setAttribute(AUTHENTICATION_LISTENERS_KEY, authenticationListeners);
        }

        return authenticationListeners;
    }

    @Override
    protected Collection<AuthenticationListener<USER>> getSessionAuthenticationListeners() {
        return getAuthenticationListeners();
    }

    @Override
    protected void setInternal(USER user) {
        if(user == null) throw new IllegalArgumentException("user cannot be null");
        getVaadinSession().setAttribute(userType, user);
    }

    @Override
    protected void unSetInternal() {
        getVaadinSession().setAttribute(userType, null);
    }
}

