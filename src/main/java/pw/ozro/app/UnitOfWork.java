package pw.ozro.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import pw.ozro.app.Entity;
import pw.ozro.app.Repository;


public class UnitOfWork {
    private Connection _connection;
    private Map<Entity, Repository> _entities;

    public UnitOfWork(Connection c) throws SQLException {
        super();
        _connection = c;
        _connection.setAutoCommit(false);
        _entities = new LinkedHashMap<Entity, Repository>();
    }

    public UnitOfWork scheduleCreate(Entity entity, Repository repository) {
        entity.state(Entity.EntityState.New);
        _entities.put(entity, repository);
        return this;
    }

    public UnitOfWork scheduleUpdate(Entity entity, Repository repository) {
        entity.state(Entity.EntityState.Modified);
        _entities.put(entity, repository);
        return this;
    }

    public UnitOfWork scheduleDelete(Entity entity, Repository repository) {
        entity.state(Entity.EntityState.Deleted);
        _entities.put(entity, repository);
        return this;
    }

    public void clear() {
        _entities.clear();
    }

    public UnitOfWork store() throws SQLException {
        for (Entity entity : _entities.keySet()) {
            switch (entity.state()) {
                case New:
                    _entities.get(entity).create(entity);
                    break;
                case Modified:
                    _entities.get(entity).update(entity);
                    break;
                case Deleted:
                    _entities.get(entity).delete(entity);
                    break;
                default:
                    System.out.println("Ouch! Not implemented!");
            }
        }
        return this;
    }

    public UnitOfWork commit() throws SQLException {
        _connection.commit();
        _entities.clear();
        return this;
    }

    public void rollback() throws SQLException {
        _connection.rollback();
    }
}
