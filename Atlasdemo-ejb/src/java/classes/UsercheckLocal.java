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
public interface UsercheckLocal {
    
    public String userCheck(String login, String psw);
    
    public String createUser (String login, String psw, String firstname, String lastname);
}
