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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// Expresion regular para saber si hay algun numero NO seguido de otro simbolo :///////// ^(?!.*[+\-*/]).*\d.*$

/**
 * Clase Engine que implementa una calculadora con funcionalidad básica y
 * soporte para modo oscuro. Hereda de JFrame e implementa ActionListener para
 * manejar eventos de acción en los botones.
 */
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

	// Botones segunda version
	private JButton marca;
	private JLabel modo;
	private JButton info;
	private JButton owner;
	private JButton b2;
	private JButton b8;
	private JButton b10;
	private JButton b16;
	private JButton d;
	private JButton e;
	private JButton f;
	private JButton a;
	private JButton b;
	private JButton c;

	private boolean mathError;
	private boolean isOscuro;
	private int baseNum;

	// Tipos de boton
	// Sirven solo para decorar
	private enum ButtonType {
		REGULAR, OPERATOR, BASE, OWNER, HEX_DIGITS
	}

	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private String hexResult;
	private char operation;

	/**
	 * Constructor de la clase Engine. Inicializa los componentes gráficos y llama a
	 * los métodos setSettings() y addActionEvent() para configurar los elementos de
	 * la calculadora.
	 *
	 * @param title Título de la ventana de la calculadora.
	 */
	public Engine(String title) {
		// Configuracion del JFrame
		this.frame = new JFrame(title);
		// Inizalizamos el JPanel
		this.contentPanel = new JPanel();
		this.displayPanel = new JPanel();

		// Panel extra
		this.topPanel = new JPanel();
		// new GridLayout(int rows, int cols, int hgap, int vgap)
		this.buttonPanel = new JPanel(new GridLayout(8, 4, 5, 5));

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
		reset = new JButton("Ce");

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

		// Botones segunda version
		marca = new JButton("Emios");
		modo = new JLabel("Base: Decimal");
		b2 = new JButton("B2");
		b8 = new JButton("B8");
		b10 = new JButton("B10");
		b16 = new JButton("B16");
		owner = new JButton("Owner");
		info = new JButton("Info");
		d = new JButton("D");
		e = new JButton("E");
		f = new JButton("F");
		a = new JButton("A");
		b = new JButton("B");
		c = new JButton("C");
		baseNum = 10;
		hexResult = "0";

		num1 = 0;
		num2 = 0;
		result = 0;
		mathError = false;
		isOscuro = false;

		setSettings();
		addActionEvent();
	}

	/**
	 * Configura los layouts, colores y elementos gráficos de los paneles y botones.
	 * Incluye ajustes como colores, tamaños y visibilidad.
	 */
	public void setSettings() {
		this.frame.setSize(440, 720);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);

		this.contentPanel.setLayout(new BorderLayout());
		this.topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

		// Modo oscuro
		this.modoOscuro.setPreferredSize(new Dimension(40, 30));
		this.modoOscuro.setBackground(Color.white);
		this.modoOscuro.setBorder(new LineBorder(Color.black, 2, true));

		// Marca
		this.marca.setPreferredSize(new Dimension(100, 30));
		this.marca.setHorizontalAlignment(SwingConstants.CENTER);
		this.marca.setForeground(Color.black);
		this.marca.setOpaque(true);
		this.marca.setBackground(Color.WHITE);
		this.marca.setBorder(BorderFactory.createLineBorder(Color.pink, 2));

		// Decimales
		this.modo.setPreferredSize(new Dimension(150, 30));
		this.modo.setHorizontalAlignment(SwingConstants.CENTER);
		this.modo.setForeground(Color.black);
		this.modo.setOpaque(true);
		this.modo.setBackground(Color.white);
		this.modo.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));

		// Top Panel
		this.topPanel.add(modo, BorderLayout.WEST);
		this.topPanel.add(marca, BorderLayout.CENTER);
		this.topPanel.add(modoOscuro, BorderLayout.EAST);
		this.contentPanel.add(topPanel, BorderLayout.NORTH);

		// Display
		this.display.setPreferredSize(new Dimension(420, 100));
		this.display.setFont(new Font("Arial", Font.PLAIN, 40));
		this.display.setHorizontalAlignment(JTextField.RIGHT);
		this.display.setEditable(false);
		this.displayPanel.add(this.display);
		this.contentPanel.add(displayPanel, BorderLayout.CENTER);

		// Fila 1
		this.buttonPanel.add(b2);
		this.buttonPanel.add(b8);
		this.buttonPanel.add(b10);
		this.buttonPanel.add(b16);

		// Fila 2
		this.buttonPanel.add(d);
		this.buttonPanel.add(e);
		this.buttonPanel.add(f);
		this.buttonPanel.add(info);

		// Fila 3
		this.buttonPanel.add(a);
		this.buttonPanel.add(b);
		this.buttonPanel.add(c);
		this.buttonPanel.add(owner);

		// Fila 4
		this.buttonPanel.add(multiply);
		this.buttonPanel.add(divide);
		this.buttonPanel.add(x2);
		this.buttonPanel.add(x3);

		// Fila 4
		this.buttonPanel.add(subtract);
		this.buttonPanel.add(add);
		this.buttonPanel.add(del);
		this.buttonPanel.add(reset);

		// Fila 5
		this.buttonPanel.add(n7);
		this.buttonPanel.add(n8);
		this.buttonPanel.add(n9);
		this.buttonPanel.add(equal);

		// Primera fila
		this.buttonPanel.add(n4);
		this.buttonPanel.add(n5);
		this.buttonPanel.add(n6);
		this.buttonPanel.add(ans);

		// Segunda Fila
		this.buttonPanel.add(n1);
		this.buttonPanel.add(n2);
		this.buttonPanel.add(n3);
		this.buttonPanel.add(n0);

		// Aniadirlo todo
		this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);

		// Visibilidad al frame
		this.frame.add(this.contentPanel);
		this.frame.setVisible(true);

		theme();
	}

	/**
	 * Configura las características de diseño de los botones según su tipo
	 * (numérico o operador) y el tema activo (claro u oscuro).
	 *
	 * @param _button El botón a personalizar.
	 * @param _type   Tipo de botón (REGULAR u OPERATOR).
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		// Persobalizacion para todos los botones
		_button.setPreferredSize(new Dimension(80, 60));

		Color purpleBackground = new Color(75, 0, 130);
		Color pinkColor = new Color(255, 105, 180);
		Color greenColor = new Color(27, 85, 48);
		Color greenMore = new Color(2, 28, 19);
		Color orangeColor = new Color(255, 203, 30);
		Color brownColor = new Color(176, 86, 8);
		Color lightBrownColor = new Color(255, 175, 104);
		Color salmonColor = new Color(255, 169, 125);

		if (_type == ButtonType.OPERATOR) {
			_button.setFont(new Font("Arial", Font.BOLD, 20));

			// Decoracion botones de operador
			if (isOscuro == false) {
				_button.setBackground(Color.white);
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

		} else if (_type == ButtonType.REGULAR) {
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

		} else if (_type == ButtonType.HEX_DIGITS) {
			_button.setFont(new Font("Arial", Font.BOLD, 20));
			_button.setBorder(new LineBorder(Color.yellow, 2, true));
			_button.setForeground(Color.WHITE);

			if (this.isOscuro == false) {
				_button.setBackground(orangeColor);
			} else {
				_button.setBackground(new Color(190, 200, 10));
			}

			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(new Color(245, 193, 20));
					} else {
						_button.setBackground(new Color(180, 190, 0));
					}
				}

				public void mouseExited(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(orangeColor);
					} else {
						_button.setBackground(new Color(190, 200, 10));
					}
				}
			});

		} else if (_type == ButtonType.BASE) {
			_button.setFont(new Font("Arial", Font.BOLD, 20));
			_button.setBorder(new LineBorder(lightBrownColor, 2, true));
			_button.setForeground(Color.WHITE);

			if (this.isOscuro == false) {
				_button.setBackground(brownColor);
			} else {
				_button.setBackground(new Color(146, 46, 80));
			}

			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(new Color(166, 76, 0));
					} else {
						_button.setBackground(new Color(136, 36, 70));
					}
				}

				public void mouseExited(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(brownColor);
					} else {
						_button.setBackground(new Color(146, 46, 80));
					}
				}
			});
		} else {
			_button.setForeground(Color.WHITE);
			_button.setBorder(new LineBorder(Color.white, 2, true));
			_button.setFont(new Font("Arial", Font.BOLD, 20));

			if (this.isOscuro == false) {
				_button.setBackground(salmonColor);
			} else {
				_button.setBackground(new Color(205, 109, 75));
			}

			// Efecto hover
			_button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(new Color(245, 159, 115));
					} else {
						_button.setBackground(new Color(195, 99, 65));
					}
				}

				public void mouseExited(MouseEvent e) {
					if (isOscuro == false) {
						_button.setBackground(salmonColor);
					} else {
						_button.setBackground(new Color(205, 109, 75));
					}
				}
			});
		}

		if (this.isOscuro == true) {
			this.contentPanel.setBackground(Color.black);
			this.topPanel.setBackground(Color.black);
			this.displayPanel.setBackground(Color.black);
			this.buttonPanel.setBackground(Color.black);
			this.display.setBackground(Color.black);
			this.display.setForeground(Color.white);
		} else {
			this.contentPanel.setBackground(purpleBackground);
			this.topPanel.setBackground(purpleBackground);
			this.displayPanel.setBackground(purpleBackground);
			this.buttonPanel.setBackground(purpleBackground);
			this.display.setBackground(Color.white);
			this.display.setForeground(Color.black);
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
	 * Registra los ActionListener para todos los botones de la calculadora.
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

		JButton[] bases = { b2, b8, b10, b16 };

		for (JButton base : bases) {
			base.addActionListener(this);
		}

		JButton[] hexDecimals = { d, e, f, a, b, c };

		for (JButton hex : hexDecimals) {
			hex.addActionListener(this);
		}

		JButton[] ownerInfo = { info, owner, marca };

		for (JButton owner : ownerInfo) {
			owner.addActionListener(this);
		}
	}

	/**
	 * Realiza la operación matemática correspondiente según el valor del atributo
	 * `operation`. Actualiza el resultado y muestra el texto en el display.
	 */
	public void operation() {
		switch (this.operation) {
		case '+':
			getBaseResult(this.num1, this.num2, "+");
			break;
		case '-':
			getBaseResult(this.num1, this.num2, "-");
			break;
		case '*':
			getBaseResult(this.num1, this.num2, "*");
			break;
		case '/':
			if (this.num2 == 0) {
				this.display.setText("Math ERROR");
				this.mathError = true;
				return;
			}

			getBaseResult(this.num1, this.num2, "/");
			break;
		}

		if (baseNum != 16) {
			this.display.setText(Integer.toString(this.result));
		} else {
			this.display.setText(this.hexResult);
		}
	}

	public void getBaseResult(int _num1, int _num2, String operador) {
		int tempResult = 0;

		if (operador == "+") {
			tempResult = _num1 + _num2;
		} else if (operador == "-") {
			tempResult = _num1 - _num2;
		} else if (operador == "*") {
			tempResult = _num1 * _num2;
		} else if (operador == "/") {
			tempResult = _num1 / _num2;
		}

		// https://www.geeksforgeeks.org/java-program-to-add-two-binary-strings/
		if (baseNum == 10) {
			this.result = tempResult;
		} else if (baseNum == 2) {
			this.result = Integer.parseInt(Integer.toBinaryString(_num1 + _num2));
		} else if (baseNum == 8) {
			this.result = Integer.parseInt(Integer.toOctalString(tempResult));
		} else if (baseNum == 16) {
			this.hexResult = Integer.toHexString(tempResult).toUpperCase();
		} else {
			this.mathError = true;
		}
	}

	/**
	 * Maneja los eventos de acción para los botones, como calcular resultados,
	 * modificar el display o alternar entre modos de tema.
	 *
	 * @param e El evento de acción disparado.
	 */
	public void actionPerformed(ActionEvent e) {
		// Recogemos el tipo de boton que se ha pulsado y su texto
		Object source = e.getSource();
		String input_text = e.getActionCommand();
		String displayText = this.display.getText();
		String regExp = "";
		VentanaEmergente ventanaEmergente;
		// System.out.println("Se ha pulsado " + input_text);

		if (input_text.equals("=") && !this.mathError) {
			ArrayList<String> digits = new ArrayList<String>();

			// Cuando la base es decimal
			if (baseNum == 10) {
				regExp = "((?<=^|[^\\d])-?\\d+(?=$|[^\\d])|[+\\-*\\/()])";
			} else if (baseNum == 2) { // Cuando la base esta en binario
				regExp = "((?<=^|[^01])-?[01]+(?=$|[^01])|[+\\-*\\/()])";
			} else if (baseNum == 8) { // En caso de que sea octañ
				regExp = "((?<=^|[^0-7])-?[0-7]+(?=$|[^0-7])|[+\\-*\\/()])";
			} else if (baseNum == 16) { // En caso de que sea hexadeciml
				regExp = "((?<=^|[^0-9a-fA-F])-?[0-9a-fA-F]+(?=$|[^0-9a-fA-F])|[+\\-*\\/()])";
			} else { // En caso de que no sea ninguna
				this.mathError = true;
			}

			Pattern pDigit = Pattern.compile(regExp);
			Matcher mDigit = pDigit.matcher(displayText);

			while (mDigit.find()) {
				digits.add(mDigit.group(0));
			}

			try {
				this.operation = digits.get(1).charAt(0);

				if (baseNum == 10) {
					this.num1 = Integer.parseInt(digits.get(0));
					this.num2 = Integer.parseInt(digits.get(2));
				} else if (baseNum == 2) {
					this.num1 = Integer.parseInt(digits.get(0), 2);
					this.num2 = Integer.parseInt(digits.get(2), 2);
				} else if (baseNum == 8) {
					this.num1 = Integer.parseInt(digits.get(0), 8);
					this.num2 = Integer.parseInt(digits.get(2), 8);
				} else {
					this.num1 = Integer.parseInt(digits.get(0), 16);
					this.num2 = Integer.parseInt(digits.get(2), 16);
				}

				operation();
			} catch (Exception e2) {
				System.out.println("Faltan parametros o el numero proporcionado es incorrecto " + e2);
				this.mathError = true;
			}
		} else if (input_text.equals("Owner") || input_text.equals("Info")) {
			ventanaEmergente = new VentanaEmergente(input_text);
		} else if (input_text.equals("Emios")) {
			// Desktop support
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("https://github.com/Emanuel-Hosu"));
				} catch (IOException eve) {
					System.out.println("Se ha producido un a la hora de abrir la url " + eve);
					eve.printStackTrace();
				} catch (URISyntaxException ev) {
					System.out.println("Se ha producido un a la hora de abrir la url " + ev);
				}
			}
		} else if (input_text.equals("B2")) {
			changeNumBase(2);
		} else if (input_text.equals("B8")) {
			changeNumBase(8);
		} else if (input_text.equals("B10")) {
			changeNumBase(10);
		} else if (input_text.equals("B16")) {
			changeNumBase(16);
		} else if (input_text.equals("Ce")) {
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
				this.display.setText("Syntax ERROR");
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
	
	/**
	 * Cambia la base numérica de los números en la calculadora (binario, octal, decimal o hexadecimal).
	 * Convierte el número actual en pantalla desde la base actual a la nueva base especificada.
	 *
	 * @param newBase La nueva base numérica a la que se quiere convertir el número.
	 */
	public void changeNumBase(int newBase) {
		String displayText = display.getText();

		try {
			// Convertimos el numero segun la base anterior que tenia
			if (!displayText.isEmpty()) {
				int decimalValue = Integer.parseInt(displayText, this.baseNum);

				switch (newBase) {
				case 2:
					display.setText(Integer.toBinaryString(decimalValue));
					break;
				case 8:
					display.setText(Integer.toOctalString(decimalValue));
					break;
				case 10:
					display.setText(Integer.toString(decimalValue));
					break;
				case 16:
					display.setText(Integer.toHexString(decimalValue).toUpperCase());
					break;
				}
			}

		} catch (NumberFormatException e) {
			display.setText("Syntax ERROR");
			this.mathError = true;
		}
		
		if(!this.mathError) {
			this.baseNum = newBase;
			this.modo.setText("Base: " + getBaseName(this.baseNum));
		}
		
	}
	
	/**
	 * Obtiene el nombre descriptivo de una base numérica.
	 *
	 * @param base La base numérica (2 para binario, 8 para octal, 10 para decimal, 16 para hexadecimal).
	 * @return El nombre de la base numérica como una cadena de texto.
	 */
	public String getBaseName(int base) {
		switch (base) {
		case 2:
			return "Binario";
		case 8:
			return "Octal";
		case 10:
			return "Decimal";
		case 16:
			return "Hexadecimal";
		default:
			return "Desconocido";
		}
	}

	/**
	 * Eleva el número más reciente en el display al exponente indicado.
	 *
	 * @param cadena   Cadena de texto actual en el display.
	 * @param elevador Exponente al que se elevará el número.
	 * @return Cadena actualizada con el resultado de la operación.
	 */
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

	/**
	 * Calcula el cuadrado de un número.
	 *
	 * @param num Número a elevar al cuadrado.
	 * @return El cuadrado del número.
	 */
	public int getSquare(int num) {
		return num * num;
	}

	/**
	 * Calcula el cubo de un número.
	 *
	 * @param num Número a elevar al cubo.
	 * @return El cubo del número.
	 */
	public int getCubed(int num) {
		return num * num * num;
	}

	/**
	 * Aplica el tema actual (claro u oscuro) a los botones y paneles de la
	 * calculadora.
	 */
	public void theme() {
		Pattern pButtonType = Pattern.compile("^\\d");
		Pattern pHexDigit = Pattern.compile("^[A-F]$");
		Pattern pBaseType = Pattern.compile("^B\\d+$");

		for (int i = 0; i < buttonPanel.getComponentCount(); i++) {
			JButton tempButton = (JButton) buttonPanel.getComponent(i);
			Matcher mButtonType = pButtonType.matcher(tempButton.getText());
			if (mButtonType.find()) {
				this.setFeaturesButton(tempButton, ButtonType.REGULAR);
			} else {
				this.setFeaturesButton(tempButton, ButtonType.OPERATOR);

				// Comprobar si es un Hex Digit
				Matcher mHexDigit = pHexDigit.matcher(tempButton.getText());
				Matcher mBaseType = pBaseType.matcher(tempButton.getText());
				if (mHexDigit.find()) {
					this.setFeaturesButton(tempButton, ButtonType.HEX_DIGITS);
				} else if (mBaseType.find()) {
					this.setFeaturesButton(tempButton, ButtonType.BASE);
				} else if (tempButton.getText().length() >= 4) {
					this.setFeaturesButton(tempButton, ButtonType.OWNER);
				}
			}
		}

	}

}
