package de.ilay.api;

import java.util.Optional;

/**
 * instances of this interface make available the currently logged in user
 * @param <USER> the type of users
 */
public interface CurrentUserProvider<USER> {
    /**
     * @return the currently logged in user, or Optional.empty() if no user is logged in
     */
    Optional<USER> getCurrent();
}
