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
public class StateTableOarDef {
    String activatedKey;
    ArrayList<String> mnemonic;
    
    public StateTableOarDef(String key)
    {
        activatedKey = key;
        mnemonic = new ArrayList<String>();
    }
    
    public void addParam(String m)
    {
        mnemonic.add(m);
    }
}
