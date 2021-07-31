package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends Conexion{
    String sql = "";
    PreparedStatement ps = null;
    Connection con = getConexion();
    ResultSet rs = null;
    
    public boolean login(Login l){
        sql = "SELECT * FROM login WHERE usuario = ? and contrasena = ?";       
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, l.getUser());
            ps.setString(2, l.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                l.setUser(rs.getString("usuario"));
                l.setPassword(rs.getString("contrasena"));
                return true;
            }            
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            try {
                con.close();                
            } catch (SQLException e) {
                System.err.println(e);                
            }
        }      
       
    }
}
