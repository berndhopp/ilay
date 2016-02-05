package de.ilay.vaadin.impl;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;

import de.ilay.password.bcrypt.BCryptHashedPasswordAuthenticationEngine;

import java.util.Optional;

@Singleton
public class SampleAuthenticationEngine extends BCryptHashedPasswordAuthenticationEngine<String, SampleCredentials, SampleUser> {

    private final ImmutableMap<String, SampleUser> users = ImmutableMap.of(
            "admin@example.com", new SampleUser("admin@example.com", "very secret", true),
            "user@example.com", new SampleUser("user@example.com", "secret", false)
    );

    protected Optional<SampleUser> getUser(String userIdentifier){
        return Optional.ofNullable(users.get(userIdentifier));
    }
}