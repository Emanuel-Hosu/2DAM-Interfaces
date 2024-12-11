package Main;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine extends JFrame {
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

	// Tipos de boton
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
		this.frame.setName(title);
	}

	/**
	 * CONCRETAMENTE, SE ENCARGA DE,ENTRE OTRAS COSAS: PONER LOS LAYOUTS DE LOS
	 * PANELES Y AÑADIRLOS, AÑADIR LOS BOTONES Y LLAMAR AL MÉTODO
	 * SETFEATURESBUTTON(),
	 */
	public void setSettings() {

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

	}

	/**
	 * ESTE MÉTODO REGISTRA LOS ACTIONLISTENER PARA TODOS LOS BOTONES DE LA
	 * APLICACIÓN. ES DECIR, PARA CADA BOTÓN, AÑADE UN ACTIONLISTENER() QUE RECIBE
	 * COMO PARÁMETRO EL OBJETO THIS PARA PODER IDENTIFICAR EL OBJETO (BOTÓN) QUE SE
	 * PULSA.
	 */
	public void addActionEvent() {

	}

	/**
	 * COMPRUEBA QUÉ OPERACIÓN SE DEBE REALIZAR. EN OTRAS PALABRAS: MIRA EL ESTADO
	 * ACTUAL DEL ATRIBUTO THIS.OPERATION Y, EN FUNCIÓN DE ESE VALOR, LLEVA A CABO
	 * UNA OPERACIÓN U OTRA (CON LOS ATRIBUTOS THIS.NUM1 Y THIS.NUM2, QUE
	 * REPRESENTAN LOS DOS ÚNICOS OPERANDO QUE MANEJA NUESTRA CALCULADORA),
	 * MODIFICANDO EL TRIBUTO THIS.RESULT Y ACTUALIZANDO EL TEXTO EN EL DISPLAY
	 */
	public void operation() {

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
	}
}
