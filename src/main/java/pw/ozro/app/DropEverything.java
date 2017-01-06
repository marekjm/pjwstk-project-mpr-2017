package pw.ozro.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import pw.ozro.app.Entity;
import pw.ozro.app.User;
import pw.ozro.app.UserRepository;
import pw.ozro.app.UnitOfWork;


/**
 * Hello world!
 *
 */
public class DropEverything {
    public static void main(String[] args) throws Exception, SQLException {
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

        (new UserRepository(c)).drop();
        (new RoleRepository(c)).drop();
        (new PermissionRepository(c)).drop();
        (new EnumerationValueRepository(c)).drop();
    }
}
