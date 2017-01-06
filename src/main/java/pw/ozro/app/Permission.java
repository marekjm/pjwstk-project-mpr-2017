package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;


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

    public String report(Connection connection) throws Exception, SQLException {
        return (
            "permission " + id() + ": "
            + permission_id
            + " (" + (new EnumerationValueRepository(connection)).withId(permission_id) + ")"
            + " to "
            + role_id
            + " (" + (new EnumerationValueRepository(connection)).withId(role_id) + ")"
        );
    }
}
