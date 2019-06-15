/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Павлик
 */
public class DBConn {
    
    private String dbuser = "root";
    private String dbpsw = "root";
    private String dburl = "jdbc:derby://localhost:1527/Atlasdb";
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dburl, dbuser, dbpsw);
    }
    
}
