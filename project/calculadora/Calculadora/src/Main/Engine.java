package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class Engine extends JFrame implements ActionListener {
	// Marco de la ventana
	private JFrame frame;
	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene el display
	private JPanel displayPanel;
	// Panel sur que contiene los botones
	private JPanel buttonPanel;
	// Panel extra para el modo oscuro
	private JPanel topPanel;
	// Display
	private JTextField display;
	// Botones
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton divide;
	private JButton multiply;
	private JButton subtract;
	private JButton add;
	private JButton equal;
	private JButton reset;
	// Botones extra
	private JButton modoOscuro;
	private JButton del;
	private JButton x2;
	private JButton x3;
	private JButton ans;
	private JButton log;
	private JButton sin;
	private JButton cos;
	private JButton tan;
	private JButton ln;

	private boolean mathError;
	private boolean isOscuro;

	// Tipos de boton
	// Sirven solo para decorar
	private enum ButtonType {
		REGULAR, OPERATOR
	}

	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;

	/**
	 * LA CONSTRUCTORA DEBE INSTANCIAR, ENTRE OTRAS COSAS, LOS ATRIBUTOS QUE SE HAN
	 * MENCIONADO ANTERIORMENTE.
	 * 
	 * AL FINAL DE LA CONSTRUCTORA DEBE APARECER UNA LLAMADA A SETSETTINGS() Y
	 * ADDACTIONEVENT().
	 * 
	 * @param title
	 */
	public Engine(String title) {
		// Configuracion del JFrame
		this.frame = new JFrame(title);
		// Inizalizamos el JPanel
		this.contentPanel = new JPanel();
		this.displayPanel = new JPanel();

		// Panel extra
		this.topPanel = new JPanel();
		// Los dos 4 indican la cantidad de filas, columnas y el primer 5 la separacion
		// entre las columnas en horizontal y el otro la separacion en vertical
		this.buttonPanel = new JPanel(new GridLayout(5, 5, 5, 5));

		this.display = new JTextField();

		// Bbotones numéricos
		n0 = new JButton("0");
		n1 = new JButton("1");
		n2 = new JButton("2");
		n3 = new JButton("3");
		n4 = new JButton("4");
		n5 = new JButton("5");
		n6 = new JButton("6");
		n7 = new JButton("7");
		n8 = new JButton("8");
		n9 = new JButton("9");

		// Bbotones de operadores
		divide = new JButton("/");
		multiply = new JButton("*");
		subtract = new JButton("-");
		add = new JButton("+");
		equal = new JButton("=");
		reset = new JButton("C");

		// Botones extra
		modoOscuro = new JButton("🌙");
		del = new JButton("DEL");
		x2 = new JButton("^2");
		x3 = new JButton("^3");
		ans = new JButton("Ans");
		log = new JButton("log");
		sin = new JButton("sin");
		cos = new JButton("cos");
		tan = new JButton("tan");
		ln = new JButton("ln");

		num1 = 0;
		num2 = 0;
		result = 0;
		mathError = false;
		isOscuro = false;

		setSettings();
		addActionEvent();
	}

	/**
	 * CONCRETAMENTE, SE ENCARGA DE,ENTRE OTRAS COSAS: PONER LOS LAYOUTS DE LOS
	 * PANELES Y AÑADIRLOS, AÑADIR LOS BOTONES Y LLAMAR AL MÉTODO
	 * SETFEATURESBUTTON(),
	 * 
	 * AÑADIRLE LOS COLORES
	 */
	public void setSettings() {
		this.frame.setSize(440, 520);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);

		this.contentPanel.setLayout(new BorderLayout());
		
		Color purpleBackground = new Color(75, 0, 130);
		this.contentPanel.setBackground(purpleBackground);
		this.topPanel.setBackground(purpleBackground);
		this.displayPanel.setBackground(purpleBackground);
		this.buttonPanel.setBackground(purpleBackground);

		this.topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.modoOscuro.setPreferredSize(new Dimension(40, 40));
		this.modoOscuro.setBackground(Color.white);
		this.modoOscuro.setBorder(new LineBorder(Color.black, 2, true));
		this.topPanel.add(modoOscuro, BorderLayout.EAST);
		this.contentPanel.add(topPanel, BorderLayout.NORTH);

		this.display.setPreferredSize(new Dimension(420, 100));
		this.display.setFont(new Font("Arial", Font.PLAIN, 40));
		this.display.setHorizontalAlignment(JTextField.RIGHT);
		this.display.setEditable(false);
		this.displayPanel.add(this.display);
		this.contentPanel.add(displayPanel, BorderLayout.CENTER);

		// Fila 0 aniadida extra
		this.buttonPanel.add(log);
		this.buttonPanel.add(sin);
		this.buttonPanel.add(cos);
		this.buttonPanel.add(tan);
		this.buttonPanel.add(ln);

		// Primera fila
		this.buttonPanel.add(n7);
		this.buttonPanel.add(n8);
		this.buttonPanel.add(n9);
		this.buttonPanel.add(del);
		this.buttonPanel.add(reset);

		// Segunda Fila
		this.buttonPanel.add(n4);
		this.buttonPanel.add(n5);
		this.buttonPanel.add(n6);
		this.buttonPanel.add(multiply);
		this.buttonPanel.add(divide);

		// Tercera fila
		this.buttonPanel.add(n1);
		this.buttonPanel.add(n2);
		this.buttonPanel.add(n3);
		this.buttonPanel.add(add);
		this.buttonPanel.add(subtract);

		// Cuarta fila
		this.buttonPanel.add(n0);
		this.buttonPanel.add(x2);
		this.buttonPanel.add(x3);
		this.buttonPanel.add(ans);
		this.buttonPanel.add(equal);

		// Aniadirlo todo
		this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);

		// Visibilidad
		this.frame.add(this.contentPanel);
		this.frame.setVisible(true);

		theme();
	}

	public void setFeaturesButton(JButton _button, ButtonType _type) {
		// Persobalizacion para todos los botones
		_button.setPreferredSize(new Dimension(80, 60));

		Color purpleBackground = new Color(75, 0, 130);
		Color pinkColor = new Color(255, 105, 180);
		Color greenColor = new Color(27, 85, 48);
		Color greenMore = new Color(2, 28, 19);

		if (_type == ButtonType.OPERATOR) {
			_button.setFont(new Font("Arial", Font.BOLD, 20));

			// Decoracion botones de operador
			if (isOscuro == false) {
				_button.setBackground(Color.WHITE);
				_button.setForeground(purpleBackground);
				_button.setBorder(new LineBorder(pinkColor, 2, true));
			} else {
				_button.setBackground(greenMore);
				_button.setForeground(Color.white);
				_button.setBorder(new LineBorder(greenColor, 2, true));
			}

			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(new Color(240, 240, 240));
					} else {
						_button.setBackground(new Color(0, 18, 9));
					}
				}

				public void mouseExited(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(Color.WHITE);
					} else {
						_button.setBackground(greenMore);
					}
				}
			});

		} else {
			// Decoracion general
			_button.setForeground(Color.WHITE);
			_button.setFont(new Font("Arial", Font.PLAIN, 18));
			_button.setBorder(new LineBorder(Color.white, 2, true));

			if (this.isOscuro == false) {
				_button.setBackground(pinkColor);
			} else {
				_button.setBackground(purpleBackground);
			}

			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(new Color(255, 182, 193));
					} else {
						_button.setBackground(new Color(65, 0, 120));
					}
				}

				public void mouseExited(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(pinkColor);
					} else {
						_button.setBackground(purpleBackground);
					}
				}
			});
			
			if (this.isOscuro == true) {
				this.contentPanel.setBackground(Color.black);
				this.topPanel.setBackground(Color.black);
				this.displayPanel.setBackground(Color.black);
				this.buttonPanel.setBackground(Color.black);
				this.display.setBackground(Color.black);
				this.display.setForeground(Color.white);
			}
		}

		// Efecto de presionado para todos los botones
		_button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				_button.setBackground(_button.getBackground().darker());
			}

			public void mouseReleased(MouseEvent evt) {
				_button.setBackground(_button.getBackground().brighter());
			}
		});
	}

	/**
	 * ESTE MÉTODO REGISTRA LOS ACTIONLISTENER PARA TODOS LOS BOTONES DE LA
	 * APLICACIÓN. ES DECIR, PARA CADA BOTÓN, AÑADE UN ACTIONLISTENER() QUE RECIBE
	 * COMO PARÁMETRO EL OBJETO THIS PARA PODER IDENTIFICAR EL OBJETO (BOTÓN) QUE SE
	 * PULSA.
	 */
	public void addActionEvent() {
		JButton[] numberButtons = { n0, n1, n2, n3, n4, n5, n6, n7, n8, n9 };

		for (JButton numbers : numberButtons) {
			numbers.addActionListener(this);
		}

		JButton[] operatorButtons = { divide, multiply, subtract, add, equal, reset, del, x2, x3, ans, log, sin, cos,
				tan };

		for (JButton operators : operatorButtons) {
			operators.addActionListener(this);
		}

		JButton modoOscuro = this.modoOscuro;
		modoOscuro.addActionListener(this);
	}

	/**
	 * COMPRUEBA QUÉ OPERACIÓN SE DEBE REALIZAR. EN OTRAS PALABRAS: MIRA EL ESTADO
	 * ACTUAL DEL ATRIBUTO THIS.OPERATION Y, EN FUNCIÓN DE ESE VALOR, LLEVA A CABO
	 * UNA OPERACIÓN U OTRA (CON LOS ATRIBUTOS THIS.NUM1 Y THIS.NUM2, QUE
	 * REPRESENTAN LOS DOS ÚNICOS OPERANDO QUE MANEJA NUESTRA CALCULADORA),
	 * MODIFICANDO EL TRIBUTO THIS.RESULT Y ACTUALIZANDO EL TEXTO EN EL DISPLAY
	 */
	public void operation() {
		switch (this.operation) {
		case '+':
			this.result = this.num1 + this.num2;
			break;
		case '-':
			this.result = this.num1 - this.num2;
			break;
		case '*':
			this.result = this.num1 * this.num2;
			break;
		case '/':
			if (this.num2 == 0) {
				this.display.setText("Math ERROR");
				this.mathError = true;
				return;
			}
			this.result = this.num1 / this.num2;
			break;
		}

		this.display.setText(Integer.toString(this.result));
	}

	/**
	 * ESTE MÉTODO SE ENCARGA DE OBTENER LA INFORMACIÓN QUE HAYA EN EL DISPLAY
	 * (NÚMEROS INTRODUCIDOS Y OPERACIÓN QUE SE DEBE REALIZAR) Y LLAMAR AL MÉTODO
	 * OPERATION() PARA EJECUTAR DICHA OPERACIÓN.
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		// Recogemos el tipo de boton que se ha pulsado y su texto
		Object source = e.getSource();
		String input_text = e.getActionCommand();
		String displayText = this.display.getText();
		// System.out.println("Se ha pulsado " + input_text);

		if (input_text.equals("=") && !this.mathError) {
			ArrayList<String> digits = new ArrayList<String>();
			Pattern pDigit = Pattern.compile("((?<=^|[^\\d])-?\\d+(?=$|[^\\d])|[+\\-*\\/()])");
			Matcher mDigit = pDigit.matcher(displayText);

			while (mDigit.find()) {
				digits.add(mDigit.group(0));
			}

			try {
				this.operation = digits.get(1).charAt(0);
				this.num1 = Integer.parseInt(digits.get(0));
				this.num2 = Integer.parseInt(digits.get(2));

				operation();
			} catch (Exception e2) {
				System.out.println("Faltan parametros " + e2);
				this.mathError = true;
			}
		} else if (input_text.equals("C")) {
			this.display.setText("");
			this.mathError = false;
		} else if (input_text.equals("DEL")) {
			try {
				displayText = displayText.substring(0, displayText.length() - 1);
				this.display.setText(displayText);
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("No se ha podido borrar ningun numero " + e2);
			}

		} else if (input_text.equals("^2")) {
			try {
				displayText = elevarNumero(displayText, 2);
				this.display.setText(displayText);
			} catch (Exception e2) {
				System.out.println("Error al elevar al cuadrado " + e2);
				this.mathError = true;
			}
		} else if (input_text.equals("^3")) {
			try {
				displayText = elevarNumero(displayText, 3);
				this.display.setText(displayText);
			} catch (Exception e2) {
				System.out.println("Error al elevar al cubo " + e2);
				this.mathError = true;
			}
		} else if (input_text.equals("Ans")) {
			this.display.setText(displayText + this.result);
		} else if (input_text.equals("🌙") || input_text.equals("🌞")) {
			if (this.isOscuro == true) { // En caso de que sea modo oscuro
				this.isOscuro = false;
				this.modoOscuro.setText("🌙");
				theme();
			} else { // En caso de que no sea modo oscuro
				this.isOscuro = true;
				this.modoOscuro.setText("🌞");
				theme();
			}
		} else {
			if (!this.mathError) {
				this.display.setText(displayText + input_text);
			}

		}
	}

	public String elevarNumero(String cadena, int elevador) {
		if (cadena.isEmpty()) {
			return "";
		}

		StringBuilder numero = new StringBuilder();
		int i = cadena.length() - 1;
		boolean encontradoNumero = false;

		while (i >= 0) {
			char c = cadena.charAt(i);
			if (Character.isDigit(c)) {
				// Insert en la posicion 0 la C
				numero.insert(0, c);
				encontradoNumero = true;
				// Comprueba si C vale -, en caso de que valga menos comprueba que si i esta en
				// la posicion 0 y si antes del - no hay otro caracter, manejo de numeros
				// negativos --
			} else if (c == '-' && i > 0 && !Character.isDigit(cadena.charAt(i - 1))) {
				numero.insert(0, c);
				System.out.println(numero.toString());
				break;
			} else if (encontradoNumero) {
				break;
			}
			i--;
		}

		// safety check
		if (numero.length() == 0) {
			return cadena;
		}

		int num = Integer.parseInt(numero.toString());
		int resultado = 0;

		if (elevador == 2) {
			resultado = getSquare(num);
		} else {
			resultado = getCubed(num);
		}

		return cadena.substring(0, i + 1) + resultado;
	}

	public int getSquare(int num) {
		return num * num;
	}

	public int getCubed(int num) {
		return num * num * num;
	}
	
	public void theme() {
		Pattern pButtonType = Pattern.compile("^\\d");

		for (int i = 0; i < buttonPanel.getComponentCount(); i++) {
			JButton tempButton = (JButton) buttonPanel.getComponent(i);
			Matcher mButtonType = pButtonType.matcher(tempButton.getText());

			if (mButtonType.find()) {
				this.setFeaturesButton(tempButton, ButtonType.REGULAR);
			} else {
				this.setFeaturesButton(tempButton, ButtonType.OPERATOR);
			}
		}
	}
}
