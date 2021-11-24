/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Futbolista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import modelo.RegistroGoleadores;

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
    private RegistroGoleadores modelo;

    public VentanaRegistro(JFrame owner, boolean modal) {
        super(owner, modal);
        this.modelo = new RegistroGoleadores();
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
        
        SpinnerNumberModel modeloJSPartidos = new SpinnerNumberModel(1,1,25,1);
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
        this.bGuardar.addActionListener(new clickBotonGuardar());
        panel.add(this.bBuscar);
        this.bBuscar.addActionListener(new clickBotonBuscar());
        panel.add(this.bELiminar);
        this.bELiminar.addActionListener(new clickBotonEliminar());
        panel.add(this.bCancelar);
        this.bCancelar.addActionListener(new clickBotonCancelar());
        
        this.panelBotones = new JPanel();
         this.panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelBotones.setLayout(new FlowLayout());
        
        this.panelBotones.add(panel);
        
        this.contenedor.add(this.panelBotones, BorderLayout.EAST);
        
    
    }
    
    public Futbolista leerDatos(){
        
        Futbolista f = new Futbolista();
        Object  cc = this.ftCc.getValue();
        if(cc!=null)
            f.setCc(this.ftCc.getValue().toString()); // por ser JFormattedTextField
        else
            f.setCc(null);
        
        f.setNombre(this.tNombre.getText());
        
        f.setEquipo(this.cbEquipo.getSelectedItem().toString());
        f.setNoGoles(Integer.valueOf(this.jsGoles.getValue().toString()));
        f.setNoPartidos(Integer.valueOf(this.jsPartidos.getValue().toString()));
        f.setPromedioGoles();
        return f;
    }
    
    
    public void guardar(){
        
        Futbolista f = this.leerDatos();
        
        try {
            this.modelo.registrar(f);
            VentanaEmergente.msgConfirmacion("Registro Exitoso", "EL futbolista se almaceno correctamente", JOptionPane.INFORMATION_MESSAGE, this);

        } catch (IOException ex) {
            VentanaEmergente.msgConfirmacion("Excepcion", ex.getMessage(), JOptionPane.ERROR_MESSAGE, this);
            
        }
        catch(NullPointerException ne){
            VentanaEmergente.msgConfirmacion("Validacion", ne.getMessage(), JOptionPane.ERROR_MESSAGE, this);
        }
        
        
    }
    
    //escuchador evento click para boton guardar
    class clickBotonGuardar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Click en guardar");
            guardar();
        }
        
    }
     //escuchador evento click para boton buscar
    class clickBotonBuscar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Click en buscar");
        }
        
    }
    
    //escuchador evento click para boton eliminar
    class clickBotonEliminar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    
    //escuchador evento click para boton cancelar
    class clickBotonCancelar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    
    
}
