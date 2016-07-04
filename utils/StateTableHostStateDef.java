/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader.utils;

/**
 *
 * @author Harish
 */
public class StateTableHostStateDef {
    String response, toState, toScreenDef;
    
    public StateTableHostStateDef(String resp, String toState, String toScreenDef)
    {
        this.response = resp;
        this.toState = toState;
        this.toScreenDef = toScreenDef;
    }
}
