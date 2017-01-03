package pw.ozro.app;

import pw.ozro.app.Entity;


public class EnumerationValue extends Entity {
    /*
     *  Informacje o rolach i uprawnieniach zostały
     *  osłownikowane oraz będą się zawierać w tabeli 't_sys_enums'.
     */
    public String table() { return "t_sys_enums"; }
}
