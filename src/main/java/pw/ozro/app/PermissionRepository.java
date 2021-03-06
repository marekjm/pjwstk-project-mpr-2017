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
                + "role_id BIGINT,"
                + "permission_id BIGINT,"
                + ")"
               );
    }

    @Override
    protected String queryCreate() {
        return "(role_id, permission_id) values (?, ?)";
    }

    @Override
    protected String queryUpdate() {
        return "(role_id, permission_id) values (?, ?)";
    }

    @Override
    protected void bindCreate(PreparedStatement ps, Permission entity) throws SQLException {
        ps.setInt(1, entity.role_id);
        ps.setInt(2, entity.permission_id);
    }

    @Override
    protected void bindUpdate(PreparedStatement ps, Permission entity) throws SQLException {
        bindCreate(ps, entity);
        ps.setInt(3, entity.id());
    }

    @Override
    protected String selectColumns() {
        return "role_id, permission_id";
    }

    @Override
    protected Permission extractSelected(ResultSet rs) throws SQLException {
        Permission entity = new Permission(
            rs.getInt("role_id")
            , rs.getInt("permission_id")
        );
        return entity;
    }
}
