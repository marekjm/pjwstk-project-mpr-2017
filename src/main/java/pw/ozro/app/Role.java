package pw.ozro.app;


import pw.ozro.app.Entity;


public class Role extends Entity {
    public int role_id;
    public int user_id;

    Role(int rid, int uid) {
        role_id = rid;
        user_id = uid;
    }

    public String toString() {
        return (role_id + ":" + user_id);
    }
}
