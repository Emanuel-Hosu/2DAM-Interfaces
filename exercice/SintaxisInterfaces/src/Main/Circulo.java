package Main;

public class Circulo implements FiguraGeometrica {
	private float radio;

	public Circulo(float radio) {
		this.radio = radio;
	}

	public float area() {
		return (float) (PI * radio * radio);
	}

	public void drawFiguraGeometrica(){
		System.out.println("Radio del círculo: " + this.radio);
	}
}