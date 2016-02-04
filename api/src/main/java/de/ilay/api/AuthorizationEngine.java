package de.ilay.api;


/**
 * instances of this class decide whether a certain {@see AuthorizationAwareUser} has a certain permission
 * @param <PERMISSION> the type of permissions to evaluate
 * @param <USER> the type of users that the AuthorizationEngine applies to
 */
public interface AuthorizationEngine<PERMISSION, USER extends AuthorizationAwareUser<?, PERMISSION>> {

    /**
     * decides wheter a certain user has a certain permission
     * @param user the user
     * @param permission the permission to be evaluated
     * @return true if the user is granted the permission, otherwise false
     */
    boolean hasPermission(USER user, PERMISSION permission);

    /**
     * decides whether a certain permission is granted without a user being logged in
     * @param permission the permission to be evaluated
     * @return true if the permission is granted, otherwise false
     */
    boolean isPermittedWithoutLogin(PERMISSION permission);
}
