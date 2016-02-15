package de.ilay.groups;

class AllGroupsPermission<GROUP> extends GroupPermission<GROUP> {
    private final GROUP[] groups;

    public AllGroupsPermission(GROUP[] groups) {
        if (groups == null || groups.length < 2)
            throw new IllegalArgumentException("groups cannot be null and must have more than one entry");
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
