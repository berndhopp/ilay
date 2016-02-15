package de.ilay.groups;

import de.ilay.sample.api.AuthorizationEngine;

public class GroupBasedAuthorizationEngine<GROUP, PERMISSION extends GroupPermission<GROUP>, USER extends GroupAwareUser<GROUP>>
        implements AuthorizationEngine<PERMISSION, USER> {

    public boolean hasPermission(USER user, PERMISSION permission) {
        return permission.isGrantedFor(user);
    }

    public boolean isPermittedWithoutLogin(PERMISSION permission) {
        return false;
    }
}
