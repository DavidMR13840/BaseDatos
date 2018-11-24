/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

/**
 *
 * @author David
 */
class Field {
    private String name;
    private boolean primaryKey;
    private String type;


    public Field(String name, String type, boolean primaryKey) {
        this.name = name;
        this.primaryKey = primaryKey;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public String getType() {
        return type;
    }

    public boolean equals(Field obj) {
        return this.name.equals(obj.name) && this.type.equals(obj.type);
    }

    @Override
    public String toString() {
        String msg = "";
        msg += name + "(" + type + ")";
        return msg;
    }
    
    

    
    
}
