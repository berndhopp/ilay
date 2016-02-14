package de.ilay.groups;

public class OneGroupPermission<GROUP> extends GroupPermission<GROUP> {
    private final GROUP group;

    public OneGroupPermission(GROUP group) {
        this.group = group;
    }

    @Override
    public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        return user.isInGroup(group);
    }
}
