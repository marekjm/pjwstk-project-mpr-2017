package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import pw.ozro.app.Repository;
import pw.ozro.app.EnumerationValue;


public class EnumerationValueRepository extends Repository<EnumerationValue> {
    EnumerationValueRepository(Connection c) throws SQLException {
        super(c);
    }

    @Override
    public String table() { return "t_sys_enums"; }

    @Override
    protected String queryCreateTable() {
        return ("create table t_sys_enums ("
                + "id BIGINT GENERATED BY DEFAULT AS IDENTITY,"
                + "system_key BIGINT,"
                + "human_readable_key VARCHAR(160),"
                + "value VARCHAR(255),"
                + "name VARCHAR(160),"
                + ")"
               );
    }

    @Override
    protected String queryCreate() {
        return "(system_key, human_readable_key, value, name) values (?, ?, ?, ?)";
    }

    @Override
    protected String queryUpdate() {
        return "(system_key, human_readable_key, value, name) = (?, ?, ?, ?)";
    }

    @Override
    protected void bindCreate(PreparedStatement ps, EnumerationValue entity) throws SQLException {
        ps.setInt(1, entity.system_key);
        ps.setString(2, entity.human_readable_key);
        ps.setString(3, entity.value);
        ps.setString(4, entity.name);
    }

    @Override
    protected void bindUpdate(PreparedStatement ps, EnumerationValue entity) throws SQLException {
        bindCreate(ps, entity);
        ps.setInt(5, entity.id());
    }

    @Override
    protected String selectColumns() {
        return "system_key, human_readable_key, value, name";
    }

    @Override
    protected EnumerationValue extractSelected(ResultSet rs) throws SQLException {
        EnumerationValue entity = new EnumerationValue(
            rs.getInt("system_key")
            , rs.getString("human_readable_key")
            , rs.getString("value")
            , rs.getString("name")
        );
        return entity;
    }
}