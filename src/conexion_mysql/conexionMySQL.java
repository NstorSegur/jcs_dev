package conexion_mysql;
        
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;       

/**
 *
 * @author nesto
 */
public class conexionMySQL {
    
    public static Connection getConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jcsdb"; 
        String username = "MarkytosPRO";
        String password = "123456789";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection myConn = (Connection) DriverManager.getConnection(url, username, password);
        
        return myConn;
    }
}
