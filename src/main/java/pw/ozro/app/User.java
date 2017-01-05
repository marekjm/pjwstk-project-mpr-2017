package pw.ozro.app;


import pw.ozro.app.Entity;


public class User extends Entity {
    public String login;
    public String password;

    User(String l, String p) {
        login = l;
        password = p;
    }

    public String toString() {
        return (login + ":" + password);
    }
}
