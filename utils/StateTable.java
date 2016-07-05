/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statetablereader.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Harish
 */
public class StateTable {
    File stateFile;
    public ArrayList<StateTableConfig> configList;
    public ArrayList<StateTableTimerDef> timerDefList;
    public ArrayList<StateTableMiscConfigData> miscConfigDataList;
    public ArrayList<StateTableEnhancedParamsDef> enhancedParamsDefList;
    public ArrayList<StateTableScreenDef> screenDefList;
    public ArrayList<StateTableStateDef> stateDefList;
    public ArrayList<StateTableState> stateList;
    public ArrayList<StateTableHostStateDef> hostStateDefList;
    public ArrayList<StateTableOarDef> oarDefList;
    String[] keywords = {"CONFIG","TIMER_DEF", "MISC_CONFIG_DATA", "ENHANCED_PARAMS_DEF", "SCREEN_DEF", "STATE_DEF", "STATE_TABLE", "HOST_STATE_DEF", "OAR_DEF", "PRINT_DEF"};
    Scanner scanner;
    String mode = "NONE";
    boolean screenReading = false;
    StateTableScreenDef stsd = null;
    StateTableOarDef stod = null;
    
    public StateTable(){
        configList = new ArrayList<StateTableConfig>();
        timerDefList = new ArrayList<StateTableTimerDef>();
        miscConfigDataList = new ArrayList<StateTableMiscConfigData>();
        enhancedParamsDefList = new ArrayList<StateTableEnhancedParamsDef>();
        screenDefList = new ArrayList<StateTableScreenDef>();
        stateDefList = new ArrayList<StateTableStateDef>();
        stateList = new ArrayList<StateTableState>();
        hostStateDefList = new ArrayList<StateTableHostStateDef>();
        oarDefList = new ArrayList<StateTableOarDef>();
    }
    
    public StateTable(String path){
        stateFile = new File(path);
        System.out.println("Path : " + path);
        configList = new ArrayList<StateTableConfig>();
        timerDefList = new ArrayList<StateTableTimerDef>();
        miscConfigDataList = new ArrayList<StateTableMiscConfigData>();
        enhancedParamsDefList = new ArrayList<StateTableEnhancedParamsDef>();
        screenDefList = new ArrayList<StateTableScreenDef>();
        stateDefList = new ArrayList<StateTableStateDef>();
        stateList = new ArrayList<StateTableState>();
        hostStateDefList = new ArrayList<StateTableHostStateDef>();
        oarDefList = new ArrayList<StateTableOarDef>();
    }
    
    public StateTable(File file)
    {
        stateFile = file;
        configList = new ArrayList<StateTableConfig>();
        timerDefList = new ArrayList<StateTableTimerDef>();
        miscConfigDataList = new ArrayList<StateTableMiscConfigData>();
        enhancedParamsDefList = new ArrayList<StateTableEnhancedParamsDef>();
        screenDefList = new ArrayList<StateTableScreenDef>();
        stateDefList = new ArrayList<StateTableStateDef>();
        stateList = new ArrayList<StateTableState>();
        hostStateDefList = new ArrayList<StateTableHostStateDef>();
        oarDefList = new ArrayList<StateTableOarDef>();
    }
    
