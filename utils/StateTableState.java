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
    public String name;
    public String type;
    public ArrayList<String> paramList;
    ArrayList<StateTableState> stateList;
    ArrayList<StateTableScreenDef> screenDefList;
    ArrayList<Integer> numberList;
    
    public StateTableState(String name)
    {
        this.name = name;
        paramList = new ArrayList<String>();
        stateList = new ArrayList<StateTableState>();
        screenDefList = new ArrayList<StateTableScreenDef>();
        numberList = new ArrayList<Integer>();
    }
    
    public StateTableState(String name, String type)
    {
        this.name = name;
        this.type = type;
        paramList = new ArrayList<String>();
        stateList = new ArrayList<StateTableState>();
        screenDefList = new ArrayList<StateTableScreenDef>();
        numberList = new ArrayList<Integer>();
    }
    
    public void addParam(StateTableState p)
    {
        paramList.add("<STATE>");
        stateList.add(p);
    }
    
    public void addParam(StateTableScreenDef p)
    {
        paramList.add("<SCREEN>");
        screenDefList.add(p);
    }
    
    public void addParam(Integer p)
    {
        paramList.add("<NUMBER>");
        numberList.add(p);
    }
    
    public void addParam(String p)
    {
        paramList.add(p);
    }
    
    public StateTableState getParam(int location, StateTableState dummy)
    {
        int i,st=-1;
        for(i=0; i<location; i++)
        {
            if(paramList.get(i).equals("<STATE>"))
                st++;
        }
        return stateList.get(i);
    }
    
    public StateTableScreenDef getParam(int location, StateTableScreenDef dummy)
    {
        int i,sc=-1;
        for(i=0; i<location; i++)
        {
            if(paramList.get(i).equals("<SCREEN>"))
                sc++;
        }
        return screenDefList.get(i);
    }
    
    public Integer getParam(int location, int dummy)
    {
        int i,n=-1;
        for(i=0; i<location; i++)
        {
            if(paramList.get(i).equals("<NUMBER>"))
                n++;
        }
        return numberList.get(i);
    }
    
    public String getParamString(int location)
    {
        int i,n=-1,st=-1,sc=-1;
        //System.out.println(paramList.get(location-1) + "     " + location);
        
        for(i=0; i<location; i++)
        {
            if(paramList.get(i).equals("<SCREEN>"))
                sc++;
            else if(paramList.get(i).equals("<STATE>"))
                st++;
            else if(paramList.get(i).equals("<NUMBER>"))
                n++;
         }
        
        //System.out.println(st + "  " + sc + "  " + n);
//        System.out.println(this.name);
//        for(i=0;i<numberList.size();i++)
//            System.out.print(numberList.get(i) + " ");
//        System.out.println();
        
        if(paramList.get(location-1).equals("<SCREEN>"))
            return screenDefList.get(sc).name;
        else if(paramList.get(location-1).equals("<STATE>"))
            return stateList.get(st).name;
        else if(paramList.get(location-1).equals("<NUMBER>"))
            return numberList.get(n).toString();
        return paramList.get(location-1);
    }
}
