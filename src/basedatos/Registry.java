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
    public String toString(){
        String msg = "";
        for(Value v: values){
            msg += v.toString() + " ";
        }
        return msg;
    }
   
   
    
}
