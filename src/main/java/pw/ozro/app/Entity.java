package pw.ozro.app;


public abstract class Entity {
    public enum EntityState {
        New,
        Modified,
        UnChanged,
        Deleted,
        Unknown
    }

    private int _id;
    private EntityState _state;

    public int id() { return _id; }

    public EntityState state() { return _state; }
    public EntityState state(EntityState _) { return _state = _; }
}
