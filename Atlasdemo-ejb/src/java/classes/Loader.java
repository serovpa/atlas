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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Павлик
 */
@Stateless
public class Loader implements LoaderLocal {

    private Connection con;
    private ResultSet rs;
    String republic, kr, obl, regInfo, otherReg, allReg, sightInReg, sightInfo,
            randomSightInfo, eventInReg, eventInfo, randomEventInfo, allEventsInReg,
            allCommentsEvents, allCommentsSights, regCode;
    String test = "TEST";
    String[] regs;

    /*Метод для загрузки всех регионов из БД*/
    public String[] loadAllReg() {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st;
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT all REGION FROM REGIONS"; // загружаем готовим выборку регионов /**/
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1));
                sb.append(",");
            }
            allReg = sb.toString();
            String delimeter = ",";
            regs = allReg.split(delimeter);
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regs;
    }

    /*Метод для загрузки списка Республик из БД*/
    @Override
    public String loadRepList() {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT all REGION FROM REGIONS WHERE Region LIKE '%Республика%'"; // загружаем готовим выборку республик /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb1 = new StringBuilder();
            while (rs.next()) {
                sb1.append(rs.getString(1));
                sb1.append("<br/>");

            }
            republic = sb1.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return republic;
    }

    /*Метод для загрузки списка Краев из БД*/
    @Override
    public String loadKrList() {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT all REGION FROM REGIONS WHERE Region LIKE '%край%'"; // загружаем готовим выборку краев /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb2 = new StringBuilder();
            while (rs.next()) {
                sb2.append(rs.getString(1));
                sb2.append("<br/>");
            }
            kr = sb2.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kr;
    }

    /*Метод для загрузки списка Областей из БД*/
    @Override
    public String loadOblList() {
        try {
            int count = 0;
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT all REGION FROM REGIONS WHERE Region LIKE '%область%' and region not like '%автономная%'"; // загружаем готовим выборку областей /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb3 = new StringBuilder();
            while (rs.next()) {
                sb3.append(rs.getString(1));
                sb3.append(", ");
                count++;
                if (count % 2 == 0) {
                    sb3.append("<br/>");
                }
            }
            obl = sb3.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obl;
    }

    /*Метод для загрузки списка остальных регионов из БД*/
    @Override
    public String loadOtherlList() {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT all REGION FROM REGIONS WHERE Region LIKE '%Город%' or region like '%автоном%'"; // загружаем готовим выборку областей /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb4 = new StringBuilder();
            while (rs.next()) {
                sb4.append(rs.getString(1));
                sb4.append(", ");
                sb4.append("<br/>");
            }
            otherReg = sb4.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return otherReg;
    }

    /*Метод для загрузки информации о регионе из БД*/
    @Override
    public String loadRegInfo(String region) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM REGIONS WHERE Region like '%" + region + "%'"; // загружаем строку с запрашиваемым регионом /**/
            rs = st1.executeQuery(sql);
            rs.first();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(rs.getString(2) + "<br/>");
            sb4.append("Столица - " + rs.getString(3) + "<br/>");
            sb4.append("Площадь, квадратных км - " + rs.getDouble(5) + "<br/>");
            sb4.append("Население на 2019 год - " + rs.getDouble(6) + " человек" + "<br/>");
            regInfo = sb4.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regInfo;
    }

    /*Метод для загрузки списка достопримечательностей в регионе*/
    @Override
    public String loadSightsInRegList(String region, String type) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT All Sightname FROM Sight INNER JOIN Regions ON (region IN('" + region + "') AND  sight.PLACE_REG_CODE = regions.code) where sighttype like '%" + type + "%'"; // загружаем список достопримечательностей в данном регионе регионом /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb5 = new StringBuilder();
            while (rs.next()) {
                sb5.append(rs.getString(1));
                sb5.append("<br/>");
            }
            sightInReg = sb5.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sightInReg;
    }

    /*Метод для загрузки информации о достопримечательности из БД*/
    @Override
    public String loadSightInfo(String sight, String region) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Sight inner join regions on (sight.PLACE_REG_CODE = regions.code) WHERE Sightname like '%" + sight + "%' and region like '%" + region + "%'"; // загружаем строку с запрашиваемым местом /**/
            rs = st1.executeQuery(sql);
            rs.first();
            StringBuilder sb6 = new StringBuilder();
            sb6.append(rs.getString(2) + "<br/>");
            sb6.append("Находится в - " + rs.getString(3) + "<br/>");
            sb6.append("Место - " + rs.getString(5) + "<br/>");
            sb6.append("Краткая информация - " + rs.getString(6) + "<br/>");
            sb6.append("На карте: <br/>");
            sb6.append(rs.getString(8));
            sightInfo = sb6.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sightInfo;
    }

    /*Метод для загрузки информации о случайной достопримечательности из БД*/
    @Override
    public String loadRandomSightInfo() {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Sight"; // загружаем все достопримечательности /**/
            rs = st1.executeQuery(sql);
            rs.last();
            int max = rs.getRow();
            Random random = new Random();
            int i = random.nextInt(max);
            rs.absolute(i);
            if (i == 0) {
                rs.first();
            }
            StringBuilder sb7 = new StringBuilder();
            sb7.append(rs.getString(2) + "<br/>");
            sb7.append("Находится в - " + rs.getString(3) + "<br/>");
            sb7.append("Место - " + rs.getString(5) + "<br/>");
            sb7.append("Краткая информация - " + rs.getString(6) + "<br/>");
            sb7.append("На карте: <br/>");
            sb7.append(rs.getString(8));
            randomSightInfo = sb7.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return randomSightInfo;
    }

    /*Метод для загрузки информации о событиях в регионе из БД*/
    @Override
    public String loadEventsInRegList(String region, String start, String end) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT All Event_title FROM Events INNER JOIN Regions ON (region IN('" + region + "') AND  events.event_REG_CODE = regions.code) "
                    + "where (events.event_startdate <= '" + start + "' and events.event_enddate >= '" + end + "')"
                    + " or (events.event_startdate >= '" + start + "' or events.event_enddate >= '" + end + "')"; // загружаем список  событий в данном регионе регионом /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb6 = new StringBuilder();
            while (rs.next()) {
                sb6.append(rs.getString(1));
                sb6.append("<br/>");
            }
            eventInReg = sb6.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventInReg;

    }

    /*Метод для загрузки информации о событии*/
    @Override
    public String loadEventInfo(String event, String region) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Events inner join regions on (Events.event_REG_CODE = regions.code) WHERE Event_title like '%" + event.trim() + "%' and region like '%" + region + "%'"; // загружаем строку с запрашиваемым местом /**/
            rs = st1.executeQuery(sql);
            rs.first();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(rs.getString(7) + "<br/>");
            sb7.append("Описание - " + rs.getString(2) + "<br/>");
            sb7.append("Начало - " + rs.getDate(4) + "<br/>");
            sb7.append("Окончание - " + rs.getDate(6) + "<br/>");
            sb7.append("Место проведения - " + rs.getString(5) + "<br/>");
            eventInfo = sb7.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventInfo;
    }

    /*Метод для загрузки списка событий в регионе из БД*/
    @Override
    public String loadAllEventsInReg(String region) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT All Event_title FROM Events INNER JOIN Regions "
                    + "ON (region IN('" + region + "') AND  events.event_REG_CODE = regions.code) "; // загружаем список  событий в данном регионе регионе /**/
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            StringBuilder sb6 = new StringBuilder();
            while (rs.next()) {
                sb6.append(rs.getString(1));
                sb6.append("<br/>");
            }
            allEventsInReg = sb6.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEventsInReg;

    }
    
    /*Метод для загрузки комментариев о событии*/
    @Override
    public String loadAllCommentsEvents(String region, String event) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "Select code from Regions where region = '" + region + "'";
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            rs.next();
            regCode = rs.getString(1);
            rs.close();
            String sql2 = "SELECT Comments_events.Comm_title, Comments_events.comment, Comments_events.comm_date, USERS.login "
                    + "FROM Comments_events "
                    + "INNER JOIN USERS ON (USERS.ID = Comments_events.ID_tourist)"
                    + "inner join events on (events.ID_EVENT = Comments_events.ID_EVENT)"
                    + "where event_reg_code = '" + regCode + "' and event_title = '" + event + "'"
                    + "ORDER BY comments_events.comm_date"; // загружаем список комментариев
            rs = st1.executeQuery(sql2);
            rs.beforeFirst();
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Вот что пишут другие туристы о " + event);
            while (rs.next()) {
                sb7.append("<hr>Написано: " + rs.getDate(3) + "<br/>");
                sb7.append("Автор: " + rs.getString(4) + "<br/>");
                sb7.append("Комментарий: " + rs.getString(1) + "<br/>");
                sb7.append(rs.getString(2) + "<hr><br/>");
            }
            allCommentsEvents = sb7.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCommentsEvents;
    }

    /*Метод для загрузки клмментариев о достопримечательности*/
    @Override
    public String loadAllCommentsSights(String region, String sight) {
        try {
            DBConn conn = new DBConn();
            con = conn.getConnection();
            Statement st1;
            st1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "Select code from Regions where region = '" + region + "'";
            rs = st1.executeQuery(sql);
            rs.beforeFirst();
            rs.next();
            regCode = rs.getString(1);
            rs.close();
            String sql2 = "SELECT Comments_sights.Comm_title, Comments_sights.comment, Comments_sights.comm_date, USERS.login "
                    + "FROM Comments_sights "
                    + "INNER JOIN USERS ON (USERS.ID = Comments_sights.ID_tourist) "
                    + "inner join sight on (sight.sightid = Comments_sights.ID_sight) "
                    + "where place_reg_code = '" + regCode + "' and sightname = '" + sight + "' "
                    + "ORDER BY comments_sights.comm_date"; // загружаем список комментариев
            rs = st1.executeQuery(sql2);
            rs.beforeFirst();
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Вот что пишут другие туристы о " + sight);
            while (rs.next()) {
                sb7.append("<hr>Написано: " + rs.getDate(3) + "<br/>");
                sb7.append("Автор: " + rs.getString(4) + "<br/>");
                sb7.append("Комментарий: " + rs.getString(1) + "<br/>");
                sb7.append(rs.getString(2) + "<hr><br/>");
            }
            allCommentsSights = sb7.toString();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usercheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCommentsSights;
    }

}
