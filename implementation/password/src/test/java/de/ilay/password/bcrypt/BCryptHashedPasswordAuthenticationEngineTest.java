package de.ilay.password.bcrypt;

import de.ilay.sample.exception.AuthenticationException;
import de.ilay.sample.exception.UserNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BCryptHashedPasswordAuthenticationEngineTest {

    public static final String USER_1_PASSWORD = "secret1";
    public static final String USER_2_PASSWORD = "secret2";
    private UserImpl user1;
    private UserImpl user2;
    private BCryptHashedPasswordAuthenticationEngine<Integer, CredentialsImpl, UserImpl> engine;

    @Before
    public void init() {
        user1 = new UserImpl();
        user1.setPassword(USER_1_PASSWORD);

        user2 = new UserImpl();
        user2.setPassword(USER_2_PASSWORD);

        engine = new BCryptHashedPasswordAuthenticationEngine<Integer, CredentialsImpl, UserImpl>() {
            @Override
            protected UserImpl getUser(Integer userId) throws UserNotFoundException {
                switch (userId) {
                    case 1:
                        return user1;
                    case 2:
                        return user2;
                    default:
                        throw new UserNotFoundException();
                }
            }
        };
    }

    @Test
    public void login_with_correct_credentials_should_return_user() throws UserNotFoundException, AuthenticationException {
        UserImpl user1Optional = engine.authenticateUser(new CredentialsImpl(USER_1_PASSWORD, 1));

        Assert.assertNotNull(user1Optional);
        Assert.assertEquals(user1, user1Optional);

        UserImpl user2Optional = engine.authenticateUser(new CredentialsImpl(USER_2_PASSWORD, 2));

        Assert.assertNotNull(user2Optional);
        Assert.assertEquals(user2, user2Optional);
    }

    @Test(expected = AuthenticationException.class)
    public void login_with_wrong_password_should_return_optional_empty() throws UserNotFoundException, AuthenticationException {

        UserImpl user1Optional = engine.authenticateUser(new CredentialsImpl("foobar", 1));

        Assert.assertNotNull(user1Optional);
    }

    @Test(expected = UserNotFoundException.class)
    public void login_with_unknown_user_id_should_return_optional_empty() throws UserNotFoundException, AuthenticationException {
        UserImpl user2Optional = engine.authenticateUser(new CredentialsImpl(USER_2_PASSWORD, 3));
    }
}