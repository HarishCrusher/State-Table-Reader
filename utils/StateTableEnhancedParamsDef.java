/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader.utils;

/**
 *
 * @author Harish
 */
public class StateTableEnhancedParamsDef {
    String id, comment;
    int value;
    
    public StateTableEnhancedParamsDef(String id, int value, String comment){
        this.id = id;
        this.value = value;
        this.comment = comment;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    public String getId(){
        return id;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return comment;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    
    public String getString(){
        return (new StringBuilder().append(comment).append(" : ").append(id).append(" : ").append(value).toString());
    }
}
