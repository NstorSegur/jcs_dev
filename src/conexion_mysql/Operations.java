/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion_mysql;

import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.protocol.Resultset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author nesto
 */
public class Operations {
    public static boolean isLogin(String username, String completeName, String password, String usertype, JFrame frame){
        
        try {
            Connection myConn = conexionMySQL.getConnection();
            
            String mySqlQuery = 
                    "SELECT adminID, fullname, username, adminpass, admintype FROM jcsdb.adminjcs WHERE username = '" +
                    username+
                    "' AND adminpass = '"+
                    password+
                    "' AND admintype = '"+
                    usertype+
                    "'";
            JdbcPreparedStatement preparedStatement = (JdbcPreparedStatement) myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                loginSession.adminID = resultSet.getString("adminID");
                loginSession.admintype = resultSet.getString("admintype");
                loginSession.fullname = resultSet.getString("fullname");
                loginSession.username = resultSet.getString("username");
                
                return true;
            }   
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Error en la base de datos: " + exception.getMessage());
        }
        return false;
    }

    public boolean isLogin(String usernameString, String usertypeString, String passwordString, String usertypeString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
