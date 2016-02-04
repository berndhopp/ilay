package de.ilay.api;


/**
 * An {@see User} that can be used in authorization-based scenarios, it defines a certain permission type
 * @param <CREDENTIALS> the type of credentials a user must provide to login
 * @param <PERMISSION> the type of permission that a user needs for certain operations
 */
public interface AuthorizationAwareUser<CREDENTIALS, PERMISSION> extends User<CREDENTIALS> {
}
