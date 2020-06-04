/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Contacto;
import Modelo.ModeloTablaContactos;
import Modelo.SesionActual;
import Modelo.UsuariosRegistrados;
import Vista.BuscarContacto;
import Vista.ListarContactos;
import Vista.Login;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlPrincipal {
    private Principal form;
    private ModeloTablaContactos modeloTablas;

    public ControlPrincipal(Principal form) {
        this.form = form;
        this.form.getBtnBuscarContactos().addActionListener(this::buscarContactos);
        this.form.getBtnListarMisContactos().addActionListener(this::listarMisContactos);
        this.form.getBtnListarTodos().addActionListener(this::listarTodos);
        this.form.getBtnBorrarCuenta().addActionListener(this::borrarMiCuenta);
        this.modeloTablas = new ModeloTablaContactos();
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
        ArrayList<Contacto> contactos = SesionActual.listarMisContactos();
        crearTabla(contactos, "Mis contactos");
    }
    
    private void listarTodos(ActionEvent e) {
        ArrayList<Contacto> contactos = UsuariosRegistrados.getTodosLosUsuarios();
        crearTabla(contactos, "Todos los usuarios registrados");
    }
    
    private void borrarMiCuenta(ActionEvent e) {
        // Si - 0, No - 1
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de querer borrar tu cuenta?", "Borrar cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(opcion == 0) {
            SesionActual.borrarCuenta();
            JOptionPane.showMessageDialog(null, "La cuenta fue borrada");
            
            Login login = new Login();
            ControlLogin con = new ControlLogin(login);
            login.setVisible(true);
            form.dispose();
        }
    }
    
    private void crearTabla(ArrayList<Contacto> contactos, String titulo) {
        ListarContactos formTabla = new ListarContactos();
        ControlListarContactos con = new ControlListarContactos(formTabla, titulo);
        
        modeloTablas.rellenarTabla(formTabla.getTableContactos(), contactos);
        formTabla.setVisible(true);        
    }
    
}
