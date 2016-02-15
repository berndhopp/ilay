package de.ilay.sample.api;

public interface AuthenticationListener<USER> {
    void onLogin(USER user);

    void onLogout(USER user);
}
