/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import Modelo.Contacto;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Equipo1
 */
public class ComparatorNodos implements Comparator<NodoB<Contacto>>, Serializable {

    @Override
    public int compare(NodoB<Contacto> o1, NodoB<Contacto> o2) {
        return o1.getKey(0).getElemento().compareTo(o2.getKey(0).getElemento());
    }

}
