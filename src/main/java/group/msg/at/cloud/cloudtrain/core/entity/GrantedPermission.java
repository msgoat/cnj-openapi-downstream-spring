package group.msg.at.cloud.cloudtrain.core.entity;

public class GrantedPermission {

    private String permission;

    /**
     * Default constructor for JSON binding only.
     */
    public GrantedPermission() {
    }

    /**
     * Special constructor for application use.
     *
     * @param permission
     */
    public GrantedPermission(Permission permission) {
        this.permission = permission.getPermissionName();
    }

    public String getPermission() {
        return permission;
    }
}
