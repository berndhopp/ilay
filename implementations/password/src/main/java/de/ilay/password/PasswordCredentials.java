package de.ilay.password;

import de.ilay.api.CredentialsWithIdentifier;

public interface PasswordCredentials<USER_IDENTIFIER> extends CredentialsWithIdentifier<USER_IDENTIFIER>{
    String getPassword();
    USER_IDENTIFIER getUserIdentifier();
}
