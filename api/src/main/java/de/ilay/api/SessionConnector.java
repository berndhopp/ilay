package de.ilay.api;

public interface SessionConnector<USER extends User<?>> extends CurrentUserProvider<USER> {
    void setCurrent(USER currentUser);
}
