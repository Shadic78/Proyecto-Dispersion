/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ArbolB.ArbolB;

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
    
}
