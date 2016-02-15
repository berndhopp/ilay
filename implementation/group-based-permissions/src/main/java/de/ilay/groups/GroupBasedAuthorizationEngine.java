package de.ilay.groups;

import de.ilay.sample.api.AuthorizationEngine;

public class GroupBasedAuthorizationEngine<GROUP, USER extends GroupAwareUser<GROUP>>
        implements AuthorizationEngine<GroupPermission<GROUP>, USER> {

    public boolean hasPermission(USER user, GroupPermission<GROUP> permission) {
        if(user == null) throw new IllegalArgumentException("user cannot be null");
        if(permission == null) throw new IllegalArgumentException("permission cannot be null");

        return permission.isGrantedFor(user);
    }

    public boolean isPermittedWithoutLogin(GroupPermission<GROUP> permission) {
        return false;
    }
}
