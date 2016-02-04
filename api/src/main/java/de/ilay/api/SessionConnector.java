package de.ilay.api;

/**
 * instances of this interface connect a user with a certain "session", e.g. a JSecurity session in
 * web environments.
 * @param <USER> the type of user to connect to a session
 */
public interface SessionConnector<USER extends User<?>> extends CurrentUserProvider<USER> {
    /**
     * attaches a user to the current session
     * @param currentUser the user to attach to the current session
     */
    void setCurrent(USER currentUser);
}
