/* Class to store individual card information for each attribute */

import java.util.Random;

public class Card {

	private Shape shape; 
	private Color color;
	private Number number;
	private Shading shading;

	public Card(Shape shape, Color color, Number number, Shading shading) {
		this.shape = shape;
		this.color = color;
		this.number = number;
		this.shading = shading;
	} 

	public Shape getShape() {
		return this.shape;
	}

	public Color getColor() {
		return this.color;
	}

	public Number getNumber() {
		return this.number;
	}

	public Shading getShading() {
		return this.shading;
	}

	public String toString() {
		return "Color: " + getColor() + ", Shape: " + getShape() +  ", Number: " + getNumber()
				+ " Shading: " + getShading();
	}

}


enum Shape {
	OVAL,
	SQUIGGLE,
	DIAMOND;

	private static final Shape[] shapes = values();
	private static final Random random_number = new Random();

	public static Shape getRandomShape() {
		return shapes[random_number.nextInt(shapes.length)];
	}
}

enum Color {
	RED,
	PURPLE,
	GREEN;

	private static final Color[] colors = values();
	private static final Random random_number = new Random();

	public static Color getRandomColor() {
		return colors[random_number.nextInt(colors.length)];
	}
}

enum Number {
	ONE,
	TWO,
	THREE;

	private static final Number[] numbers = values();
	private static final Random random_number = new Random();

	public static Number getRandomNumber() {
		return numbers[random_number.nextInt(numbers.length)];
	}
}

enum Shading {
	SOLID,
	STRIPED,
	OUTLINED;

	private static final Shading[] shadings = values();
	private static final Random random_number = new Random();

	public static Shading getRandomShading() {
		return shadings[random_number.nextInt(shadings.length)];
	}
} 