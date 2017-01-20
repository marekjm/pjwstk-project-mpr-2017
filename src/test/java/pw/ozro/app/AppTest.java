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
    public static Test suite()
    {
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
}
