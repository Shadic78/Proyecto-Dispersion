/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.UsuarioNoEncontradoException;
import Modelo.Contacto;
import Modelo.SesionActual;
import Modelo.UsuariosRegistrados;
import Vista.BuscarContacto;
import Vista.EliminarContacto;
import Vista.InfoContacto;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlBuscarEliminarContacto {
    private BuscarContacto form;

    public ControlBuscarEliminarContacto(BuscarContacto form) {
        this.form = form;
        this.form.getBtnAceptar().addActionListener(this::buscar);
        this.form.getBtnCancelar().addActionListener(this::cancelar);
    }
    
    private void buscar(ActionEvent e) {
        try {
            String correo = form.getTxtCorreo().getText();
            Contacto resultado = SesionActual.getContacto(correo);
            if (resultado != null) {
                EliminarContacto info = new EliminarContacto();
                ControlEliminarContacto con = new ControlEliminarContacto(info, resultado);
                info.setVisible(true);
                form.dispose();
            } else {
                throw new UsuarioNoEncontradoException();
            }
        } catch (UsuarioNoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el contacto");
        }        
    }
    
    private void cancelar(ActionEvent e) {
        form.dispose();
    }
    
}
