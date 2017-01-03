package pw.ozro.app;


public abstract class Entity {
    public enum EntityState {
        New,
        Modified,
        UnChanged,
        Deleted,
        Unknown
    }

    protected Integer id;
    protected EntityState state;
}
