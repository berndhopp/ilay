package de.ilay.groups;

class OneGroupPermission<GROUP> extends GroupPermission<GROUP> {
    private final GROUP group;

    public OneGroupPermission(GROUP group) {
        if (group == null) throw new IllegalArgumentException("group cannot be null");
        this.group = group;
    }

    @Override
    public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        return user.isInGroup(group);
    }
}
