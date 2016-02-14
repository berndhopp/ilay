package de.ilay.sample.auth;

import de.ilay.groups.GroupAwareUser;
import de.ilay.password.bcrypt.BCryptHashedPasswordAuthentifyingUser;

import java.util.Set;

public class User extends BCryptHashedPasswordAuthentifyingUser implements GroupAwareUser<SampleGroup> {

    private String passwordHash;
    private String email;
    private Set<SampleGroup> groups;

    public User(String password, String email, Set<SampleGroup> groups) {
        setPassword(password);
        this.email = email;
        this.groups = groups;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isInGroup(SampleGroup group) {
        return groups.contains(group);
    }
}
