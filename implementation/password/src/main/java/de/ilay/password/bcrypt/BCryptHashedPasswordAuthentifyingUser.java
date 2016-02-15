package de.ilay.password.bcrypt;

import de.ilay.password.HashedPasswordAuthentifyingUserBase;

public abstract class BCryptHashedPasswordAuthentifyingUser extends HashedPasswordAuthentifyingUserBase {

    @Override
    protected String hashPassword(String password) {
        if (password == null) throw new NullPointerException();
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
