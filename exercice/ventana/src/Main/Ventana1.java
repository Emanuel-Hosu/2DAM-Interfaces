package Main;

import javax.swing.JFrame;

public class Ventana1 extends JFrame {
	public Ventana1(String _msg) {
		super(_msg);
		this.setVisible(true);
		this.setLocation(50, 100);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
