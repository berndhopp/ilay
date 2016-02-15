package de.ilay.groups;

class AnyGroupsPermission<GROUP> extends GroupPermission<GROUP> {
    private final GROUP[] groups;

    public AnyGroupsPermission(GROUP[] groups) {
        if (groups == null || groups.length == 0)
            throw new IllegalArgumentException("groups cannot be null or empty");
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
