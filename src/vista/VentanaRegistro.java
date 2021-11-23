/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author jairo
 */
public class VentanaRegistro extends JDialog{
    
    private Container contenedor;
    private JPanel panelDatos, panelBotones;
    private JLabel lCc, lNombre, lEquipo, lGoles, lPartidos, lPromedio;
    private JTextField tNombre;
    private JFormattedTextField ftCc, ftPromedio;
    private JComboBox cbEquipo;
    private JSpinner jsGoles, jsPartidos;
    private JButton bGuardar, bBuscar, bELiminar, bCancelar;

    public VentanaRegistro(JFrame owner, boolean modal) {
        super(owner, modal);
        this.initComponentes();
        this.setTitle("Registro de Goleadores - Futbol Profesional Colombiano - Ventana de registro");
        //this.pack();
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void initComponentes(){
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        
        this.initPanelDatos();
        this.initPanelBotones();
    
    }
    
    
    public void initPanelDatos(){
        
        this.panelDatos = new JPanel();
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelDatos.setLayout(new GridLayout(6,2,5,5));
        
        this.lCc = new JLabel("no Doc: ");
        this.lNombre= new JLabel("Nombre: ");
        this.lEquipo= new JLabel("Equipo: ");
        this.lGoles= new JLabel("No goles: ");
        this.lPartidos= new JLabel("No partidos: ");
        this.lPromedio= new JLabel("Promedio: ");
        
        this.tNombre = new JTextField(null);
        
        try {
            MaskFormatter mascaraCC= new MaskFormatter("#######");
            this.ftCc = new JFormattedTextField(mascaraCC);
            
            MaskFormatter mascaraProm= new MaskFormatter("#.####");
            this.ftPromedio = new JFormattedTextField(mascaraProm);
            this.ftPromedio.setEditable(false);
            
        } catch (ParseException ex) { }
        
        this.cbEquipo = new JComboBox();
        this.cbEquipo.addItem("Junior");
        this.cbEquipo.addItem("Nacional");
        this.cbEquipo.addItem("America");
        this.cbEquipo.addItem("Millonarios");
        this.cbEquipo.addItem("Cali");
        
        SpinnerNumberModel modeloJSGoles = new SpinnerNumberModel(0,0,100,1);
        this.jsGoles = new JSpinner(modeloJSGoles);
        
        SpinnerNumberModel modeloJSPartidos = new SpinnerNumberModel(0,0,25,1);
        this.jsPartidos = new JSpinner(modeloJSPartidos);
        
        this.panelDatos.add(this.lCc);
        this.panelDatos.add(this.ftCc);
        
        this.panelDatos.add(this.lNombre);
        this.panelDatos.add(this.tNombre);
        
        this.panelDatos.add(this.lEquipo);
        this.panelDatos.add(this.cbEquipo);
        
        this.panelDatos.add(this.lGoles);
        this.panelDatos.add(this.jsGoles);
        
        this.panelDatos.add(this.lPartidos);
        this.panelDatos.add(this.jsPartidos);
        
        this.panelDatos.add(this.lPromedio);
        this.panelDatos.add(this.ftPromedio);
       
        
        this.contenedor.add(this.panelDatos, BorderLayout.CENTER);
        
        
    
    }
    
    public void initPanelBotones(){
        JPanel panel = new JPanel();
       
        panel.setLayout(new GridLayout(4,1,5,5));
        
        this.bGuardar = new JButton("Guardar");
        this.bCancelar = new JButton("Cancelar");
        this.bELiminar = new JButton("Eliminar");
        this.bBuscar=new JButton("Buscar");
        
        panel.add(this.bGuardar);
        panel.add(this.bBuscar);
        panel.add(this.bELiminar);
        panel.add(this.bCancelar);
        
        this.panelBotones = new JPanel();
         this.panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelBotones.setLayout(new FlowLayout());
        
        this.panelBotones.add(panel);
        
        this.contenedor.add(this.panelBotones, BorderLayout.EAST);
        
    
    }
    
    
    
    
}
