package de.ilay.groups;

public abstract class GroupPermission<GROUP> {

    GroupPermission(){
    }

    public static <GROUP> GroupPermission<GROUP> of(GROUP group) {
        return new OneGroupPermission<GROUP>(group);
    }

    public static <GROUP> GroupPermission<GROUP> ofAll(GROUP... groups) {
        return new AllGroupsPermission<GROUP>(groups);
    }

    public static <GROUP> GroupPermission<GROUP> ofAny(GROUP... groups) {
        return new AnyGroupsPermission<GROUP>(groups);
    }

    public abstract boolean isGrantedFor(GroupAwareUser<GROUP> user);
}
