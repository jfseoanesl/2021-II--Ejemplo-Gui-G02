/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jairo
 */
public class VentanaPrincipal extends JFrame implements ActionListener {
    
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemMenuRegistro, itemMenuConsulta;

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
        
        this.itemMenuRegistro = new JMenuItem("Registrar");
        this.itemMenuRegistro.addActionListener(this);
        this.menu.add(this.itemMenuRegistro);
        
        this.itemMenuConsulta = new JMenuItem("Consulta");
        this.itemMenuConsulta.addActionListener(this);
        this.menu.add(itemMenuConsulta);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.itemMenuRegistro){
           
            VentanaRegistro registro = new VentanaRegistro(this, true);
        
        }
        else{
            
            VentanaConsulta consulta = new VentanaConsulta(this, true);
        }
        
    }
    
   
    
    
}
