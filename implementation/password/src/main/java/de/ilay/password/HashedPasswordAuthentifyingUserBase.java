package de.ilay.password;

public abstract class HashedPasswordAuthentifyingUserBase<CREDENTIALS extends PasswordCredentials> implements HashedPasswordAuthentifyingUser<CREDENTIALS> {

    protected abstract String hashPassword(String password);

    public void setPassword(String password){
        if(password == null) throw new NullPointerException();
        setPasswordHash(hashPassword(password));
    }
}
