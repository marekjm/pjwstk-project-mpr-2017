package pw.ozro.app;


import pw.ozro.app.Entity;


public class Role extends Entity {
    public String name;

    Role(String n) {
        name = n;
    }

    public String toString() {
        return name;
    }
}
