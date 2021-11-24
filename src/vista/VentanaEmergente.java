/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author jairo
 */
public class VentanaEmergente {
    
    public static void msgConfirmacion(String titulo, String contenido, int icono, JDialog padre){
        
        JOptionPane.showMessageDialog(padre,contenido, titulo, icono);
    
    }
    
}
