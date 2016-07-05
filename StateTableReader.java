/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import statetablereader.utils.StateTable;
import statetablereader.utils.StateTableScreenDef;
import statetablereader.utils.StateTableState;

/**
 *
 * @author Harish
 */
public class StateTableReader {

    /**
     * @param args the command line arguments
     */
    static StateTable st;
    static int sbX = 600, sbY = 50;
    
    public StateTableReader()
    {
        
    }
    private static void addLegends(JFrame p)
    {
        JPanel whole = new JPanel();
        JButton tb = new JButton();
        tb.setText("Screens");
        tb.setSize(90, 30);
        tb.setBackground(Color.GREEN);
        
        JButton tb1 = new JButton();
        tb1.setText("State (parameter)");
        tb1.setSize(90, 30);
        tb1.setBackground(Color.ORANGE);
        
        JButton tb2 = new JButton();
        tb2.setText("Number");
        tb2.setSize(90, 30);
        tb2.setBackground(Color.YELLOW);
        
        JButton tb3 = new JButton();
        tb3.setText("State");
        tb3.setSize(90, 30);
        tb3.setBackground(Color.BLUE);
        tb3.setForeground(Color.WHITE);
        
        JButton tb4 = new JButton();
        tb4.setText("Undefined");
        tb4.setSize(90, 30);
        tb4.setBackground(Color.RED);
        tb4.setForeground(Color.WHITE);
        
        whole.setLayout(new FlowLayout(FlowLayout.CENTER));
        whole.add(tb);
        whole.add(tb1);
        whole.add(tb2);
        whole.add(tb3);
        whole.add(tb4);
        p.add(whole);
    }
    private static void addState(JPanel p,StateTableState sts)
    {
        ArrayList<String> pList = sts.paramList;
        JPanel whole = new JPanel();
        JPanel child = new JPanel();
        JPanel child1 = new JPanel();
        //child.setSize(700,300);
        //whole.setSize(900,400);
        JButton tb = new JButton();
        tb.setText(sts.name);
        tb.setSize(90, 30);
        //tb.setLocation(sbX, sbY);
        tb.setBackground(Color.BLUE);
        tb.setForeground(Color.WHITE);
        //p.add(tb);
        sbY += 100;
        int i;
        for(i=0;i<pList.size();i++)
        {
            JButton ttb = new JButton();
            ttb.setSize(90,30);
            ttb.setText(sts.getParamString(i+1));
            
            if(pList.get(i).equals("<STATE>"))
            {
                if(ttb.getText().equals("NULL"))
                {
                    ttb.setBackground(Color.RED);
                    ttb.setForeground(Color.WHITE);
                }
                else
                    ttb.setBackground(Color.ORANGE);
            }
            else if(pList.get(i).equals("<NUMBER>"))
            {
                ttb.setBackground(Color.YELLOW);
            }
            else if(pList.get(i).equals("<SCREEN>"))
            {
                if(ttb.getText().startsWith("<undef>"))
                {
                    ttb.setBackground(Color.RED);
                    ttb.setForeground(Color.WHITE);
                }
                ttb.setBackground(Color.GREEN);
            }
            else
            {
                ttb.setBackground(Color.RED);
                ttb.setForeground(Color.WHITE);
            }
            //ttb.setFont(new Font("Calibri", 17, 17));
            child.add(ttb);
            
        }
        child.setLayout(new FlowLayout(FlowLayout.CENTER));
        child1.setLayout(new FlowLayout(FlowLayout.CENTER));
        //child.setLayout(new GridLayout(1,sts.paramList.size(),15,15));
        tb.setFont(new Font("Calibri",16,16));
        //whole.add(tb);
        child1.add(tb);
        whole.add(child1);
        whole.add(child);
        whole.setLayout(new GridLayout(2, 1, 10, 10));
        //whole.setFont(new Font("Calibri",16,16));//new Insets(9, 9, 9, 9));
        //whole.setLayout(new GridLayout(FlowLayout.CENTER));
        whole.setBackground(Color.lightGray);
        child1.setBackground(Color.lightGray);
        child.setBackground(Color.lightGray);
        //child.setSize(p.getWidth(), 400);
        //whole.setSize(p.getWidth(), 400);
        p.add(whole);
    }
    
    private static StateTableViewerForm initComponents(StateTable s)
    {
        StateTableViewerForm v = new StateTableViewerForm();
        v.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //System.out.println("Max both : " + JFrame.MAXIMIZED_BOTH);
        
        JPanel container = new JPanel();
        //Add Elements Here
        
        container.setLayout(new GridLayout(s.stateList.size(), 1, 20, 20));
        
        container.setSize(v.getWidth(), v.getHeight());
        container.revalidate();
        addLegends(v);
        ArrayList<StateTableState> stsList = s.stateList;
        int si;
        for(si=0; si<stsList.size(); si++)
        {
            addState(container,stsList.get(si));
        }
        
        //container.setPreferredSize(v.getSize(null));
        //container.add();
        container.revalidate();
        JScrollPane jsp = new JScrollPane(container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        jsp.setPreferredSize(new Dimension(1300, 650));
        jsp.revalidate();
        v.add(jsp);
        return v;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //StateTable 
        st = new StateTable("F:\\statetbl.txt");
        st.readStateTableFile();
        st.printStateTableStatus();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StateTableViewerForm viewer = initComponents(st);
                viewer.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                viewer.setVisible(true);
            }
        }); 
    }
}
