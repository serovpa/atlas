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
public interface CommentWriterLocal {
    
    public String writeEventComment(String comment, String title, String login, String event);

    public String writeSightComment(String comment, String title, String login, String sight);
}
