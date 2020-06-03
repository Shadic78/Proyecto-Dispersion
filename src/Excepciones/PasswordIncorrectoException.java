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
public class PasswordIncorrectoException extends Exception {

    @Override
    public String getMessage() {
        return "La contraseña es incorrecta";
    }
    
}
