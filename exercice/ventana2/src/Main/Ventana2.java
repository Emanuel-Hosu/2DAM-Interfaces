package Main;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ventana2 extends JFrame{
	private Container panelPrincipal;
	private JLabel etiqueta;
	
	
	public Ventana2(String _msg) {
		super(_msg);
		// Contenedor principal
		this.panelPrincipal = this.getContentPane();
		this.etiqueta = new JLabel("Bienvenido a mi segundo conversor");
		// Inserto el componente en el contenedor
		this.panelPrincipal.add(this.etiqueta);
		// Establezco el resto de propiedades de la ventana
		this.setLocation(50, 100);
		this.setSize(400, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
