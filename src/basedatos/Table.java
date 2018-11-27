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
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Registry> registries = new ArrayList<>();
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
    
    public ArrayList<Field> getFields(){
        return  this.fields;
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
        }else if(isStringClass(Double.class, pString)){
            type = "double";
        }else if(isStringClass(Float.class, pString)){
            type = "float";
        }else if(isStringClass(Boolean.class, pString)){
            type = "boolean";
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
    
    public ArrayList<Field> getFields(ArrayList<String> strings){
        ArrayList<Field> fs = new ArrayList<>();
        for(String s : strings){
            fs.add(getField(s));
        }
        return fs;
    }
    
    public ArrayList<String> showRegistries(ArrayList<String> fieldsnames, String opString, String cField, String condition){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Field> fs = getFields(fieldsnames);
        int op = detOperators(opString);
        Field f = getField(cField);
        if(!f.getType().equals(getType(condition))){
            return null;
        }else{
            for(Registry r: registries){
                if(!r.toString(f, condition, fs,op).equals("")){
                     res.add(r.toString(f, condition, fs,op));
                }
            }
            return res;
        }
        
    }
    
    private Field getField(String pname){
        Field f = null;
        for(Field field: fields){
            if(field.getName().equals(pname)){
               f = field;
               break;
            }
        }
        return f;
    }
        private int detOperators(String opsString){
        int op = 0;
        switch(opsString){
            case "==":
                op = 1;
                break;
            case "!=":
                op = 2;
                break;
            case ">":
                op = 3;
                break;
            case "<":
                op = 4;
                break;
            default:
                break;
        }
        return op;
    }

    public int deleteRegistries(String cField, String opString, String condition) {
        int op = detOperators(opString);
        Field f = getField(cField);
        int cant = 0;
        if(!f.getType().equals(getType(condition))){
            return -1;
        }else{
            if(condition.equals("")){
                cant = registries.size();
                registries.clear();
                return cant;
            }else{
                cant = deleteReg_aux(f, op, condition);
            }
            return cant;
        }
    }
           
    private int deleteReg_aux(Field field, int op, String condition){
        int cont = 0;
        ArrayList<Registry> rs = new ArrayList<>();
        rs.addAll(registries);
        for(Registry r : registries){
            Value v = r.getValue(field);
            if(getType(v.getValue()).equals("String") || getType(v.getValue()).equals("boolean")){
                switch (op) {
           case 1:
               if(v.getValue().equals(condition)){
                   rs.remove(r);
                   cont++;
               }  break;
           case 2:
               if(!v.getValue().equals(condition)){
                   rs.remove(r);
                   cont++;
               }  break;
           case 3:
               if(v.getValue().compareToIgnoreCase(condition) > 0){
                   rs.remove(r);
                   cont++;
               }  break;
           case 4:
               if(v.getValue().compareToIgnoreCase(condition) < 0){
                   rs.remove(r);
                   cont++;
               }  break;
           default:
               rs.remove(r);
               cont++;
               break;
       }
            }else{               
                if(getType(v.getValue()).equals("double")){
                   double valField = Double.parseDouble(v.getValue());
                    double valCond = Double.parseDouble(condition);
                    switch (op) {
                    case 1:
                        if(valField == valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 2:
                        if(valField != valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 3:
                        if(valField > valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 4:
                        if(valField < valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    default:
                        rs.remove(r);
                            cont++;
                        break;
                }
                }else if(getType(v.getValue()).equals("int")){
                    int valField = Integer.parseInt(v.getValue());
                    int valCond = Integer.parseInt(condition);
                    switch (op) {
                    case 1:
                        if(valField == valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 2:
                        if(valField != valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 3:
                        if(valField > valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 4:
                        if(valField < valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    default:
                        rs.remove(r);
                            cont++;
                        break;
                }
                }else if(getType(v.getValue()).equals("float")){
                    float valField = Float.parseFloat(v.getValue());
                    float valCond = Float.parseFloat(condition);
                    switch (op) {
                    case 1:
                        if(valField == valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 2:
                        if(valField != valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 3:
                        if(valField > valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    case 4:
                        if(valField < valCond){
                            rs.remove(r);
                            cont++;
                        }  break;
                    default:
                        rs.remove(r);
                            cont++;
                        break;
                }
                }
                   
            }
        }
        registries = rs;
        return cont;
    }
    
}
