
package vista;


import entidades.Futbolista;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.RegistroGoleadores;

/**
 *
 * @author jairo
 */
public class VentanaConsulta extends JDialog {
    private Container contenedor;
    private JPanel panelFiltro, panelBase;
    private JScrollPane panelResultado;
    private JLabel lFiltro;
    private JTextField tFiltro;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private String titulos[]={"No doc", "Nombre", "Equipo", "No Goles", "No Partidos", "Promedio"};
    private RegistroGoleadores modelo;
    
    
    public VentanaConsulta(JFrame frame, boolean bln) {
        super(frame, bln);
        this.modelo = new RegistroGoleadores();
        this.setTitle("Consulta de goleadores - V1");
        this.iniciarComponentes();
        //this.pack(); 
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.actualizarTabla();
        this.setVisible(true);
    }
    
    public void iniciarComponentes(){
        this.contenedor = this.getContentPane();
        this.panelBase = new JPanel();
        this.panelBase.setLayout(new BorderLayout());
        this.panelBase.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.iniciarPanelFiltro();
        this.iniciarPanelResultado();
        this.contenedor.add(this.panelBase);
    }
    
    public void iniciarPanelFiltro(){
        this.panelFiltro = new JPanel();
        this.panelFiltro.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        this.lFiltro= new JLabel("Filtro busqueda: ");
        this.tFiltro= new JTextField(10);
        this.tFiltro.addKeyListener(new eventoTecladoFiltro());
        
        this.panelFiltro.add(this.lFiltro);
        this.panelFiltro.add(this.tFiltro);
        
        this.panelBase.add(this.panelFiltro, BorderLayout.NORTH);
        
       
    }
    
    public void actualizarTabla(){
        String filtro = this.tFiltro.getText();
        try{
            List<Futbolista> lista = this.modelo.leer();
            this.modeloTabla.setNumRows(0);
            for(Futbolista f: lista){
                String fila[] = {f.getCc(), f.getNombre(),f.getEquipo(), String.valueOf(f.getNoGoles()), String.valueOf(f.getNoPartidos()), String.valueOf(f.getPromedioGoles())};
                if(filtro!=null){
                    if(f.getEquipo().toUpperCase().contains(filtro.toUpperCase())){
                         this.modeloTabla.addRow(fila);
                     }
                }    
                else{
                     this.modeloTabla.addRow(fila);
                }
            }
            
        }catch(IOException ex){
            VentanaEmergente.msgConfirmacion("Excepcion", ex.getMessage(), JOptionPane.ERROR_MESSAGE, this);
        }    
        
    }
    
    
    
    
    
    public void iniciarPanelResultado(){
        
        this.panelResultado = new JScrollPane();
        
        this.tabla = new JTable();
        this.modeloTabla = new DefaultTableModel(null, this.titulos);
        this.tabla.setModel(modeloTabla);
        this.panelResultado.setViewportView(this.tabla);
        
        this.panelBase.add(this.panelResultado, BorderLayout.CENTER);
    
    }
    
    class eventoTecladoFiltro extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
             actualizarTabla();
        }
    }
    
    
    
    
    
    
}
