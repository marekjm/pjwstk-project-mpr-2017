package pw.ozro.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pw.ozro.app.Entity;
import pw.ozro.app.User;
import pw.ozro.app.Role;
import pw.ozro.app.Permission;
import pw.ozro.app.UserRoles;
import pw.ozro.app.RolesPermissions;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println( "Hello World!" );

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }

        Connection c;
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return;
        }

        Entity.EntityState state = Entity.EntityState.New;
    }
}
