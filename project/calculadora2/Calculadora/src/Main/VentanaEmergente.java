package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEmergente extends JFrame implements ActionListener {

	/** Marco principal de la ventana */
	private JFrame frame;
	/** Panel general que ocupa toda la ventana */
	private JPanel contentPanel;
	/** Panel inferior que contiene el botón de "Ok" */
	private JPanel southPanel;
	/** Panel central que contiene los botones de las redes sociales */
	private JPanel centerPanel;
	/** Panel superior que contiene el texto de información */
	private JPanel northPanel;
	/** Etiqueta que muestra la información del contenido de la ventana */
	private JLabel information;
	/** Botón de "Ok" para cerrar la ventana */
	private JButton okButton;
	/** Botón que redirige al perfil de GitHub */
	private JButton github;
	/** Botón que redirige al perfil de LinkedIn */
	private JButton linkedin;
	/** Identificador del tipo de ventana a abrir ("Owner" o "Info") */
	private String frameType;

	/**
	 * Constructor de la clase VentanaEmergente.
	 *
	 * @param _frameType Tipo de ventana a mostrar, puede ser "Owner" o "Info".
	 */
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
		addActionListener();
	}

	/**
	 * Configura los ajustes visuales y funcionales de la ventana emergente, como su
	 * tamaño, disposición de los paneles, colores y contenido dinámico según el
	 * tipo de ventana. Ademas de incorporar los disenios de cada ventana
	 */
	public void setSettings() {
		this.frame.setSize(770, 520);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

		this.contentPanel.setLayout(new BorderLayout(20, 20));

		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new GridLayout(1, 2, 150, 30));

		if (this.frameType.equals("Owner")) {
			Color redBackground = new Color(153, 20, 8);
			this.frame.getContentPane().setBackground(redBackground);
			this.contentPanel.setBackground(redBackground);
			this.northPanel.setBackground(redBackground);
			this.centerPanel.setBackground(redBackground);
			this.southPanel.setBackground(redBackground);
			buttonContainer.setBackground(redBackground);

			this.information.setText("<html>" + "<div style=\"display: flex; align-items: center;\">"
					+ "    <h1 style=\"color: white; margin-right: 10px; font-family: Verdana, Geneva, Tahoma, sans-serif;\">Emanuel Hosu</h1>"
					+ "    <div style=\"margin-left: 180px;\">"
					+ "        <img src=\"https://img.utdstc.com/icon/71b/a84/71ba84705548280991e54bbb30f5d72f00885d19e178c962d393549c8b10db5b:200\" style=\"width: 40px; height: auto;\">"
					+ "    </div>" + "</div>"
					+ "    <div style=\"font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; margin-top: 10px; color: white\">\r\n"
					+ "        <p>¡Heyyy! Si estás aquí, es por algo. Me llamo Emanuel, pero todos me dicen Emi. \r\n"
					+ "        <p>Actualmente estudio 2º de Desarrollo de Aplicaciones Multiplataforma (DAM), y esta</p>\r\n"
					+ "        <p>es mi súper calculadora, no olvides de darme un toque ;)</p>\r\n" + "    </div>"
					+ "</html>");
		} else if (frameType.equals("Info")) {
			Color blueBackGround = new Color(0, 111, 100);
			this.frame.getContentPane().setBackground(blueBackGround);
			this.contentPanel.setBackground(blueBackGround);
			this.northPanel.setBackground(blueBackGround);
			this.centerPanel.setBackground(blueBackGround);
			this.southPanel.setBackground(blueBackGround);
			buttonContainer.setBackground(blueBackGround);

			this.information.setText("<html>" + "<div style=\"display: flex; align-items: center;\">"
					+ "    <h1 style=\"color: white;margin-left: 80px; margin-right: 10px; font-family: Verdana, Geneva, Tahoma, sans-serif;\">About the project</h1>"
					+ "    <div style=\"margin-left: 100px;\">"
					+ "        <img src=\"https://i.blogs.es/4fe9ab/outer-wilds-2-/200_200.jpeg\" style=\"width: 40px; height: auto;\">"
					+ "    </div>" + "</div>"
					+ "    <div style=\"font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; margin-top: 10px; color: white\">\r\n"
					+ "        <p>¡Hola, soy Emi y esta es la versión 2 de mi calculadora, donde hemos ampliado las. \r\n"
					+ "        <p>funcionalidades para incluir cálculos en sistemas binario, octal, hexadecimal y,</p>\r\n"
					+ "        <p> por supuesto, decimal. Además de añadir varias cositas más. Este proyecto es para</p>\r\n"
					+ " <p> la asignatura de Interfaces de 2 DAM. Si quieres saber mas ya sabes ;)</p>" + "    </div>"
					+ "</html>");
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
		JButton[] buttons = { this.github, this.linkedin, this.okButton };
		for (JButton button : buttons) {
			setFeatures(button);
		}

	}

	/**
	 * Configura el estilo y los efectos de hover para los botones.
	 *
	 * @param _button Botón al que se aplicarán las configuraciones.
	 */
	public void setFeatures(JButton _button) {
		_button.setFont(new Font("Arial", Font.BOLD, 12));
		// _button.setBorder(new LineBorder(Color.yellow, 2, true));
		_button.setForeground(Color.WHITE);

		Color darkButton = new Color(23, 23, 23);
		Color blueButton = new Color(10, 102, 194);

		if (_button.getText().equals("GitHub")) { // Si el boton es de github
			_button.setBackground(darkButton);
			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					_button.setBackground(new Color(13, 13, 13));
				}

				public void mouseExited(MouseEvent e) {
					_button.setBackground(darkButton);
				}
			});
		} else if (_button.getText().equals("Linkedin")) { // Si el boton es de linkedin
			_button.setBackground(blueButton);
			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					_button.setBackground(new Color(0, 82, 174));
				}

				public void mouseExited(MouseEvent e) {
					_button.setBackground(blueButton);
				}
			});
		} else {
			_button.setBackground(Color.white);
			_button.setForeground(darkButton);
			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					_button.setBackground(new Color(0, 82, 174));
				}

				public void mouseExited(MouseEvent e) {
					_button.setBackground(Color.white);
				}
			});
		}
	}

	/**
	 * Añade los ActionListener a los botones para manejar sus eventos.
	 */
	public void addActionListener() {
		JButton[] emergentButtons = { okButton, github, linkedin };

		for (JButton button : emergentButtons) {
			button.addActionListener(this);
		}
	}

	/**
	 * Maneja los eventos de acción generados por los botones.
	 *
	 * @param e Evento de acción generado.
	 */
	public void actionPerformed(ActionEvent e) {
		String input_text = e.getActionCommand();
		Desktop desktop = Desktop.getDesktop();

		if (input_text.equals("Ok")) {
			this.frame.dispose();
		} else if (input_text.equals("GitHub")) {
			try {
				desktop.browse(new URI("https://github.com/Emanuel-Hosu"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (input_text.equals("Linkedin")) {
			try {
				desktop.browse(new URI("https://www.linkedin.com/in/emanuel-hosu/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
