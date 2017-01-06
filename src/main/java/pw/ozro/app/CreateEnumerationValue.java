package pw.ozro.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CreateEnumerationValue {
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
            new EnumerationValue(
                Integer.parseInt(args[0])   // system key
                , args[1]                   // human readable key
                , args[2]                   // value
                , args[3]                   // name
            ),
            new EnumerationValueRepository(c)
        ).store().commit();
    }
}
