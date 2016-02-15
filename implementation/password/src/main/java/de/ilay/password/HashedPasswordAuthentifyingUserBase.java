package de.ilay.password;

public abstract class HashedPasswordAuthentifyingUserBase implements HashedPasswordAuthentifyingUser {

    protected abstract String hashPassword(String password);

    public void setPassword(String password) {
        if (password == null) throw new NullPointerException();
        setPasswordHash(hashPassword(password));
    }
}
