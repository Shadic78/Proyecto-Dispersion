/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ArbolB.ArbolB;
import Excepciones.NoDatosException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo1
 */
public class SesionActual {
    private static String rutaArbolB;
    private static ArbolB arbol;
    private static Contacto usuario;
    
    public static void cargarDatos(Contacto usuario) {
        SesionActual.usuario = usuario;
        SesionActual.rutaArbolB = ArbolesUsuarios.getRutaArbol(usuario.getCorreo());
        SesionActual.arbol = ArbolesUsuarios.cargarArbol(usuario);
        SesionActual.validarMisContactos();
    }
    
    public static void agregarContacto(Contacto c) {
        arbol.add(c);
        ArbolesUsuarios.guardarArbol(arbol, rutaArbolB, usuario.getCorreo());
    }
    
    public static Contacto getContacto(String correo) {
        Contacto c = null;
        ArrayList<Contacto> lista = SesionActual.listarMisContactos();
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getCorreo().equals(correo)) {
                c = lista.get(i);
                break;
            }
        }
        return c;
    }
    
    public static ArrayList<Contacto> listarMisContactos() {
        ArrayList<Contacto> misContactos = new ArrayList<>();        
        try {
            misContactos = arbol.enlistarElementos();
        } catch (NoDatosException ex) {
            System.out.println("No datos exception listarMisContactos");
            //Logger.getLogger(SesionActual.class.getName()).log(Level.SEVERE, null, ex);
        }
        return misContactos;
    }
    
    public static void borrarCuenta() {
        UsuariosRegistrados.borrarUsuario(usuario);
    }
    
    public static void eliminarContacto(Contacto c) {
        ArrayList<Contacto> misContactos = SesionActual.listarMisContactos();
        for(int i = 0; i < misContactos.size(); i++) {
            if(misContactos.get(i).getCorreo().equals(c.getCorreo())) {
                arbol.remove(misContactos.get(i));
                ArbolesUsuarios.guardarArbol(arbol, rutaArbolB, SesionActual.getCorreo());
                break;
            }
        }
    }
    
    public static void agregarTodosLosContactos(Contacto c) {
        ArrayList<Contacto> contactos = UsuariosRegistrados.getContactos(c);
        for(int i = 0; i < contactos.size(); i++) {
            SesionActual.agregarContacto(contactos.get(i));
        }
    }
    
    public static void validarMisContactos() {
        ArrayList<Contacto> usuarios = UsuariosRegistrados.getTodosLosUsuarios();
        ArrayList<Contacto> misContactos = SesionActual.listarMisContactos();
        System.out.println("Registrados: " + usuarios);
        System.out.println("Mis contactos: " + misContactos);
        int sizeContactos = misContactos.size();
        boolean estaRegistrado = false;
        
        for(int i = 0; i < misContactos.size(); i++) {
            estaRegistrado = false;
            for(int j = 0; j < usuarios.size(); j++) {
                if(misContactos.get(i).getCorreo().equals(usuarios.get(j).getCorreo())) {
                    estaRegistrado = true;
                    break;
                }
            }
            if(!estaRegistrado) {
                misContactos.remove(i);
            }
        }
        
        if(sizeContactos > misContactos.size()) {
            ArbolB<Contacto> arbolNuevo = new ArbolB<>();
            for(int i = 0; i < misContactos.size(); i++) {
                arbolNuevo.add(misContactos.get(i));
            }
            SesionActual.setArbol(arbolNuevo);
            ArbolesUsuarios.guardarArbol(arbolNuevo, rutaArbolB, SesionActual.getCorreo());
        }
    }
    
    public static String getNombre() {
        return SesionActual.usuario.getNombre();
    }
    
    public static String getCorreo() {
        return SesionActual.usuario.getCorreo();
    }    
    
    public static int getEdad() {
        return SesionActual.usuario.getEdad();
    }    
    
    public static void setArbol(ArbolB arbol) {
        SesionActual.arbol = arbol;
    }
    
}
