package Main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaEmergente extends JFrame {

	/* Marco de la ventana */
	private JFrame frame;
	/* Panel general que ocupa toda la ventana */
	private JPanel contentPanel;
	/* Panel sur que contiene el boton de ok */
	private JPanel southPanel;
	/* Panel norte que contiene el texto */
	private JPanel northPanel;
	/* JLabel para dar la info de la ventana en la que estan */
	private JLabel information;
	/* Boton de okey para cerrar la ventana */
	private JButton okButton;
	/* String para identificar que ventana abrir */
	private String frameType;

	public VentanaEmergente(String _frameType) {
		frameType = _frameType;
		frame = new JFrame("Ventana de " + _frameType);
		contentPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();
		okButton = new JButton("Ok");
		information = new JLabel();

		setSettings();
	}

	public void setSettings() {
		this.frame.setSize(700, 420);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

		if (this.frameType.equals("Owner")) {
			this.information.setText("<html>¡Hola! Soy Emi, el genio detrás de esta calculadora. 😎<br>"
					+ "Me gusta programar, hacer clics random y convencer a Java de que no se cuelgue.<br>"
					+ "Si algo salió mal... ¡no es mi culpa, fue culpa del compilador! 🙃<br>"
					+ "¡Gracias por usar mi calculadora y no hacer matemáticas a mano como en la prehistoria!✨</html>");
		} else if (frameType.equals("Project")) {
			this.information.setText("<html>¡Bienvenid@ al proyecto de Interfaces de DAM! 🎉<br>"
					+ "Esta no es una calculadora cualquiera... ¡es una 'todopoderosa'! 😎<br>"
					+ "Suma, resta, multiplica, divide y posiblemente hasta hace café ☕ (en desarrollo).<br>"
					+ "También soporta varias bases. Échale un ojo; si algo parece raro, es porque el profe lo pidió así <br>"
					+ "Creada con amor, esfuerzo y unos cuantos bugs convertidos en features.<br>"
					+ "Si encuentras algo raro... es intencional. Se llama 'interfaz intuitiva'. 😉<br>"
					+ "¡Gracias por usarla y no confiar tu suerte a una calculadora de bolsillo! ✨</html>");
		}
		
		this.northPanel.add(this.information);
		this.contentPanel.add(this.northPanel, BorderLayout.NORTH);
		this.frame.add(this.contentPanel);
	}
}
