/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Contacto;
import Modelo.SesionActual;
import Vista.BuscarContacto;
import Vista.InfoContacto;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlInfoContacto {
    private InfoContacto form;
    private Contacto contacto;

    public ControlInfoContacto(InfoContacto form, Contacto contacto) {
        this.form = form;
        this.contacto = contacto;
        this.form.getBtnAgregar().addActionListener(this::agregarContacto);
        this.form.getBtnAgregarContactos().addActionListener(this::agregarTodosLosContactos);
        this.form.getBtnCancelar().addActionListener(this::cancelar);
        mostrarDatos();
    }
    
    private void mostrarDatos() {
        form.getLbNombre().setText(contacto.getNombre());
        form.getLbCorreo().setText(contacto.getCorreo());
        form.getLbEdad().setText(Integer.toString(contacto.getEdad()));
    }
    
    private void agregarContacto(ActionEvent e) {
        SesionActual.agregarContacto(contacto);
        JOptionPane.showMessageDialog(null, "Agregaste a " + contacto.getNombre() + " a tus contactos");
    }
    
    private void agregarTodosLosContactos(ActionEvent e) {
        
    }
    
    private void cancelar(ActionEvent e) {
        BuscarContacto buscar = new BuscarContacto();
        ControlBuscarContacto con = new ControlBuscarContacto(buscar);
        form.dispose();
    }
    
}
