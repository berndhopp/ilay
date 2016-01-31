package de.ilay.api;

import java.util.Optional;

public interface AuthenticationEngine<CREDENTIALS, USER extends User<CREDENTIALS>> {
    Optional<USER> login(CREDENTIALS credentials);
}