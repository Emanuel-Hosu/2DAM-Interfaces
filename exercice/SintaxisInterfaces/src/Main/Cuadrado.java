package Main;

public class Cuadrado implements FiguraGeometrica {
	private float lado;

	public Cuadrado(float lado) {
		this.lado = lado;
	}

	public float area() {
		return lado * lado;
	}

	public void drawFiguraGeometrica(){
		System.out.println("Longitud lado cuadrado: " + this.lado);
	}
}