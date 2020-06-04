/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import Modelo.Contacto;
import java.io.Serializable;
import java.util.ArrayList;


public class Key<T extends Comparable<T>> implements Comparable<Key<T>>, Serializable {

    private T elemento;

    public Key(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    @Override
    public int compareTo(Key<T> o) {
        return elemento.compareTo(o.getElemento());
    }

    @Override
    public String toString() {
        return elemento.toString();
    }

}
