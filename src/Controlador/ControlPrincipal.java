/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SesionActual;
import Vista.BuscarContacto;
import Vista.Principal;
import java.awt.event.ActionEvent;

/**
 *
 * @author Equipo1
 */
public class ControlPrincipal {
    private Principal form;

    public ControlPrincipal(Principal form) {
        this.form = form;
        this.form.getBtnBuscarContactos().addActionListener(this::buscarContactos);
        this.form.getBtnListarMisContactos().addActionListener(this::listarMisContactos);
        mostrarDatosUsuario();
    }
    
    private void mostrarDatosUsuario() {
        String nombre = SesionActual.getNombre();
        String correo = SesionActual.getCorreo();
        int edad = SesionActual.getEdad();
        
        form.getLbNombre().setText(nombre);
        form.getLbCorreo().setText(correo);
        form.getLbEdad().setText(Integer.toString(edad));
    }
    
    private void buscarContactos(ActionEvent e) {
        BuscarContacto buscarCont = new BuscarContacto();
        ControlBuscarContacto con = new ControlBuscarContacto(buscarCont);
        buscarCont.setVisible(true);
    }
    
    private void listarMisContactos(ActionEvent e) {
        SesionActual.listarMisContactos();
    }
    
}
