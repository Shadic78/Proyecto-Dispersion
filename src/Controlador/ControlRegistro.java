/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Contacto;
import Modelo.UsuariosRegistrados;
import Vista.Login;
import Vista.Registro;
import Vista.SeleccionRuta;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlRegistro {
    private Registro form;

    public ControlRegistro(Registro form) {
        this.form = form;
        this.form.getBtnAceptar().addActionListener(this::registrar);
        this.form.getBtnCancelar().addActionListener(this::cancelar);
    }
    
    private void registrar(ActionEvent e) {
        String nombre = form.getTxtNombre().getText();
        String correo = form.getTxtCorreo().getText();
        int edad = Integer.parseInt(form.getTxtEdad().getText());
        String pass = form.getTxtPassword().getText();
        
        Contacto c = new Contacto(nombre, edad, correo, pass);
        
        if(UsuariosRegistrados.comprobarRegistro(correo)) {
            JOptionPane.showMessageDialog(null, "Registro fallido\nEl correo ya esta registrado");                                  
        }
        else {
            solicitarRuta(c);
            form.dispose();
            //UsuariosRegistrados.registrarYGuardar(correo, c);
            //JOptionPane.showMessageDialog(null, "Registro exitoso");              
        }

    }
    
    private void cancelar(ActionEvent e) {
        Login login = new Login();
        ControlLogin con = new ControlLogin(login);
        login.setVisible(true);
        
        form.dispose();
    }
    
    private void solicitarRuta(Contacto usuario) {
        SeleccionRuta seleccion = new SeleccionRuta();
        ControlSeleccionarRuta controlRuta = new ControlSeleccionarRuta(seleccion, usuario);
    }
    
}
