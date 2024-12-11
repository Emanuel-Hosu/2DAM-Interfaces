package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana3 extends JFrame {
    private Container panelPrincipal;
    private JLabel etiqueta;
    private JPanel panelNorte;
    private JPanel panelSur;
    private JPanel panelCentral;
    private JLabel pedirCambio;
    private JTextField cambio;
    private JLabel pedirCantidad;
    private JTextField cantidad;
    

    public Ventana3(String _msg) {
        super(_msg);

        // Contenedor principal
        this.panelPrincipal = this.getContentPane();
        this.panelPrincipal.setLayout(new BorderLayout());

        // Configuro el panel norte
        this.panelNorte = new JPanel();
        this.panelNorte.setLayout(new FlowLayout());
        this.etiqueta = new JLabel("Bienvenido a mi conversor");
        this.panelNorte.add(this.etiqueta);

        // Agrego el panel norte al contenedor principal
        this.panelPrincipal.add(this.panelNorte, BorderLayout.NORTH);

        // Configuro botones
        JButton euroADolar = new JButton("Alvaro es un crack");
        JButton dolarAEuro = new JButton("Alvaro no es un crack");

        // Creo un panel sur para los botones
        panelSur = new JPanel();
        panelSur.setLayout(new FlowLayout());
        panelSur.add(euroADolar);
        panelSur.add(dolarAEuro);

        // Inserto el panel sur en el contenedor principal
        this.panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        pedirCambio = new JLabel("Introduce el cambio");   
        cambio = new JTextField(6);
        
        pedirCantidad = new JLabel("Cantidad");
        cantidad = new JTextField(6);
        
        panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout());
        panelCentral.add(pedirCambio);
        panelCentral.add(cambio);
        panelCentral.add(pedirCantidad);
        panelCentral.add(cantidad);
        
        this.panelPrincipal.add(panelCentral);
        
        // Configuro la ventana
        this.setLocation(50, 100);
        this.setSize(400, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
