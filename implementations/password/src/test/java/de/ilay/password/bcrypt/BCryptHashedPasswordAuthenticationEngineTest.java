package de.ilay.password.bcrypt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class BCryptHashedPasswordAuthenticationEngineTest {

    public static final String USER_1_PASSWORD = "secret1";
    public static final String USER_2_PASSWORD = "secret2";
    private UserImpl user1;
    private UserImpl user2;
    private BCryptHashedPasswordAuthenticationEngine<Integer, CredentialsImpl, UserImpl> engine;

    @Before
    public void init(){
        user1 = new UserImpl();
        user1.setPassword(USER_1_PASSWORD);

        user2 = new UserImpl();
        user2.setPassword(USER_2_PASSWORD);

        engine = new BCryptHashedPasswordAuthenticationEngine<Integer, CredentialsImpl, UserImpl>() {
            @Override
            protected Optional<UserImpl> getUser(Integer userId) {
                switch (userId){
                    case 1: return Optional.of(user1);
                    case 2: return Optional.of(user2);
                    default: return Optional.empty();
                }
            }
        };
    }
    
    @Test
    public void login_with_correct_credentials_should_return_user(){
        Optional<UserImpl> user1Optional = engine.loadUser(new CredentialsImpl(USER_1_PASSWORD, 1));

        Assert.assertNotNull(user1Optional);
        Assert.assertTrue(user1Optional.isPresent());
        Assert.assertEquals(user1, user1Optional.get());

        Optional<UserImpl> user2Optional = engine.loadUser(new CredentialsImpl(USER_2_PASSWORD, 2));

        Assert.assertNotNull(user2Optional);
        Assert.assertTrue(user2Optional.isPresent());
        Assert.assertEquals(user2, user2Optional.get());
    }

    @Test
    public void login_with_wrong_password_should_return_optional_empty(){

        Optional<UserImpl> user1Optional = engine.loadUser(new CredentialsImpl("foobar", 1));

        Assert.assertNotNull(user1Optional);
        Assert.assertFalse(user1Optional.isPresent());
    }

    @Test
    public void login_with_unknown_user_id_should_return_optional_empty(){
        Optional<UserImpl> user2Optional = engine.loadUser(new CredentialsImpl(USER_2_PASSWORD, 3));

        Assert.assertNotNull(user2Optional);
        Assert.assertFalse(user2Optional.isPresent());
    }
}