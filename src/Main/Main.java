/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import ArbolB.NodoB;
import Controlador.ControlLogin;
import Modelo.Contacto;
import Vista.Login;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo1
 */
public class Main {

    public static void main(String[] args) {
        //generarTablas();
        //Login login = new Login();
        //ControlLogin controlLogin = new ControlLogin(login);        
        //login.setVisible(true);
        
        try {
            NodoB<String> n = new NodoB<>(null,  3, 3);
            ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream("Datos/prueba.txt"));
            escribiendoFichero.writeObject(n);
            escribiendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error al guardar el nodo");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void generarTablas() {
        try {
            Hashtable<String, Contacto> tabla = new Hashtable();  
            //Contacto c = new Contacto("Carlos", 20, "carlos@gmail.com", "gongora05");
            //tabla.put(c.getCorreo(), c);
            ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream("Datos/UsuariosRegistrados.txt"));
            escribiendoFichero.writeObject(tabla);
            escribiendoFichero.close();            
        } catch (IOException ex) {
            System.out.println("Error al crear tabla de usuarios registrados");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
