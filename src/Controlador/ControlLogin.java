/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.CorreoNoRegistradoException;
import Excepciones.PasswordIncorrectoException;
import Modelo.Contacto;
import Modelo.SesionActual;
import Modelo.UsuariosRegistrados;
import Vista.Login;
import Vista.Principal;
import Vista.Registro;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlLogin {
    private Login form;

    public ControlLogin(Login form) {
        this.form = form;
        this.form.getBtnEntrar().addActionListener(this::iniciarSesion);
        this.form.getBtnRegistrarse().addActionListener(this::registrarse);
    }
 
    private void iniciarSesion(ActionEvent e) {
        String correo = form.getTxtNombre().getText();
        String pass = form.getTxtPassword().getText();
        Hashtable<String, Contacto> usuariosRegistrados = UsuariosRegistrados.getTablaGuardada();
        
        try {
            Contacto usuario = usuariosRegistrados.get(correo);
            if(usuario != null) {
                if(usuario.getPassword().equals(pass)) {
                    JOptionPane.showMessageDialog(null, "Inició sesión");

                    SesionActual.cargarDatos(usuario);
                    Principal menu = new Principal();
                    ControlPrincipal c = new ControlPrincipal(menu);
                    menu.setVisible(true);
                    
                    form.dispose();
                }
                else {
                    throw new PasswordIncorrectoException();
                }
            }
            else {
                throw new CorreoNoRegistradoException();
            }            
        } catch (PasswordIncorrectoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (CorreoNoRegistradoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    private void registrarse(ActionEvent e) {
        Registro reg = new Registro();
        ControlRegistro con = new ControlRegistro(reg);
        reg.setVisible(true);
        
        form.dispose();
    }
    
}
