package de.ilay.api;

import java.util.Optional;

public interface CurrentUserProvider<USER extends User<?>> {
    Optional<USER> getCurrent();
}
