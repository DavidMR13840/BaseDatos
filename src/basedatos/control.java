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
public class control {
    
    public static void main(String[] args) {
        BaseDatos bd = new BaseDatos("BaseD");
        Table t = bd.createTable("Student");
        bd.addTableField(t, "Identificacion", "int", true);
        bd.addTableField(t, "StudentName", "String", false);
        bd.addTableField(t, "Identificacion", "int", false);
        if(bd.addTable(t)){
            System.out.println("Table " + t.getName() + " added");
        }else{
            System.out.println("Fallo");
        }
        if(!bd.addTable(t)){
            System.out.println("Ya existe");
        }
        Table table = bd.createTable("Nombrelargo");
        bd.addTableField(table, "Problema", "int", true);
        bd.addTableField(table, "Problema1", "String", false);
        bd.addTableField(table, "Solucion", "String", false);
        bd.addTable(table);
        System.out.println(table.structure);
        if(bd.changeTableName("Nombrelargo", "HolaMundo")){
            System.out.println("Nombre cambio a " + table.getName());
        }else{
            System.out.println("Tabla con mismo nombre ya existe");
        }
        if(bd.deleteTable("HolaMundo")){
            System.out.println("Table deleted");
        }else{
            System.out.println("Fallo");
        }
        if(bd.addRegistry("Student", "123,David")){
            System.out.println("Registro agregado");
        }else{
            System.out.println("Fallo");
        }
        System.out.println(t.registries.get(0).toString());
//        if(bd.){
//            System.out.println("Registro agregado");
//        }else{
//            System.out.println("Fallo");
//        }
//        
        //System.out.println(t.getName());
    }
    
}
