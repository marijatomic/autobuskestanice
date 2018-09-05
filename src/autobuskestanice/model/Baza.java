/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marija
 */
public abstract class Baza {
    
    public static Connection connection;
    
    public abstract void spasi();
    public abstract void uredi();
    public abstract void brisi();
    
    public static void spoji(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String url ="jdbc:mysql://"+Config.HOSTNAME+ "/"+Config.DATABASE + "?useUnicode=true&characterEncoding=utf-8" + "&user="+Config.USERNAME+"&password="+Config.PASSWORD;
    
            
            try {
                connection=(Connection) DriverManager.getConnection(url);
            } catch (SQLException ex) {
                System.out.println("Nastala je greška, nisam se mogao spojiti na bazu.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Nastala je greška, ne mogu pronaći klasu com.mysql.jdbc.drive.");
        }
    }
    
    public static void odspoji(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom odspajanja.");
        }
    }
    
}
