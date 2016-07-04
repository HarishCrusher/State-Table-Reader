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

public class StateTableStateDef {
    ArrayList<String> paramList;
    int numberOfParams;
    String type;
    
    public StateTableStateDef(String type, int nop){
        this.type = type;
        this.numberOfParams = nop;
        this.paramList = new ArrayList<String>();
    }
    
    void addParam(String param){
        paramList.add(param);
    }
    
    void printStateDef()
    {
        System.out.println("Type : " + type + " NOP : " + numberOfParams + " List Size : " + paramList.size());
        int i;
        for(i=0; i<paramList.size(); i++)
            System.out.print(paramList.get(i) + " ");
        System.out.println();
    }
 }
