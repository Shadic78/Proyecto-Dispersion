/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo1
 */
public class ModeloTablaContactos {

    public void rellenarTabla(JTable tabla, ArrayList<Contacto> datos) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Correo electrónico");
        model.addColumn("Edad");
        for (int i = 0; i < datos.size(); i++) {
            Contacto c = datos.get(i);
            model.addRow(new String[]{c.getNombre(), c.getCorreo(), Integer.toString(c.getEdad())});
        }
        tabla.setModel(model);
    }
    
}
