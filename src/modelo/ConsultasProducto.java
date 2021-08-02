
package modelo;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import static modelo.Conexion.getConexion;

/**
 *
 * @author fronux
 */
public class ConsultasProducto extends Conexion{
    
    public boolean registrar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();        
        String sql = "INSERT INTO producto (id, codigo, nombre, precio, cantidad) VALUES(SEQUENCE_ID.NEXTVAL,?,?,?,?)";        
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }   
    }    
    
    public boolean modificar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();        
        String sql = "UPDATE producto SET codigo=? , nombre=?, precio=?, cantidad=? WHERE id=? ";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        
        }
    
    }
    
    public boolean eliminar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();        
        String sql = "DELETE FROM producto WHERE id=? ";
        
        try {
            ps = con.prepareStatement(sql);            
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }        
        }    
    }
    
    public boolean buscar(Producto pro){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "select * from producto where codigo = ?";
        
        try {
            ps = con.prepareStatement(sql); 
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next()){
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                return true;
            }else{return false;}
            
        } catch (Exception e) {
        }finally{
            try {
                con.close();
                System.out.println("Conexion cerrada");
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        
        return false;
     }
    
    public ArrayList<Producto> listar(){
        ArrayList listaProducto = new ArrayList();
        Producto producto;
        String sql = "select * from producto";
        Connection con = getConexion();
        
        try {            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setCodigo(rs.getString(2));
                producto.setNombre(rs.getString(3));
                producto.setPrecio(rs.getDouble(4));
                producto.setCantidad(rs.getInt(5));
                listaProducto.add(producto);
            }
        } catch (Exception e) {
            System.err.println(e);
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }        
        }
        return listaProducto;
    }    
    
    public boolean Cagar_combobox(JComboBox cbox_nombre){
        String sql= "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            sql = "Select nombre from producto ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            cbox_nombre.addItem("");
            while(rs.next()){
                cbox_nombre.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }finally{
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return true;
    }
}




