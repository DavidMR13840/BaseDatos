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
public class Value {
    private String value;
    private Field field;

    public Value(String value, Field field) {
        this.value = value;
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public Field getField() {
        return field;
    }
    
    public boolean isValid(Field f){
        return field.equals(f);
    }
    
    public String toString(){
        return  value;
    }

    
}
 