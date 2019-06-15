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
public interface PageCreatorLocal {
    
    public String mainpageGuest(String attribute);
    
    public String mainpageUser(String attribute);
    
    public String regions(String attribute);
    
    public String sights(String attribte);
    
    public String sightsInReg(String attribte);
    
    public String events(String attribute);
    
    public String eventInReg(String attribute);
    
    public String commentsMainPage (String attribute);
    
    public String writer (String attribute);
    
    public String redirectGuest (String attribute);
    
    public String writerCommWrite (String attribute);
    
    public String formsForComm();
    
    public String confirmComment(String title);
    
    public String reader(String attribute);
    
    public String readCommets(String attribute);
}
