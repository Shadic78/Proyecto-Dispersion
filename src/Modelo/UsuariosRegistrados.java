/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ArbolB.ArbolB;
import Excepciones.NoDatosException;
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
    
    public static void borrarUsuario(Contacto c) {
        // Quitar el usuario de la tabla de usuarios registrados        
        Hashtable<String, Contacto> tablaUsuarios = UsuariosRegistrados.getTablaGuardada();
        tablaUsuarios.remove(c.getCorreo());
        UsuariosRegistrados.guardarTabla(tablaUsuarios);
        
        // Quitar la ruta del arbol de la tabla de arboles
        Hashtable<String, String> tablaArboles = ArbolesUsuarios.getTablaGuardada();
        tablaArboles.remove(c.getCorreo());
        ArbolesUsuarios.guardarTabla(tablaArboles);
        
        // Borrar la cuenta de los contactos de todos
        UsuariosRegistrados.borrarContactoDeTodos(c);
    }
    
    private static void borrarContactoDeTodos(Contacto c) {
        try {
            //ArrayList<ArbolB<Contacto>> arboles = ArbolesUsuarios.getTodosLosArboles();
            ArrayList<Contacto> usuarios = UsuariosRegistrados.getTodosLosUsuarios();
            Hashtable<String, String> tablaArboles = ArbolesUsuarios.getTablaGuardada();

            for (int i = 0; i < usuarios.size(); i++) {
                Contacto usuario = usuarios.get(i);
                ArbolB<Contacto> arbolUsuario = ArbolesUsuarios.cargarArbol(usuario);
                ArrayList<Contacto> listaContactos = arbolUsuario.enlistarElementos();
                String rutaGuardado = tablaArboles.get(usuario.getCorreo());
                
                Contacto aBorrar = UsuariosRegistrados.obtenerContacto(c, listaContactos);
                if(aBorrar != null) {
                    arbolUsuario.remove(aBorrar);
                    ArbolesUsuarios.guardarArbol(arbolUsuario, rutaGuardado, usuario.getCorreo());
                }
            }
            
        } catch (NoDatosException ex) {
            Logger.getLogger(UsuariosRegistrados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*for(int i = 0; i < arboles.size(); i++) {
            Contacto contacto = usuarios.get(i);
            ArbolB<Contacto> arbol = arboles.get(i);
            String ruta = tablaArboles.get(contacto.getCorreo());
            
            arbol.remove(c);
            ArbolesUsuarios.guardarArbol(arbol, ruta, contacto.getCorreo());
        }*/
        
    }
    
    private static Contacto obtenerContacto(Contacto c, ArrayList<Contacto> lista) {
        Contacto con = null;
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getCorreo().equals(c.getCorreo())) {
                con = lista.get(i);
                break;
            }
        }
        return con;
    }
    
}
