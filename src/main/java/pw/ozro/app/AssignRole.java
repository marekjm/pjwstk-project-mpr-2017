package pw.ozro.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AssignRole {
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

        (new UnitOfWork(c)).scheduleCreate(
            new Role(
                (new EnumerationValueRepository(c)).withId(Integer.parseInt(args[0])),
                (new UserRepository(c)).withId(Integer.parseInt(args[1]))
            ),
            new RoleRepository(c)
        ).store().commit();
    }
}
