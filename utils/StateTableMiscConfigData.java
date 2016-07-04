/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader.utils;

/**
 *
 * @author Harish
 */
public class StateTableMiscConfigData {
    String name;
    int value;
    
    public StateTableMiscConfigData(String name, int value){
        this.name = name;
        this.value = value;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    
    public String getString()
    {
        return (new StringBuilder().append(name).append(" : ").append(value).toString());
    }
}
