/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Павлик
 */
@Stateless
public class CommentWriter implements CommentWriterLocal {
    
    private Connection con;
    private ResultSet rs;
    private int id_tourist, id_event, id_sight;

    @Override
    public String writeEventComment(String comment, String title, String login, String obj) {
        DBConn conn = new DBConn();
        try {
            LocalDate todayLocalDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
            con = conn.getConnection();
            Statement st;
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql1 = "SELECT all id, login FROM USERS where login = '" + login.trim() +"'"; //достаем id и логин из базы
            rs = st.executeQuery(sql1);
            rs.beforeFirst();
            rs.next();
            id_tourist = rs.getInt(1); //записываем id туриста в переменную
            rs.close();
            String sql2 = "SELECT all id_event, event_title FROM Events where event_title = '" + obj.trim() +"'";
            rs = st.executeQuery(sql2);
            rs.beforeFirst();
            rs.next();
            id_event = rs.getInt(1); //записываем id события в переменную
            rs.close();
            String sql3 = "INSERT INTO Comments_events (id_tourist, comm_title, comment, id_event, comm_date) VALUES ("
                    + id_tourist + ", '" + title + "', '" + comment +"', " + id_event + ", '" + sqlDate + "')";
            st.executeUpdate(sql3);
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return title;
       
    }
    
    @Override
    public String writeSightComment(String comment, String title, String login, String obj) {
        DBConn conn = new DBConn();
        try {
            LocalDate todayLocalDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
            con = conn.getConnection();
            Statement st, st1;
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql1 = "SELECT all id, login FROM USERS where login = '" + login.trim() +"'"; //достаем id и логин из базы
            rs = st.executeQuery(sql1);
            rs.beforeFirst();
            rs.next();
            id_tourist = rs.getInt(1); //записываем id туриста в переменную
            rs.close();
            String sql2 = "SELECT all sightid, sightname FROM Sight where sightname = '" + obj.trim() +"'";
            rs = st.executeQuery(sql2);
            rs.beforeFirst();
            rs.next();
            id_sight = rs.getInt(1); //записываем id события в переменную
            rs.close();
            String sql3 = "INSERT INTO Comments_sights (id_tourist, comm_title, comment, id_sight, comm_date) VALUES ("
                    + id_tourist + ", '" + title + "', '" + comment +"', " + id_sight + ", '" + sqlDate + "')";
            st.executeUpdate(sql3);
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return title;
       
    }
}
