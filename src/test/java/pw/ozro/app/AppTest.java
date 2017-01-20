package pw.ozro.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() throws SQLException
    {
        Connection c = fetchConnection();

        // ensure required tables exist
        new EnumerationValueRepository(c);
        new PermissionRepository(c);
        new UserRepository(c);

        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public static Connection fetchConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
    }

    public void testConnection() throws SQLException {
        fetchConnection();
    }

    public void testCreateEnumerationValue() throws SQLException {
        Connection c = fetchConnection();
        EnumerationValue entity = new EnumerationValue( 42, "forty-two", "The Ultimate Answer.", "answer" );
        EnumerationValueRepository repo = new EnumerationValueRepository(c);

        (new UnitOfWork(c)).scheduleCreate(entity, repo).store().commit();
        assertTrue( repo.count() > 0 );
    }

    public void testCreateUser() throws SQLException {
        Connection c = fetchConnection();

        User entity = new User( "JohnDoe", "p4$Sw0rD" );
        UserRepository repo = new UserRepository(c);

        (new UnitOfWork(c)).scheduleCreate(entity, repo).store().commit();
        assertTrue( repo.count() > 0 );
    }

    public void testClearUsers() throws SQLException {
        Connection c = fetchConnection();

        UserRepository repo = new UserRepository(c);
        repo.clear();

        assertTrue( repo.count() == 0 );
    }

    public void testCreateAndAssignPermissionToRole() throws Exception, SQLException {
        Connection c = fetchConnection();

        EnumerationValue enum_role = new EnumerationValue( 1, "the_role", "Role", "role" );
        EnumerationValue enum_permission = new EnumerationValue( 2, "the_perm", "Permission", "permission" );

        EnumerationValueRepository repo = new EnumerationValueRepository(c);

        UnitOfWork unit = new UnitOfWork(c);
        unit.scheduleCreate(enum_role, repo)
            .scheduleCreate(enum_permission, repo)
            .store()
            .commit();

        // check if enums were created
        assertTrue( repo.count() > 0 );

        // check if IDs are different; the enums must be distinguishable
        assertTrue( enum_role.id() != enum_permission.id() );

        // assign permission to role...
        Permission perm = new Permission( enum_role.id(), enum_permission.id() );
        PermissionRepository perm_repo = new PermissionRepository(c);
        // ...and store the mapping in database
        unit.scheduleCreate(perm, perm_repo).store().commit();

        // assert that at least one permission has been created
        assertTrue( perm_repo.count() > 0 );

        // this will make the test fail if the mapping has not been created
        perm_repo.withId( perm.id() );
    }
}
