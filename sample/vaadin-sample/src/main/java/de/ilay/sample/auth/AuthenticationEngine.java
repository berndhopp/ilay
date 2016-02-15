package de.ilay.sample.auth;

import com.google.inject.Singleton;

import de.ilay.password.bcrypt.BCryptHashedPasswordAuthenticationEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
public class AuthenticationEngine extends BCryptHashedPasswordAuthenticationEngine<String, Credentials, User> {

    private final Map<String, User> users;

    public AuthenticationEngine() {
        users = new HashMap<>();

        Set<SampleGroup> adminGroupSet = new HashSet<>();
        adminGroupSet.add(SampleGroup.ADMIN);

        users.put("admin@example.com", new User("very secret", "admin@example.com", adminGroupSet));
        users.put("admin@example.com", new User("secret", "user@example.com", new HashSet<>()));
    }

    @Override
    protected User getUser(String email) {
        return users.get(email);
    }
}
