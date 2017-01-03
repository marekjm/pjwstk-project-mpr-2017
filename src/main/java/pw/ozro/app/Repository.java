package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public abstract class Repository {
    private Connection _connection;

    Repository(Connection c) throws SQLException {
        _connection = c;
        ensureTableExists();
    }

    protected abstract String table();
    protected abstract String queryCreateTable();

    /*
    protected abstract String queryCreate();
    protected abstract String queryRead();
    protected abstract String queryUpdate();
    protected abstract String queryDelete();
    */

    private void ensureTableExists() throws SQLException {
		boolean tableExists = false;
		ResultSet rs = _connection.getMetaData().getTables(null, null, null, null);
		while (rs.next()) {
			if (table().equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
				tableExists = true;
				break;
			}
		}

		if (!tableExists) {
            System.out.println("table " + table() + " does not exist, creating");
            Statement create_table = _connection.createStatement();
			create_table.executeUpdate(queryCreateTable());
        } else {
            System.out.println("table " + table() + " exists");
        }
    }
}
