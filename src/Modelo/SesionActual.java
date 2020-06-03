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
    private static ArbolB arbol;
    private static Contacto usuario;
    
    public static void cargarDatos(Contacto usuario) {
        SesionActual.usuario = usuario;
    }
    
}
