/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class BaseDatos {
    private String name;
    private ArrayList<Table> tables = new ArrayList<Table>();

    public BaseDatos(String nameString) {
        this.name = nameString;
    }
    
    
    private boolean validName(String pname){
        boolean valid = true;
        if(7 > pname.length() || pname.length() > 16){
            return false;
        }
        for(char c : pname.toCharArray()){
            if(Character.isDigit(c)){
                valid = false;
                break;
            }
        }
        return valid;
    }
    public Table createTable(String pname){
        Table table = null;
        if(validName(pname)){
            table = new Table(pname);
        }
        return table;
    }
    
    public boolean addTable(Table table){
        if (table == null) {
            return false;
        }else{
        if(alreadyExists(table.getName()) || !table.minSize()){
            return false;
        }else{
        tables.add(table);
        return true;
        }
        }
    }
    
    public boolean changeTableName(String tableName, String newName){
        Table table = selecTable(tableName);
        if(table != null && !alreadyExists(newName)){
            return table.changeName(newName);
        }else{
            return false;
        }
    }
    
    private boolean alreadyExists(String pname){
        for(Table t : tables){
            if(pname.equals(t.getName())){
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteTable(String pname){
        boolean deleted = false;
        Table table = selecTable(pname);
        if(table != null && table.isEmpty()){
            tables.remove(table);
            deleted = true;
        }
        return deleted;
    }
    
    private Table selecTable(String pname){
        Table table;
        table = null;
        
        for(Table t : tables){
            if(t.getName().equals(pname)){
                table = t;
                break;
            }
        }
        
        return table;
    }
    
    
    
    public boolean addTableField(String tablename, String fname, String type, boolean pkey){
        Table table = selecTable(tablename);
        if(table == null){
            return false;
        }else{
            return table.addField(fname, pkey, type);
        }
    }
    
        public boolean addTableField(Table table, String fname, String type, boolean pkey){
            return table.addField(fname, pkey, type);
    }
    
    
    public boolean addRegistry(String pnameString, String values_string){
        Table table = selecTable(pnameString);
        if(table == null){
            return false;
        }else{
            ArrayList<String> values = convertToArray(values_string);
            return table.addRegistry(values);
        }
    }
    
    

    private ArrayList<String> convertToArray(String values_string) {
        ArrayList<String> array = new ArrayList<>();
        String[] a = values_string.split(",");
        for(String s : a){
            array.add(s);
        }
        return array;
    }
    
}
