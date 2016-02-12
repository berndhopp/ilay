package de.ilay.groups;

public abstract class GroupPermission<GROUP> {

    private GroupPermission(){
    }

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

    private static class OneGroupPermission<GROUP> extends GroupPermission<GROUP>{
        private final GROUP group;

        public OneGroupPermission(GROUP group){
            this.group = group;
        }

        @Override
        public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
            if(user == null) throw new IllegalArgumentException("user cannot be null");
            return user.isInGroup(group);
        }
    }

    private static class AllGroupsPermission<GROUP> extends GroupPermission<GROUP>{
        private final GROUP[] groups;

        public AllGroupsPermission(GROUP[] groups){
            this.groups = groups;
        }

        @Override
        public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
            if(user == null) throw new IllegalArgumentException("user cannot be null");

            for (GROUP group : groups) {
                if(!user.isInGroup(group)){
                    return false;
                }
            }

            return true;
        }
    }

    private static class AnyGroupsPermission<GROUP> extends GroupPermission<GROUP>{
        private final GROUP[] groups;

        public AnyGroupsPermission(GROUP[] groups){
            this.groups = groups;
        }

        @Override
        public boolean isGrantedFor(GroupAwareUser<GROUP> user) {
            if(user == null) throw new IllegalArgumentException("user cannot be null");

            for (GROUP group : groups) {
                if(user.isInGroup(group)){
                    return true;
                }
            }

            return false;
        }
    }

    public abstract boolean isGrantedFor(GroupAwareUser<GROUP> user);
}
