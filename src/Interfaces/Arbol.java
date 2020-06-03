/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Equipo1
 */
public interface Arbol <T extends Comparable<T>> {

    public void insertar(T elemento, int indice);
    
    public Nodo <T> buscar(T elemento);
    
    public void inOrden();
    
    public void preOrden();
    
    public void posOrden();
    
    public void setRaiz(T raiz);

}
