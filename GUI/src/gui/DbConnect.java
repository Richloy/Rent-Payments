package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DbConnect {
    
    Statement stmt;
    Connection con;
    String host = "jdbc:mysql://localhost:3306/rent-payments";
    ResultSet rs;
    
    public DbConnect(String name, String password) {
        try {
            con = DriverManager.getConnection(host, name, password);
            JOptionPane.showMessageDialog(new JFrame(), "Connected to database as: " + name, "DATABASE CONNECTED", JOptionPane.INFORMATION_MESSAGE);

        } catch(SQLException err) {
            
            JOptionPane.showMessageDialog(new JFrame(), "Could not connect to database,\nplease use correct authentication.", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(err.getMessage());
        }
    }
   
    public Map lastFivePayments() {
        Map <String, Integer> map = new HashMap();
        String query = "SELECT * FROM payments ORDER BY ID DESC LIMIT 5";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                String date = new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(2));
                int i = rs.getInt(3);
                map.put(date, i);
                System.out.println("Date: " + rs.getDate(2));
                System.out.println("Amount: " + rs.getInt(3));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }
}
