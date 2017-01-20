package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import pw.ozro.app.Entity;


public abstract class Repository<AbstractEntity extends Entity> {
    private Connection _connection;

    Repository(Connection c) throws SQLException {
        _connection = c;
        ensureTableExists();
    }

    protected Connection connection() { return _connection; }

    protected abstract String table();
    protected abstract String queryCreateTable();

    protected abstract String queryCreate();
    private String fullQueryCreate() {
        return ("insert into " + table() + " " + queryCreate());
    }

    protected abstract String queryUpdate();
    private String fullQueryUpdate() {
        return ("update " + table() + " set " + queryUpdate() + " where id = ?");
    }

    public void drop() throws SQLException {
        Statement create_table = _connection.createStatement();
        String q = ("drop table if exists " + table());
        create_table.executeUpdate(q);
    }

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

    protected abstract void bindCreate(PreparedStatement ps, AbstractEntity entity) throws SQLException;
    public void create(AbstractEntity entity) throws SQLException {
        PreparedStatement ps = connection().prepareStatement(fullQueryCreate(), Statement.RETURN_GENERATED_KEYS);
        bindCreate(ps, entity);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            entity.id(rs.getInt(1));
        }
    }

    protected abstract void bindUpdate(PreparedStatement ps, AbstractEntity entity) throws SQLException;
    public void update(AbstractEntity entity) throws SQLException {
        PreparedStatement ps = connection().prepareStatement(fullQueryUpdate());
        bindUpdate(ps, entity);
        ps.executeUpdate();
    }

    public void delete(AbstractEntity entity) throws SQLException {
        String q = ("delete from " + table() + " where id = ?");
        PreparedStatement ps = connection().prepareStatement(q);
        ps.setInt(1, entity.id());
        ps.executeUpdate();
    }

    public int count() throws SQLException {
        String q = ("select count(*) as total from " + table() + ";");
        PreparedStatement ps = connection().prepareStatement(q);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("total");
    }

    public void clear() throws SQLException {
        String q = ("delete from " + table() + ";");
        PreparedStatement ps = connection().prepareStatement(q);
        ps.executeUpdate();
    }

    protected abstract String selectColumns();
    protected abstract AbstractEntity extractSelected(ResultSet rs) throws SQLException;
    public AbstractEntity withId(int id) throws Exception, SQLException {
        String q = ("select " + selectColumns() + " from " + table() + " where id = ?;");
        PreparedStatement ps = connection().prepareStatement(q);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new Exception("no " + table() + " with id " + (new Integer(id)).toString());
        }
        AbstractEntity entity = extractSelected(rs);
        entity.id(id);
        return entity;
    }

    public List<AbstractEntity> all() throws Exception, SQLException {
        String q = ("select id from " + table());
        PreparedStatement ps = connection().prepareStatement(q);
        ResultSet rs = ps.executeQuery();

        List<AbstractEntity> result = new ArrayList<AbstractEntity>();
        while (rs.next()) {
            result.add(withId(rs.getInt("id")));
        }
        return result;
    }
}
