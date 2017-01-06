package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;


import pw.ozro.app.Entity;


public class Role extends Entity {
    public int role_id;
    public int user_id;

    Role(int rid, int uid) {
        role_id = rid;
        user_id = uid;
    }
    Role(EnumerationValue rid, User uid) {
        role_id = rid.id();
        user_id = uid.id();
    }

    public String toString() {
        return (role_id + ":" + user_id);
    }

    public String report(Connection connection) throws Exception, SQLException {
        return (
            "role " + id() + ": "
            + role_id + " to " + user_id
            + " (" + (new UserRepository(connection)).withId(user_id).login + ")"
        );
    }
}
