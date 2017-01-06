package pw.ozro.app;


import pw.ozro.app.Entity;


public class EnumerationValue extends Entity {
    public int system_key;
    public String human_readable_key;
    public String value;
    public String name;

    EnumerationValue(int sk, String hrk, String v, String n) {
        system_key = sk;
        human_readable_key = hrk;
        value = v;
        name = n;
    }

    public String toString() {
        return ("[" + id() + "]" + human_readable_key + "=" + value);
    }
}
