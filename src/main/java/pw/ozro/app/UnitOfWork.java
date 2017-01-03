package pw.ozro.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import pw.ozro.app.Entity;

public class UnitOfWork {

    /*
    private Connection connection;
    private Map<Entity, IUnitOfWorkRepository> entities;

    public UnitOfWork(Connection connection) throws SQLException {
        super();
        this.connection = connection;
        this.connection.setAutoCommit(false);
        entities = new LinkedHashMap<Entity, IUnitOfWorkRepository>();
    }

    public void store() throws SQLException {
        for (Entity entity : entities.keySet()) {
            switch (entity.state()) {
                case Entity.EntityState.Deleted:
                    entities.get(entity).persistDelete(entity);
                    break;
                case Entity.EntityState.Modified:
                    entities.get(entity).persistUpdate(entity);
                    break;
                case Entity.EntityState.New:
                    entities.get(entity).persistInsert(entity);
                    break;
                default:
                    break;
            }
        }
        try {
            connection.commit();
            entities.clear();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public void undo() throws SQLException {
        try {
            connection.rollback();
            entities.clear();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void markAsNew(Entity entity, IUnitOfWorkRepository repository) {
        entity.setState(EntityState.New);
        entities.put(entity, repository);

    }

    public void markAsDeleted(Entity entity, IUnitOfWorkRepository repository) {
        entity.setState(EntityState.Deleted);
        entities.put(entity, repository);

    }

    public void markAsChanged(Entity entity, IUnitOfWorkRepository repository) {
        entity.setState(EntityState.Modified);
        entities.put(entity, repository);
    }
    */
}
