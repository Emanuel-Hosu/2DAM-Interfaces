package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEmergente extends JFrame {

	/* Marco de la ventana */
	private JFrame frame;
	/* Panel general que ocupa toda la ventana */
	private JPanel contentPanel;
	/* Panel sur que contiene el boton de ok */
	private JPanel southPanel;
	/* Panel sur que contiene los botones de las redes*/
	private JPanel centerPanel;
	/* Panel norte que contiene el texto */
	private JPanel northPanel;
	/* JLabel para dar la info de la ventana en la que estan */
	private JLabel information;
	/* Boton de okey para cerrar la ventana */
	private JButton okButton;
	/* Boton de github */
	private JButton github;
	/* Boton de Linkedin */
	private JButton linkedin;
	/* String para identificar que ventana abrir */
	private String frameType;

	public VentanaEmergente(String _frameType) {
		frameType = _frameType;
		frame = new JFrame("Ventana de " + _frameType);
		contentPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel();
		northPanel = new JPanel();
		okButton = new JButton("Ok");
		github = new JButton("GitHub");
		linkedin = new JButton("Linkedin");
		information = new JLabel();

		setSettings();
	}

	public void setSettings() {
		this.frame.setSize(770, 520);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		
		this.contentPanel.setLayout(new BorderLayout(20, 20));
		
		Color redBackground = new Color(153, 20, 8);
	    this.frame.getContentPane().setBackground(redBackground);
	    this.contentPanel.setBackground(redBackground);
	    this.northPanel.setBackground(redBackground);
	    this.centerPanel.setBackground(redBackground);
	    this.southPanel.setBackground(redBackground);
	    
	    JPanel buttonContainer = new JPanel();
	    buttonContainer.setLayout(new GridLayout(1, 2, 150, 30));

		if (this.frameType.equals("Owner")) {
			this.information.setText("<html>"
					+ "<div style=\"display: flex; align-items: center;\">"
			        + "    <h1 style=\"color: white; margin-right: 10px; font-family: Verdana, Geneva, Tahoma, sans-serif;\">Emanuel Hosu</h1>"
			        + "    <div style=\"margin-left: 180px;\">"
			        + "        <img src=\"https://img.utdstc.com/icon/71b/a84/71ba84705548280991e54bbb30f5d72f00885d19e178c962d393549c8b10db5b:200\" style=\"width: 40px; height: auto;\">"
			        + "    </div>"
			        + "</div>"
					+ "    <div style=\"font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; margin-top: 10px; color: white\">\r\n"
					+ "        <p>¡Heyyy! Si estás aquí, es por algo. Me llamo Emanuel, pero todos me dicen Emi. \r\n"
					+ "        <p>Actualmente estudio 2º de Desarrollo de Aplicaciones Multiplataforma (DAM), y esta</p>\r\n"
					+ "        <p>es mi súper calculadora, no olvides de darme un toque ;)</p>\r\n"
					+ "    </div>"
					+ "</html>");
			 buttonContainer.setBackground(redBackground);
		} else if (frameType.equals("Project")) {
			this.information.setText("<html>¡Bienvenid@ al proyecto de Interfaces de DAM!<br>"
					+ "Esta no es una calculadora cualquiera... ¡es una 'todopoderosa'! 😎<br>"
					+ "Suma, resta, multiplica, divide y posiblemente hasta hace café ☕ (en desarrollo).<br>"
					+ "También soporta varias bases. Échale un ojo; si algo parece raro, es porque el profe lo pidió así <br>"
					+ "Creada con amor, esfuerzo y unos cuantos bugs convertidos en features.<br>"
					+ "Si encuentras algo raro... es intencional. Se llama 'interfaz intuitiva'. 😉<br>"
					+ "¡Gracias por usarla y no confiar tu suerte a una calculadora de bolsillo! ✨</html>");
		}
		this.northPanel.add(this.information);
	
		// Central panel Botones de redes sociales
		github.setPreferredSize(new Dimension(120, 40));
	    linkedin.setPreferredSize(new Dimension(120, 40));
	    buttonContainer.add(github);
	    buttonContainer.add(linkedin);
	    this.centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
	    this.centerPanel.add(buttonContainer);
		
	    // Boton de OK
		this.southPanel.add(okButton);
		
		// Content panel general
		this.contentPanel.add(northPanel, BorderLayout.NORTH);
	    this.contentPanel.add(centerPanel, BorderLayout.CENTER);
	    this.contentPanel.add(southPanel, BorderLayout.SOUTH);
		this.frame.add(this.contentPanel);
		
		// Le aniadimos color a los botones
		JButton[] buttons = {this.github, this.linkedin, this.okButton};
		for (JButton button : buttons) {
			setFeatures(button);
		}
		
		// 
	}
	
	public void setFeatures(JButton _button) {
		if (_button.getText().equals("Github")) { // Si el boton es de github
			
		}else if (_button.getText().equals("Linkedin")) { // Si el boton es de linkedin
			
		}else {
			
		}
	}
}
