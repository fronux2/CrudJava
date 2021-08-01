
package controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultasProducto;
import modelo.Login;
import modelo.LoginDAO;
import modelo.Producto;
import vista.LoginView;
import vista.Vista;

/**
 *
 * @author fronux
 */
public class CtrlProducto implements ActionListener{

    private Producto mod;
    private ConsultasProducto modC;
    private Vista frm;
    private LoginView lv;
    private Login l;
    private LoginDAO ld;
    
    public CtrlProducto(Producto mod, ConsultasProducto modC, Vista frm, LoginView lv, LoginDAO ld, Login l)
    {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.lv = lv;
        this.l = l;
        this.ld = ld;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnListar.addActionListener(this);
        this.lv.btnAceptar.addActionListener(this);
    }
    
    public void iniciar()
    {
        lv.setTitle("Login");
        lv.setLocationRelativeTo(null);
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
        
    }
    
    public void LLenarTabla(JTable tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        tabla.setModel(modeloT);
        
        modeloT.addColumn("id");
        modeloT.addColumn("codigo");
        modeloT.addColumn("nombre");
        modeloT.addColumn("precio");
        modeloT.addColumn("cantidad");
        
        Object[] columna = new Object[5];
        
        int numRegistros = modC.listar().size();
        
        for (int i=0; i<numRegistros; i++ ){
            columna[0] = modC.listar().get(i).getId();
            columna[1] = modC.listar().get(i).getCodigo();
            columna[2] = modC.listar().get(i).getNombre();
            columna[3] = modC.listar().get(i).getPrecio();
            columna[4] = modC.listar().get(i).getCantidad();
            modeloT.addRow(columna);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        
        if(e.getSource() == frm.btnGuardar){
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombres.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            
            if(modC.registrar(mod))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else 
            {
                JOptionPane.showMessageDialog(null, "Error al guardar");
                limpiar();
            }          
        }
        
        if(e.getSource() == frm.btnModificar){
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombres.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            
            if(modC.modificar(mod))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else 
            {
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            } 
        }
        
        if(e.getSource() == frm.btnEliminar){
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            
            if(modC.eliminar(mod))
            {
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtCodigo.setText(mod.getCodigo());
                frm.txtNombres.setText(mod.getNombre());
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
            } else 
            {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
            
        }
        
        if (e.getSource() == frm.btnLimpiar){
            limpiar();
        }
        
        if (e.getSource() == frm.btnListar){
            LLenarTabla(frm.tabla);
        }
        
        if(e.getSource() == frm.btnBuscar){
            mod.setCodigo(frm.txtCodigo.getText());
            if(modC.buscar(mod))
            {
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtCodigo.setText(String.valueOf(mod.getCodigo()));
                frm.txtNombres.setText(String.valueOf(mod.getNombre()));
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                
                        
            }else{JOptionPane.showMessageDialog(null, "No se encontro registro");}
        }
        
        if(e.getSource() == lv.btnAceptar){
            l.setUsuario(lv.txtUser.getText());
            l.setContrasena(lv.txtPassword.getText());
            
            if(ld.login(l))
            {
                Vista v = new Vista();
                v.setVisible(true);
                v.setLocationRelativeTo(null);
                lv.setVisible(false);     
            }else{JOptionPane.showMessageDialog(null, "No se encontro registro");}
        }  
    }
    
    public void limpiarl()
    {
        lv.txtUser.setText(null);
        lv.txtPassword.setText(null);
    }
    
    public void limpiar()
    {
        frm.txtId.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombres.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtCantidad.setText(null);
    }
}
