package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine extends JFrame implements ActionListener {
	// Marco de la ventana
	private JFrame frame;
	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene el display
	private JPanel displayPanel;
	// Panel sur que contiene los botones
	private JPanel buttonPanel;
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

	private boolean mathError;
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
		displayPanel = new JPanel();
		// Los dos 4 indican la cantidad de filas, columnas y el primer 5 la separacion
		// entre las columnas en horizontal y el otro la separacion en vertical
		buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

		// Inicializar display
		display = new JTextField();

		// Inicializar botones numéricos
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

		// Inicializar botones de operadores
		divide = new JButton("/");
		multiply = new JButton("*");
		subtract = new JButton("-");
		add = new JButton("+");
		equal = new JButton("=");
		reset = new JButton("C");

		num1 = 0;
		num2 = 0;
		result = 0;
		mathError = false;

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
		this.frame.setSize(500, 700);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);

		this.contentPanel.setBackground(Color.LIGHT_GRAY);
		this.contentPanel.setLayout(new FlowLayout());

		// Configuracion del panel norte y panel sur
		display.setPreferredSize(new Dimension(480, 100));
		display.setFont(new Font("Arial", Font.PLAIN, 30));
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);
		displayPanel.add(display);
		contentPanel.add(displayPanel);

		// Configurando botones en el frame
		buttonPanel.add(n1);
		buttonPanel.add(n2);
		buttonPanel.add(n3);
		buttonPanel.add(equal);
		buttonPanel.add(n4);
		buttonPanel.add(n5);
		buttonPanel.add(n6);
		buttonPanel.add(add);
		buttonPanel.add(n7);
		buttonPanel.add(n8);
		buttonPanel.add(n9);
		buttonPanel.add(subtract);
		buttonPanel.add(n0);
		buttonPanel.add(reset);
		buttonPanel.add(multiply);
		buttonPanel.add(divide);
		contentPanel.add(buttonPanel);

		// Frame visibility
		this.frame.add(contentPanel);
		this.frame.setVisible(true);
		
		Pattern pButtonType = Pattern.compile("\\d");
		
		for (int i = 0; i < buttonPanel.getComponentCount(); i++) {
			JButton tempButton = (JButton) buttonPanel.getComponent(i);
			Matcher mButtonType = pButtonType.matcher(tempButton.getText());
			
			if (mButtonType.find()) {
				this.setFeaturesButton(tempButton, ButtonType.REGULAR);
			}else {
				this.setFeaturesButton(tempButton, ButtonType.OPERATOR);
			}
		}
	}

	/**
	 * CONTIENE UNA CONDICIÓN QUE PERMITE DISTINGUIR SI EL TIPO DE BOTÓN PASA COMO
	 * PARÁMETRO ES DE TIPO REGULAR U OPERATOR.
	 * 
	 * EN FUNCIÓN DE ESTO, PINTARÁ EL BOTÓN DE UN COLOR U OTRO. PUEDES AÑADIRLE (Y
	 * ES ALGO QUE SE TENDRÁ EN CUENTA) MÁS CARACTERÍSTICAS TALES COMO CAMBIO DEL
	 * TIPO DE LETRA, BORDES, ETC.
	 * 
	 * @param _button identifica el botón sobre el que se van a cambiar las
	 *                características.
	 * @param _type   identifica de qué tipo es el botón sobre el que se van a
	 *                cambiar las características.
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		// Si este boton es de tipo regular = color turquesa
		// Si este boton es de tipo operador = color azuL
		if (_type == ButtonType.OPERATOR) {
			
		}else {
			
		}
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

		JButton[] operatorButtons = { divide, multiply, subtract, add, equal, reset };

		for (JButton operators : operatorButtons) {
			operators.addActionListener(this);
		}

	}

	/**
	 * COMPRUEBA QUÉ OPERACIÓN SE DEBE REALIZAR. EN OTRAS PALABRAS: MIRA EL ESTADO
	 * ACTUAL DEL ATRIBUTO THIS.OPERATION Y, EN FUNCIÓN DE ESE VALOR, LLEVA A CABO
	 * UNA OPERACIÓN U OTRA (CON LOS ATRIBUTOS THIS.NUM1 Y THIS.NUM2, QUE
	 * REPRESENTAN LOS DOS ÚNICOS OPERANDO QUE MANEJA NUESTRA CALCULADORA),
	 * MODIFICANDO EL TRIBUTO THIS.RESULT Y ACTUALIZANDO EL TEXTO EN EL DISPLAY
	 */
	public void operation() {
		try {
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
					display.setText("Math ERROR");
					this.mathError = true;
					return;
				}
				this.result = this.num1 / this.num2;
				break;
			}

		}catch(

	NumberFormatException e)
	{
			display.setText("Error: First input must be a numbre or a negative number");
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
		String displayText = display.getText();

		if (input_text.equals("=") && !this.mathError) {
			ArrayList<String> digits = new ArrayList<String>();
			Pattern pDigit = Pattern.compile("((?<=^|[^\\d])-?\\d+(?=$|[^\\d])|[+\\-*\\/()])");
			Matcher mDigit = pDigit.matcher(displayText);
			
			while (mDigit.find()) {
				digits.add(mDigit.group(0));
			}

			this.operation = digits.get(1).charAt(0);
			this.num1 = Integer.parseInt(digits.get(0));
			this.num2 = Integer.parseInt(digits.get(2));
			
			operation();
		} else if (input_text.equals("C")) {
			display.setText("");
			this.mathError = false;
		} else {
			if (!this.mathError) {
				display.setText(displayText + input_text);
			}
			
		}
	}
}
