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
public class App {
    public static void main(String[] args) throws Exception, SQLException {
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

        UserRepository ur = new UserRepository(c);
        ur.clear();
        System.out.println(ur.count());
        /*
        User user = new User("zz", "top");
        System.out.println(user);
        System.out.println(ur.count());

        User user_by_id = ur.withId(14);
        if (user_by_id.password.equals("top")) {
            user_by_id.password = "t0p";
        } else {
            user_by_id.password = "top";
        }
        System.out.println(user_by_id);

        User user_to_delete = ur.withId(15);
        */

        RoleRepository rr = new RoleRepository(c);
        rr.clear();
        System.out.println(rr.count());
        /*
        Role role = new Role("test_role");
        System.out.println(role);
        System.out.println(rr.count());

        uow.scheduleCreate(role, rr);
        */

        PermissionRepository pr = new PermissionRepository(c);
        pr.clear();
        System.out.println(pr.count());
        // Permission permission = new Permission("test_permission");

        EnumerationValueRepository evr = new EnumerationValueRepository(c);
        EnumerationValue a_role = new EnumerationValue(1, "a_role", "A role.", "role");
        EnumerationValue a_perm = new EnumerationValue(1, "a_perm", "A permission.", "permission");


        // UnitOfWork uow = new UnitOfWork(c);
        // uow.scheduleCreate(user, ur);
        // uow.scheduleUpdate(user_by_id, ur);
        // uow.scheduleDelete(user_to_delete, ur);
        // uow.store();
        // uow.commit();
    }
}
