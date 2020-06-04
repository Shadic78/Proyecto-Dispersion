/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Contacto;
import Modelo.SesionActual;
import Vista.EliminarContacto;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlEliminarContacto {
    private EliminarContacto form;
    private Contacto contacto;

    public ControlEliminarContacto(EliminarContacto form, Contacto c) {
        this.form = form;
        this.contacto = c;
        this.form.getBtnEliminar().addActionListener(this::eliminar);
        this.form.getBtnCancelar().addActionListener(this::cancelar);
        mostrarDatos();
    }
    
    private void mostrarDatos() {
        form.getLbNombre().setText(contacto.getNombre());
        form.getLbCorreo().setText(contacto.getCorreo());
        form.getLbEdad().setText(Integer.toString(contacto.getEdad()));
    }    
    
    private void eliminar(ActionEvent e) {
        SesionActual.eliminarContacto(contacto);
        form.dispose();
        JOptionPane.showMessageDialog(null, "Se eliminó a " + contacto.getNombre() + " de tus contactos");
    }
    
    private void cancelar(ActionEvent e) {
        form.dispose();
    }
    
}
