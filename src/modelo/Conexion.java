
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    private static Connection conn = null ;
    private static String login = "bd1";
    private static String clave = "bd1";
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    
    public static Connection getConexion(){
    try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, login, clave);
            conn.setAutoCommit(false);
            if(conn != null){
                System.out.println("Conexion exitosa");
                
            }
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Conexion Erronea" + e.getMessage());
        }    
        return conn;
    }
       
   
            
            
  
    
}
