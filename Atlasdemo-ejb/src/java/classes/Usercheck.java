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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Павлик
 */
@Stateless
public class Usercheck implements UsercheckLocal {

    Connection con;
    ResultSet rs, rs2;

    /*Метод для проверки роли пользователя*/
    @Override
    public String userCheck(String login, String psw) {
        String role = "N/A"; //изначально роль обозначаем как N/A
        DBConn conn = new DBConn();
        try {
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT all login FROM USERS where login = '" + login.trim() + "'"; // загружаем логины совпадающие с введеным
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) { // сверяем введеный логин со всеми из списка
                if (login.trim().equals(rs.getString(1))) {
                    Statement st2;
                    st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String sql2 = "SELECT login, psw, user_role FROM USERS where login = '" + login + "'"; //если находим совпадение, то выгружаем строку с данными этого пользователя
                    rs2 = st2.executeQuery(sql2);
                    rs2.beforeFirst();
                    while (rs2.next()) {//сверяем введенный пароль с паролем из базы, если совпадает, то присваиваем роль из базы
                        if (psw.trim().equals(rs2.getString(2))) {
                            role = rs2.getString(3);
                            return role;
                        } else { // если пароли не совпадают, то возвращаем роль N/A
                            return role;
                        }
                    }
                    rs2.close();
                }
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    /*Метод для создания нового пользователя*/
    @Override
    public String createUser(String login, String psw, String firstname, String lastname) {

        String role = "u";
        DBConn conn = new DBConn();
        try {
            con = conn.getConnection();
            Statement st, st1;
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql1 = "SELECT all login FROM USERS where login = '" + login.trim() + "'";
            rs = st.executeQuery(sql1);
            rs.beforeFirst();
            if (rs.next()) { //проверияем наличие пользователя с таким же логином
                role = "N/A";
                rs.close();
                con.close();
                return role;
            } else { //если совпадений нет, то создаем новый
                st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String sql2 = "INSERT INTO USERS (login, psw, user_role, firstname, lastname) VALUES ('" + login + "', '" + psw + "', 'u', '" + firstname + "', '" + lastname + "')";
                st1.executeUpdate(sql2);
                rs.close();
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;

    }
}
