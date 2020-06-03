/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ArbolesUsuarios;
import Modelo.Contacto;
import Modelo.UsuariosRegistrados;
import Vista.Login;
import Vista.SeleccionRuta;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo1
 */
public class ControlSeleccionarRuta {
    private SeleccionRuta rutaForm;
    private Contacto usuarioNuevo;

    public ControlSeleccionarRuta(SeleccionRuta rutaForm, Contacto usuario) {
        this.rutaForm = rutaForm;
        this.usuarioNuevo = usuario;
        this.rutaForm.getBtnSelector().addActionListener(this::selectorRuta);
        this.rutaForm.getBtnAceptar().addActionListener(this::aceptar);
    }
    
    public void selectorRuta(ActionEvent e) {
        File ruta = selectFile(JFileChooser.DIRECTORIES_ONLY);
        rutaForm.getTxtRuta().setText(ruta.getAbsolutePath());
    }
    
    public void aceptar(ActionEvent e) {
        String ruta = rutaForm.getTxtRuta().getText();
        
        UsuariosRegistrados.registrarYGuardar(usuarioNuevo.getCorreo(), usuarioNuevo);  
        ArbolesUsuarios.registroNuevo(usuarioNuevo.getCorreo(), ruta);
        
        JOptionPane.showMessageDialog(null, "Registro exitoso");  
        
        Login log = new Login();
        ControlLogin c = new ControlLogin(log);
        log.setVisible(true);
        rutaForm.dispose();
    }
    
    public File selectFile(int tipo) {
        File archivo = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Seleccionar");
        chooser.setFileSelectionMode(tipo);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            archivo = chooser.getSelectedFile();
        } else {
            System.out.println("No se escogio un archivo ");
        }
        return archivo;
    }
    
}
