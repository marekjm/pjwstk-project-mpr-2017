package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;


import pw.ozro.app.Repository;
import pw.ozro.app.User;


public class UserRepository extends Repository<User> {
    UserRepository(Connection c) throws SQLException {
        super(c);
    }

	@Override
    public String table() { return "t_sys_users"; }

	@Override
	protected String queryCreateTable() {
		return ("create table t_sys_users ("
				+ "id BIGINT GENERATED BY DEFAULT AS IDENTITY,"
				+ "login VARCHAR(80),"
				+ "password VARCHAR(80),"
				+ ")"
                );
	}
}
