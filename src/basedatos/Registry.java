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
class Registry {
   public ArrayList<Value> values = new ArrayList<Value>();

    public Registry(ArrayList<Value> pregistry) {
        this.values = pregistry;
    }

    Registry() {
        
    }
    
    public boolean isValid(ArrayList<Field> structure){
        boolean correctStructure = true;
        int cont = 0;
        if(values.size() != structure.size()){
            return false;
        }
        while(cont < structure.size()){
            if(!values.get(cont).isValid(structure.get(cont))){
                correctStructure = false;
                break;
            }
            cont++;
        }
        return correctStructure;
    }
    
    public void addValue(Value v){
        values.add(v);
    }
    
        private Value getValue(Field f){
        Value res = null;
        for(Value v: values){
            if(v.getField().equals(f)){
                res =  v;
                break;
            }
        }
        return res;
    }
        
    public String toString(Field cField, String condition, ArrayList<Field> f, int op){
        String msg = "";
        Value v = getValue(cField);
        if(condition.equals("")){
            return toString_aux(f);
        }else if ("double".equals(v.getField().getType())){
       double val = Double.parseDouble(v.getValue());
       double con = Double.parseDouble(condition);
       switch (op) {
           case 1:
               if(val == con){
                   msg = toString_aux(f);
               }  break;
           case 2:
               if(val != con){
                   msg = toString_aux(f);
               }  break;
           case 3:
               if(val > con){
                   msg = toString_aux(f);
               }  break;
           case 4:
               if(val < con){
                   msg = toString_aux(f);
               }  break;
           default:
               msg = toString();
               break;
       }
            return msg;
        }else if ("int".equals(v.getField().getType())){
       int val = Integer.parseInt(v.getValue());
       int con = Integer.parseInt(condition);
       switch (op) {
           case 1:
               if(val == con){
                   msg = toString_aux(f);
               }  break;
           case 2:
               if(val != con){
                   msg = toString_aux(f);
               }  break;
           case 3:
               if(val > con){
                   msg = toString_aux(f);
               }  break;
           case 4:
               if(val < con){
                   msg = toString_aux(f);
               }  break;
           default:
               msg = toString();
               break;
       }
            return msg;
        }else if ("int".equals(v.getField().getType())){
            float val = Float.parseFloat(v.getValue());
       float con = Float.parseFloat(condition);
       
       switch (op) {
           case 1:
               if(val == con){
                   msg = toString_aux(f);
               }  break;
           case 2:
               if(val != con){
                   msg = toString_aux(f);
               }  break;
           case 3:
               if(val > con){
                   msg = toString_aux(f);
               }  break;
           case 4:
               if(val < con){
                   msg = toString_aux(f);
               }  break;
           default:
               msg = toString();
               break;
       }
            return msg;
        }
       switch (op) {
           case 1:
               if(v.getValue().equals(condition)){
                   msg = toString_aux(f);
               }  break;
           case 2:
               if(!v.getValue().equals(condition)){
                   msg = toString_aux(f);
               }  break;
           case 3:
               if(v.getValue().compareToIgnoreCase(condition) > 0){
                   msg = toString_aux(f);
               }  break;
           case 4:
               if(v.getValue().compareToIgnoreCase(condition) < 0){
                   msg = toString_aux(f);
               }  break;
           default:
               msg = toString();
               break;
       }
            return msg;
        }
        
        
    
   @Override
    public String toString(){
        String msg = "";
        for(Value v : values){
            msg+= v.toString() + ",";
        }
        return msg;
    }
    public String toString_aux( ArrayList<Field> f){
        String msg = "";
        if(f.get(0) == null){
            return toString();
        }else{
        for(Value v: values){
            for(Field field : f){
                if (v.getField().equals(field)) {
                    msg += v.toString() + ", ";
                }
            }
        }
        
        }   
        return msg;
    }
    
    

   
   
    
}
