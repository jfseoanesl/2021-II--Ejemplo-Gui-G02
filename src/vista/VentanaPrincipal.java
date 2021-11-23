/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;

/**
 *
 * @author jairo
 */
public class VentanaPrincipal extends JFrame {
    
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemMenu;

    public VentanaPrincipal() {
        
        this.initComponentes();
        this.setTitle("Aplicacion de registro de goleadores - FPC - Ventana Principal");
        //this.setSize(300, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    
    public void initComponentes(){
        
        this.barraMenu = new JMenuBar();
        this.setJMenuBar(this.barraMenu);
        
        this.menu = new JMenu("Opciones");
        this.barraMenu.add(this.menu);
        
        this.itemMenu = new JMenuItem("Registrar");
        this.menu.add(this.itemMenu);
        
    }
    
    
}
