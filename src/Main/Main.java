/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import ArbolB.ArbolB;
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
        /*Login login = new Login();
        ControlLogin controlLogin = new ControlLogin(login);        
        login.setVisible(true);*/
        
        ArbolB<Contacto> ar = new ArbolB<>(3);
        Contacto borrar = new Contacto("xd2", 16, "asd2@", "pas2");
        ar.insertar(new Contacto("xd", 12, "asd@", "pas"));
        ar.insertar(borrar);
        ar.insertar(new Contacto("xd3", 13, "asd3@", "pas3"));
        System.out.println(ar);
        System.out.println("deleting xd2");
        ar.remove(borrar);
        System.out.println(ar);
        System.out.println("Insertando 4  -  5");
        ar.insertar(new Contacto("xd4", 4, "asd4@", "pas4"));
        ar.insertar(new Contacto("xd5", 5, "asd5@", "pas5"));
        System.out.println(ar);
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
