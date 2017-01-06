package pw.ozro.app;


import pw.ozro.app.Entity;


public class Permission extends Entity {
    public int role_id;
    public int permission_id;

    Permission(int rid, int pid) {
        role_id = rid;
        permission_id = pid;
    }
    Permission(EnumerationValue rid, EnumerationValue pid) {
        role_id = rid.id();
        permission_id = pid.id();
    }

    public String toString() {
        return (role_id + "." + permission_id);
    }
}
