/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ArbolB.ArbolB;
import Interfaces.Arbol;
import Main.Main;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo1
 */
public class ArbolesUsuarios {

    private ArbolesUsuarios(){}

    public static void registroNuevo(String correo, String ruta) {
        // Guardar la ruta en la tabla de rutas
        Hashtable<String, String> tabla = ArbolesUsuarios.getTablaGuardada();
        tabla.put(correo, ruta);
        ArbolesUsuarios.guardarTabla(tabla);
        
        // Crear y guardar el arbol del usuario
        ArbolB<Contacto> arbol = new ArbolB<Contacto>(3);
        ArbolesUsuarios.guardarArbol(arbol, ruta, correo);
    }

    public static Hashtable<String, String> getTablaGuardada() {
        Hashtable<String, String> tabla = new Hashtable();
        try {
            ObjectInputStream leyendoFichero = new ObjectInputStream(
                    new FileInputStream("Datos/ArbolesUsuarios.txt"));
            tabla = (Hashtable) leyendoFichero.readObject();
            leyendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error io leer tabla arboles");
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
    
    public static ArbolB<Contacto> cargarArbol(Contacto c) {
        ArbolB<Contacto> arbol = null;
        try {
            String ruta = ArbolesUsuarios.getRutaArbol(c.getCorreo());
            System.out.println("Ruta del arbol: " + ruta + "\\" + c.getCorreo() + ".txt");
            ObjectInputStream leyendoFichero = new ObjectInputStream(
                    new FileInputStream(ruta + "\\" + c.getCorreo() + ".txt"));
            arbol = (ArbolB) leyendoFichero.readObject();
            leyendoFichero.close();
        }catch (ClassNotFoundException ex) {
            System.out.println("Error class not found");
        } catch (IOException ex) {
            System.out.println("Error io al cargar el arbol");
            Logger.getLogger(ArbolesUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arbol;
    }
    
    public static void guardarArbol(ArbolB<Contacto> arbol, String ruta, String correo) {
        try {
            ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream(ruta + "/" + correo + ".txt"));
            escribiendoFichero.writeObject(arbol);
            escribiendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error al guardar el arbol");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static ArrayList<ArbolB<Contacto>> getTodosLosArboles() {
        ArrayList<ArbolB<Contacto>> arboles = new ArrayList<>();
        ArrayList<Contacto> usuarios = UsuariosRegistrados.getTodosLosUsuarios();
        
        for(int i = 0; i < usuarios.size(); i++) {
            ArbolB<Contacto> arbol = ArbolesUsuarios.cargarArbol(usuarios.get(i));
            arboles.add(arbol);
        }
        
        return arboles;
    }
    
}
