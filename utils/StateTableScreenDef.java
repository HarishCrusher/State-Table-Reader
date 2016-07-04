/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader.utils;

import java.util.ArrayList;

/**
 *
 * @author Harish
 */
public class StateTableScreenDef {
    String name;
    ArrayList<String> mnemonic;
    char[][] buffer = new char[16][32];
    boolean bufferGenerated = false;
    
    public StateTableScreenDef(String name)
    {
        this.name = name;
        mnemonic = new ArrayList<String>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void addMnemonic(String m)
    {
        mnemonic.add(m);
    }
}
