/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
public class UsuariosRegistrados {
    
    private UsuariosRegistrados(){}
    
    public static void registrarYGuardar(String key, Contacto value) {
        // Guardar el contacto en la tabla y guardarla
        Hashtable<String, Contacto> tabla = UsuariosRegistrados.getTablaGuardada();     
        tabla.put(key, value);
        UsuariosRegistrados.guardarTabla(tabla);
        
        // Crear y guardar su arbol
    }
    
    public static boolean comprobarRegistro(String key) {
        boolean registrado = true;
        if(UsuariosRegistrados.getTablaGuardada().get(key) == null) {
            registrado = false;
        }
        return registrado;
    }
    
    public static Contacto getUsuario(String correo) {
        return UsuariosRegistrados.getTablaGuardada().get(correo);
    }
    
    public static Hashtable<String, Contacto> getTablaGuardada() {
        Hashtable<String, Contacto> tabla = new Hashtable();        
        try {
            ObjectInputStream leyendoFichero = new ObjectInputStream(
                    new FileInputStream("Datos/UsuariosRegistrados.txt"));           
            tabla = (Hashtable) leyendoFichero.readObject();
            leyendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error io");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error class not found");
        }
        return tabla;
    }
    
    public static void guardarTabla(Hashtable<String, Contacto> tabla) {
        try {
            ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream("Datos/UsuariosRegistrados.txt"));
            escribiendoFichero.writeObject(tabla);
            escribiendoFichero.close();
        } catch (IOException ex) {
            System.out.println("Error al guardar la tabla");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Contacto> getTodosLosUsuarios() {
        ArrayList<Contacto> listaUsuarios = new ArrayList<>();
        Hashtable<String, Contacto> tabla = UsuariosRegistrados.getTablaGuardada();
        Collection<Contacto> col = tabla.values();
        Iterator<Contacto> itr = col.iterator();
        
        while(itr.hasNext()) {
            listaUsuarios.add(itr.next());
        }
        return listaUsuarios;
    }
    
}
