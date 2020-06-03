/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ArbolB.ArbolB;
import Main.Main;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo1
 */
public class ArbolesUsuarios {

    private ArbolesUsuarios(){}

    public static void registroNuevo(String correo, String ruta) {
        Hashtable<String, String> tabla = ArbolesUsuarios.getTablaGuardada();
        tabla.put(correo, ruta);
        ArbolesUsuarios.guardarTabla(tabla);
    }

    public static Hashtable<String, String> getTablaGuardada() {
        Hashtable<String, String> tabla = new Hashtable();
        try {
            ObjectInputStream leyendoFichero = new ObjectInputStream(
                    new FileInputStream("Datos/ArbolesUsuarios.txt"));
            tabla = (Hashtable) leyendoFichero.readObject();
            leyendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error io");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error class not found");
        }
        return tabla;
    }

    public static void guardarTabla(Hashtable<String, String> tabla) {
        try {
            ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream("Datos/ArbolesUsuarios.txt"));
            escribiendoFichero.writeObject(tabla);
            escribiendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error al guardar la tabla");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getRutaArbol(String correo) {
        return ArbolesUsuarios.getTablaGuardada().get(correo);
    }
    
}
