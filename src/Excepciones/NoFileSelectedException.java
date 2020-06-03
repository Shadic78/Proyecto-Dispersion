/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Equipo1
 */
public class NoFileSelectedException extends Exception {

    @Override
    public String getMessage() {
        return "No se ha seleccionado ningun archivo"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
