package de.ilay.sample.auth;

import com.google.inject.Singleton;

import de.ilay.groups.GroupBasedAuthorizationEngine;
import de.ilay.groups.GroupPermission;

@Singleton
public class AuthorizationEngine extends GroupBasedAuthorizationEngine<SampleGroup, User> {
}
