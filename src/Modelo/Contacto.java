/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Equipo1
 */
public class Contacto implements Serializable {
    private String nombre;
    private int edad;
    private String correo;
    private String password;

    public Contacto(String nombre, int edad, String correo, String password) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", edad=" + edad + ", correo=" + correo + ", password=" + password + '}';
    }
    
}
