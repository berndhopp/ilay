package de.ilay.sample.api;

import de.ilay.sample.exception.AuthenticationException;
import de.ilay.sample.exception.UserNotFoundException;

public interface InsufficientCredentialsCallback<CREDENTIALS> {
    boolean healAuthenticationNotPossible(CREDENTIALS credentials, AuthenticationException e);

    boolean healUserNotFound(CREDENTIALS credentials, UserNotFoundException e);
}