    public void readStateTableFile(){
        if(stateFile != null)
        {
            try
            {
                scanner = new Scanner(stateFile);
                String line;
                line = scanner.nextLine().trim();
                while(scanner.hasNextLine())
                {
                    if(!line.isEmpty() && !line.startsWith("#"))
                    {
                        if(mode.equals("NONE"))
                        {
                            int i;
                            for(i = 0; i < keywords.length; i++)
                            {
                                if(keywords[i].equals(line))
                                {
                                    mode = line;
                                    System.out.println("Reading " + mode + "...");
                                }
                            }
                        }
                        else
                        {
                            while(line.endsWith("\\"))
                            {
                                line = line.replace("\\", "");
                                StringBuilder sb = new StringBuilder();
                                sb.append(line);
                                String temp = scanner.nextLine().trim();
                                while(temp.isEmpty())
                                {
                                    temp = scanner.nextLine().trim();
                                }
                                sb.append(temp);
                                line = sb.toString();
                            }
                            if(mode.equals("CONFIG"))
                            {
                                if(!line.equals("END"))
                                    parseConfig(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("TIMER_DEF"))
                            {
                                if(!line.equals("END"))
                                    parseTimerDef(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("MISC_CONFIG_DATA"))
                            {
                                if(!line.equals("END"))
                                    parseMiscConfigData(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("ENHANCED_PARAMS_DEF"))
                            {
                                if(!line.equals("END"))
                                    parseEnhancedParamsDef(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("OPKEY"))
                            {
                                if(!line.equals("END"))
                                    parseOpkey(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("SCREEN_DEF"))
                            {
                                if(!line.equals("END_SCREEN_DEF"))
                                    parseScreenDef(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("STATE_DEF"))
                            {
                                if(!line.equals("END"))
                                    parseStateDef(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("STATE_TABLE"))
                            {
                                if(!line.equals("END"))
                                    parseStateTable(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("HOST_STATE_DEF"))
                            {
                                if(!line.equals("END"))
                                    parseHostStateDef(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("OAR_DEF"))
                            {
                                if(!line.equals("END"))
                                    parseOarDef(line);
                                else
                                    mode = "NONE";
                            }
                            else if(mode.equals("PRINT_DEF"))
                            {
                                if(line.equals("END"))
                                    //parsePrintDef(line);
                                //else
                                    mode = "NONE";
                            }
                        }
                    }
                    line = scanner.nextLine();
                }
                
                scanner.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void parseConfig(String configLine) throws Exception
    {
        String[] words;
        int j,value=0;
        while(configLine.contains("  "))
            configLine = configLine.replace("  ", " ");
        words = configLine.split(" ");
        value = Integer.parseInt(words[1].trim());
        StateTableConfig stc = new StateTableConfig(words[0], value);
        configList.add(stc);
    }
    
    public void parseTimerDef(String tdLine) throws Exception
    {
        String[] words;
        while(tdLine.contains("  "))
            tdLine = tdLine.replace("  ", " ");
        words = tdLine.split(" ");
        int id, value;
        tdLine = tdLine.replaceFirst(words[0], "");
        value = Integer.parseInt(words[1]);
        tdLine = tdLine.replaceFirst(words[1], "");
        StateTableTimerDef sttd = new StateTableTimerDef(words[0], value, tdLine.trim());
        timerDefList.add(sttd);
    }
    
    
    public void parseMiscConfigData(String mcdLine) throws Exception
    {
        String[] words;
        while(mcdLine.contains("  "))
            mcdLine = mcdLine.replace("  ", " ");
        words = mcdLine.split(" ");
        int value = Integer.parseInt(words[1]);
        StateTableMiscConfigData stmcd = new StateTableMiscConfigData(words[0], value);
        miscConfigDataList.add(stmcd);
    }
    
    public void parseEnhancedParamsDef(String epdLine) throws Exception
    {
        String[] words;
        while(epdLine.contains("  "))
            epdLine = epdLine.replace("  ", " ");
        words = epdLine.split(" ");
        int id, value;
        epdLine = epdLine.replaceFirst(words[0], "");
        value = Integer.parseInt(words[1]);
        epdLine = epdLine.replaceFirst(words[1], "");
        StateTableEnhancedParamsDef stepd = new StateTableEnhancedParamsDef(words[0], value, epdLine.trim());
        enhancedParamsDefList.add(stepd);
    }
    
    public void parseOpkey(String opkeyLine) throws Exception
    {
        
    }
    
    public void parseScreenDef(String screenLine) throws Exception
    {
        if(screenReading == false)
        {
            if(screenLine.startsWith("SCREEN:"))
            {
                screenReading = true;
                //System.out.println(screenLine);
                screenLine = screenLine.replaceFirst("SCREEN:", "");
                stsd = new StateTableScreenDef(screenLine.trim());
            }
        }
        else
        {
            if(screenLine.equals("END"))
            {
                screenReading = false;
                screenDefList.add(stsd);
            }
            else
            {
                stsd.addMnemonic(screenLine);
            }
        }
        
    }
    
    public void parseStateDef(String sdLine) throws Exception
    {
        
        String[] words;
        while(sdLine.contains("  "))
            sdLine = sdLine.replace("  ", " ");
        words = sdLine.trim().split(" ");
        int nop,i;
        nop = Integer.parseInt(words[1]);
        StateTableStateDef stsd = new StateTableStateDef(words[0], nop);
        for(i=2; i<words.length; i++)
            stsd.addParam(words[i]);
        stateDefList.add(stsd);
            
    }
    
    private void parseStateTable(String stLine) throws Exception
    {
        String[] words;
        int loop, wi;
        while(stLine.contains("  "))
            stLine = stLine.trim().replace("  ", " ");
        words = stLine.split(" ");
        StateTableState sts = getStateByName(words[0]);
        
        if(sts == null)
            sts = new StateTableState(words[0], words[1]);
        else
            sts.type = words[1];
        
        StateTableStateDef tempstsd = getStateDefByType(sts.type);
        for(loop=0,wi=2; loop<tempstsd.numberOfParams; loop++,wi++)
        {//System.out.println("analyzing " + words[wi]);
            if(tempstsd.paramList.get(loop).equals("screen"))
            {
                //Find the screen with the given name and add it to this state's paramList
                StateTableScreenDef tempstscr = getScreenDefByName(words[wi]);
                if(tempstscr != null)
                    sts.addParam(tempstscr);
                else
                {
                    sts.addParam("<undef>" + words[wi]);
                    System.out.println("Error : Screen definition for " + words[wi] + " is not found");
                }
            }
            else if(tempstsd.paramList.get(loop).equals("state"))
            {
                //check if the state already exists
                //Otherwise create a state here
                StateTableState tempsts = getStateByName(words[wi]);
                if(tempsts == null)
                    tempsts = new StateTableState(words[wi]);
                sts.addParam(tempsts);
            }
            else if(tempstsd.paramList.get(loop).equals("number"))
            {
                //System.out.println("Converting " + words[wi]);
                Integer integer = new Integer(Integer.parseInt(words[wi]));//Integer.getInteger(words[wi]);
                System.out.println(integer);
                sts.addParam(integer);
            }
        }
        //Object tempObj = new Object();
        
        stateList.add(sts);
    }
    
    private void parseHostStateDef(String hsdLine) throws Exception
    {
        String[] words;
        while(hsdLine.contains("  "))
            hsdLine = hsdLine.replace("  ", " ");
        words = hsdLine.trim().split(" ");
        int i;
        
        StateTableHostStateDef sthsd = new StateTableHostStateDef(words[0], words[1], words[2]);
        hostStateDefList.add(sthsd);
    }
    
    private void parseOarDef(String odLine) throws Exception
    {
        
        if(odLine.startsWith("ActivatedKey:"))
        {
            odLine = odLine.replaceFirst("ActivatedKey:", "").trim();
            stod = new StateTableOarDef(odLine);
        }
        else
        {
            stod.addParam(odLine.trim());
        }
        
    }
    
    private void parsePrintDef(String pdLine) throws Exception
    {
        
    }
    
    public void printStateTableStatus()
    {
        System.out.println("----------------------------------------------");
        System.out.println("Config Count : " + configList.size());
        System.out.println("TimerDef Count : " + timerDefList.size());
        System.out.println("Misc Config Data Count : " + miscConfigDataList.size());
        System.out.println("Enhanced Params Def Count : " + enhancedParamsDefList.size());
        //System.out.println("Opkey Count : " + opkeyList.size());
        System.out.println("Screen Def Count : " + screenDefList.size());
        System.out.println("State Def Count : " + stateDefList.size());
        System.out.println("State Count : " + stateList.size());
        System.out.println("Host State Def Count : " + hostStateDefList.size());
        System.out.println("Oar Def Count : " + 1);
        System.out.println("----------------------------------------------");
    }
    
    public StateTableState getStateByName(String name)
    {
        int i;
        for(i=0;i<stateList.size();i++)
        {
            if(stateList.get(i).name.equals(name))
            {
                return stateList.get(i);
            }
        }
        return null;
    }
    
    public StateTableScreenDef getScreenDefByName(String name){
        int i=0;
        while(i < screenDefList.size())
        {
            if(screenDefList.get(i).name.equals(name))
            {
                return screenDefList.get(i);
            }
            i++;
        }
        return null;
    }
    
    public StateTableStateDef getStateDefByType(String type)
    {
        int i;
        for(i=0; i<stateDefList.size(); i++)
        {
            if(stateDefList.get(i).type.equals(type))
                return stateDefList.get(i);
        }
        return null;
    }
    
    public String getParamTypeAtIndex(String type, int index)
    {
        int i;
        for(i=0; i<stateDefList.size(); i++)
        {
            if(stateDefList.get(i).type.equals(type))
                return stateDefList.get(i).paramList.get(index);
        }
        return "";
    }
}
