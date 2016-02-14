package de.ilay.groups;

public class AllGroupsPermission<GROUP> extends GroupPermission<GROUP> {
    private final GROUP[] groups;

    public AllGroupsPermission(GROUP[] groups) {
        this.groups = groups;
    }

    @Override
    public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");

        for (GROUP group : groups) {
            if (!user.isInGroup(group)) {
                return false;
            }
        }

        return true;
    }
}
