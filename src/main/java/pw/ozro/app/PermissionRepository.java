package pw.ozro.app;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import pw.ozro.app.Repository;
import pw.ozro.app.Permission;


public class PermissionRepository extends Repository<Permission> {
    PermissionRepository(Connection c) throws SQLException {
        super(c);
    }

    @Override
    public String table() { return "t_sys_permissions"; }

    @Override
    protected String queryCreateTable() {
        return ("create table t_sys_permissions ("
                + "id BIGINT GENERATED BY DEFAULT AS IDENTITY,"
                + "name VARCHAR(80),"
                + ")"
               );
    }

    @Override
    protected String queryCreate() {
        return "(name) values (?)";
    }

    @Override
    protected String queryUpdate() {
        return "(name) = (?)";
    }

    @Override
    protected void bindCreate(PreparedStatement ps, Permission entity) throws SQLException {
        ps.setString(1, entity.name);
    }

    @Override
    protected void bindUpdate(PreparedStatement ps, Permission entity) throws SQLException {
        bindCreate(ps, entity);
        ps.setInt(2, entity.id());
    }

    @Override
    protected String selectColumns() {
        return "name";
    }

    @Override
    protected Permission extractSelected(ResultSet rs) throws SQLException {
        Permission entity = new Permission(rs.getString("name"));
        return entity;
    }
}