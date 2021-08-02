/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudmvc;

import controlador.CtrlProducto;
import modelo.ConsultasProducto;
import modelo.Login;
import modelo.LoginDAO;
import modelo.Producto;
import vista.LoginView;
import vista.Vista;

/**
 *
 * @author marku
 */
public class CRUDMVC {
    public static void main(String[] args){
        Producto mod = new Producto();
        ConsultasProducto modC = new ConsultasProducto();
        Vista frm = new Vista();
        LoginView lv = new LoginView();
        Login l = new Login();
        LoginDAO ld = new LoginDAO();
        
        
        CtrlProducto ctrl = new CtrlProducto(mod, modC, frm, lv, ld, l);
        ctrl.iniciar();
        
       
        
        
        
    }
}
