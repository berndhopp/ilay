package de.ilay.api;

public interface AuthenticationListener<USER> {
    void onLogin(USER user);
    void onLogout(USER user);
}
