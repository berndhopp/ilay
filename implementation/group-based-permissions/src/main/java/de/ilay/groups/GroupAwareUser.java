package de.ilay.groups;

public interface GroupAwareUser<GROUP> {
    boolean isInGroup(GROUP group);
}
