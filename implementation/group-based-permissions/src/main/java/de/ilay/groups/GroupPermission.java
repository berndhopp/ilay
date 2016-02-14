package de.ilay.groups;

public abstract class GroupPermission<GROUP> {

    public static <GROUP> GroupPermission<GROUP> of(GROUP group){
        if(group == null) throw new IllegalArgumentException("group cannot be null");
        return new OneGroupPermission<GROUP>(group);
    }

    public static <GROUP> GroupPermission<GROUP> ofAll(GROUP... groups){
        if(groups == null || groups.length == 0) throw new IllegalArgumentException("groups cannot be null or empty");
        return new AllGroupsPermission<GROUP>(groups);
    }

    public static <GROUP> GroupPermission<GROUP> ofAny(GROUP... groups){
        if(groups == null || groups.length == 0) throw new IllegalArgumentException("groups cannot be null or empty");
        return new AnyGroupsPermission<GROUP>(groups);
    }

    public abstract boolean isGrantedFor(GroupAwareUser<GROUP> user);
}
