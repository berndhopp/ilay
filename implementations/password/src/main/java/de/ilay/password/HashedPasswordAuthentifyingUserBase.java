package de.ilay.password;

public abstract class HashedPasswordAuthentifyingUserBase<CREDENTIALS extends PasswordCredentials> implements HashedPasswordAuthentifyingUser<CREDENTIALS> {

    protected abstract String hashPassword(String password);
    protected abstract boolean passwordAppliesToConventions(String password);

    public void setPassword(String password){
        if(password == null) throw new NullPointerException();
        if(!passwordAppliesToConventions(password)) throw new IllegalArgumentException();

        setPasswordHash(hashPassword(password));
    }
}
