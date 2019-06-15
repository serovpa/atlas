/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Павлик
 */
public class User {
    
    private String login;
    private String psw;
    
    public User(String login, String psw){
        this.login = login;
        this.psw = psw;
    }

    public String getLogin() {
        return login;
    }

    public String getPsw() {
        return psw;
    }
    
    
    
    
    
}
