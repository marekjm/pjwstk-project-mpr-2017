package pw.ozro.app;


import pw.ozro.app.Entity;


public class Permission extends Entity {
    public String name;

    Permission(String n) {
        name = n;
    }

    public String toString() {
        return name;
    }
}
