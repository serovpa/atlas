/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.ejb.Local;

/**
 *
 * @author Павлик
 */
@Local
public interface LoaderLocal {
    
    public String loadRepList();
    
    public String loadKrList();
    
    public String loadOblList();
    
    public String loadOtherlList();
    
    public String loadRegInfo(String region);
    
    public String loadSightsInRegList(String region, String type);
    
    public String loadSightInfo(String sight, String region);
    
    public String loadRandomSightInfo();
    
    public String loadEventsInRegList (String region, String start, String end);
    
    public String loadEventInfo(String event, String region);
    
    public String loadAllEventsInReg(String region);
    
    public String loadAllCommentsEvents(String region, String event);
    
    public String loadAllCommentsSights(String region, String sight);
    
    
}
