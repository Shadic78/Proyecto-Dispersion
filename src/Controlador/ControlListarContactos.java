/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.ListarContactos;
import java.awt.event.ActionEvent;

/**
 *
 * @author Equipo1
 */
public class ControlListarContactos {
    private ListarContactos form;

    public ControlListarContactos(ListarContactos form, String titulo) {
        this.form = form;
        this.form.getBtnAceptar().addActionListener(this::aceptar);
        this.form.getLbTitulo().setText(titulo);
    }
    
    private void aceptar(ActionEvent e) {
        form.dispose();
    }
    
}
