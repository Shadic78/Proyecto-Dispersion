/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.UsuarioNoEncontradoException;
import Modelo.Contacto;
import Modelo.UsuariosRegistrados;
import Vista.BuscarContacto;
import Vista.InfoContacto;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlBuscarContacto {
    private BuscarContacto form;

    public ControlBuscarContacto(BuscarContacto form) {
        this.form = form;
        this.form.getBtnAceptar().addActionListener(this::buscar);
        this.form.getBtnCancelar().addActionListener(this::cancelar);
    }
    
    private void buscar(ActionEvent e) {
        try {
            String correo = form.getTxtCorreo().getText();
            Contacto resultado = UsuariosRegistrados.getUsuario(correo);
            if (resultado != null) {
                InfoContacto info = new InfoContacto();
                ControlInfoContacto con = new ControlInfoContacto(info, resultado);
                info.setVisible(true);
                form.dispose();
            } else {
                throw new UsuarioNoEncontradoException();
            }
        } catch (UsuarioNoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void cancelar(ActionEvent e) {
        
    }
    
}
