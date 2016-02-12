package de.ilay.api;

import java.util.Optional;

/**
 * instances of this interface connect a user with a certain "session", e.g. a JSecurity session in
 * web environments.
 * @param <USER> the type of user to connect to a session
 */
public abstract class SessionConnector<USER> implements CurrentUserProvider<USER> {

    public abstract void addSessionAuthenticationListener(AuthenticationListener<USER> authenticationListener);
    protected abstract Iterable<AuthenticationListener<USER>> getSessionAuthenticationListeners();

    public void set(USER user){
        if(getCurrent().isPresent()){
            throw new IllegalStateException("already logged in");
        }

        setInternal(user);

        for (AuthenticationListener<USER> authenticationListener : getSessionAuthenticationListeners()) {
            authenticationListener.onLogin(user);
        }
    }

    public void unSet(){
        final Optional<USER> previousUser = getCurrent();

        if(!previousUser.isPresent()){
            throw new IllegalStateException("cannot log out without logged in user");
        }

        unSetInternal();

        for (AuthenticationListener<USER> authenticationListener : getSessionAuthenticationListeners()) {
            authenticationListener.onLogout(previousUser.get());
        }
    }

    protected abstract void setInternal(USER user);
    protected abstract void unSetInternal();
}