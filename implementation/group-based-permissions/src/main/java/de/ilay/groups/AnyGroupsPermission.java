package de.ilay.groups;

public class AnyGroupsPermission<GROUP> extends GroupPermission<GROUP> {
    private final GROUP[] groups;

    public AnyGroupsPermission(GROUP[] groups) {
        this.groups = groups;
    }

    @Override
    public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");

        for (GROUP group : groups) {
            if (user.isInGroup(group)) {
                return true;
            }
        }

        return false;
    }
}
