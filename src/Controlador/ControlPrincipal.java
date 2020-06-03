/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SesionActual;
import Vista.Principal;

/**
 *
 * @author Equipo1
 */
public class ControlPrincipal {
    private Principal form;

    public ControlPrincipal(Principal form) {
        this.form = form;
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
    
}
