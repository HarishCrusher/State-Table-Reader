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
public class StateTableState {
    String name;
    String type;
    ArrayList<Object> paramList;
    
    public StateTableState(String name)
    {
        this.name = name;
        paramList = new ArrayList<Object>();
    }
    
    public StateTableState(String name, String type)
    {
        this.name = name;
        this.type = type;
        paramList = new ArrayList<Object>();
    }
    
    public void addParam(Object o)
    {
        paramList.add(o);
    }
}
