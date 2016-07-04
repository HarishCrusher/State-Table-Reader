/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader;

import statetablereader.utils.StateTable;

/**
 *
 * @author Harish
 */
public class StateTableReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StateTable st = new StateTable("F:\\statetbl.txt");
        st.readStateTableFile();
        st.printStateTableStatus();
    }
}
