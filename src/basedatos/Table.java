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
public class Table {
    
    private String name;
    private ArrayList<Field> fields = new ArrayList<Field>();
    public ArrayList<Registry> registries = new ArrayList<Registry>();
    private String structure;
    private Field primField;    
    
    public Table(String pname){
        setName(pname);
    }
    
    public Table(){}
    
    public boolean minSize(){
        return fields.size() >= 2;
    }
    
    private boolean isInFields(Field f) {
        boolean sameField = false;
        for(Field field : fields){
            if(field.equals(f)){
                sameField = true;
                break;
            }
        }
        return sameField;
    }
    
    public String getStructure(){
        return this.structure;
    }
    
    public boolean addField(String pname, boolean pKey, String type){
        if(validName(pname)){
            Field f = new Field(pname, type, pKey);
        if(!isInFields(f)){
        fields.add(f);
        setPrimKey(f);
        setStructure();
        return true;
        }else{return false;}
        }else{
            return false;
        }        
    }
    
    private void setPrimKey(Field f){
        if(f.isPrimaryKey()){
            primField = f;
        }
    }
    
    public boolean isEmpty(){
        return  registries.isEmpty();
    }

    
    private boolean setName(String pname){
        if(validName(pname)){
            this.name = pname;
            return true;
        }else{
            return false;
        }
    }
    private void setStructure() {
        structure = "";
        for(Field f: fields){
            structure += f.toString() + ", ";
        }
    }
    
    public boolean validName(String pname){
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
    
    public boolean changeName(String pname){
        return setName(pname);
    }

    public String getName() {
        return this.name;
    }
    
    public boolean addRegistry(Registry pregistry){
        if(pregistry.isValid(fields)){
            registries.add(pregistry);
            return true;
        }else{
            return false;
        }
    }

    public boolean addRegistry(ArrayList<String> strings) {
        Registry registry = createRegistry(strings);
        if(registry == null){
            return false;
        }else{
            return addRegistry(registry);
        }  
    }
    
    private Registry createRegistry(ArrayList<String> strings){
       Registry reg = new Registry();
        int i = 0;
        for(String s : strings){
            if(getType(s).equals(fields.get(i).getType())){
                Value v = new Value(s, fields.get(i));
                reg.addValue(v);
                i++;
            }else{
                reg = null;
                break;
            }
        }
        return reg;
    }
    
    private String getType(String pString){
        String type;
        if(isStringClass(Integer.class, pString)){
            type = "int";
        }else if(isStringClass(Boolean.class, pString)){
            type = "boolean";
        }else if(isStringClass(Double.class, pString)){
            type = "double";
        }else if(isStringClass(Float.class, pString)){
            type = "float";
        }else{
            type = "String";}
        return type;
    }
    
    boolean isStringClass (Class clazz, String string) {

    try {
        if (Integer.class == clazz) {
           Integer.valueOf(string);
        } else if (Boolean.class == clazz) {
            if ("true".equals(string) || "false".equals(string)) {
                return true;
            }else{
                return false;
            }
        } else if(Double.class == clazz) {
            Double.valueOf(string);
        } else if(Float.class == clazz) {
            Float.valueOf(string);
        }
    } catch (Exception e) {
        return false;
    }

    return true;
}
}
