package de.ilay.password.bcrypt;

import de.ilay.password.HashedPasswordAuthentifyingUserBase;
import de.ilay.password.PasswordCredentials;

import static de.ilay.password.bcrypt.BCrypt.gensalt;
import static de.ilay.password.bcrypt.BCrypt.hashpw;

public abstract class BCryptHashedPasswordAuthentifyingUser<CREDENTIALS extends PasswordCredentials> extends HashedPasswordAuthentifyingUserBase<CREDENTIALS> {

    @Override
    protected String hashPassword(String password) {
        if(password == null) throw new NullPointerException();
        return hashpw(password, gensalt());
    }
}
