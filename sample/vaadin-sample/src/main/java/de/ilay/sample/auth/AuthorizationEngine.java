package de.ilay.sample.auth;

import de.ilay.groups.GroupBasedAuthorizationEngine;
import de.ilay.groups.GroupPermission;
import com.google.inject.Singleton;

@Singleton
public class AuthorizationEngine extends GroupBasedAuthorizationEngine<SampleGroup, GroupPermission<SampleGroup>, User> {
}
