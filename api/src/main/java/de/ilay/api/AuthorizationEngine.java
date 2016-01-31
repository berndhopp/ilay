package de.ilay.api;

public interface AuthorizationEngine<PERMISSION, USER extends AuthorizationAwareUser<?, PERMISSION>> {
    boolean hasPermission(USER user, PERMISSION permission);

    boolean isPermittedWithoutLogin(PERMISSION permission);
}
