import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SolverTest {
	/*  Future Unit Test for Solver class
		cards[0] = new Card(Shape.SQUIGGLE, Color.RED, Number.ONE, Shading.OUTLINED);
		cards[1] = new Card(Shape.DIAMOND, Color.RED, Number.ONE, Shading.OUTLINED);
		cards[2] = new Card(Shape.OVAL, Color.RED, Number.ONE, Shading.OUTLINED);

		for (int i=3; i< totalCardCount;  i++) {
			cards[i] = new Card(Shape.getRandomShape(), Color.getRandomColor(),
					Number.getRandomNumber(), Shading.getRandomShading());
		}
	 */

    /* Generates few cards that should actually be a set*/
	@Before
    public void setUp() {
	    Card [] cards = new Card[9];

        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        colors.add("Black");
        colors.add("Purple");

        List<String> backgroundcolors = new ArrayList<>();
        backgroundcolors.add("Red");
        backgroundcolors.add("Green");
        backgroundcolors.add("Blue");
        backgroundcolors.add("Yellow");
        backgroundcolors.add("Black");
        backgroundcolors.add("Purple");

        List<String> shading = new ArrayList<>();
        shading.add("Stripe");
        shading.add("Solid");
        shading.add("None");
        shading.add("Dotted");

        List<String> shape = new ArrayList<>();
        shape.add("Triangle");
        shape.add("Square");
        shape.add("Rectangle");
        shape.add("Squiggle");
        shape.add("Oval");

        List<String> numbers = new ArrayList<>();
        numbers.add("One");
        numbers.add("Two");
        numbers.add("Three");
        numbers.add("Four");

        Map<String,String> dimensions = new HashMap<>();
        dimensions.put("Color", "Yellow");
        dimensions.put("Background Color", "Red");
        dimensions.put("Shading", "Solid");
        dimensions.put("Shape", "Triangle");
        dimensions.put("Number", "One");

        cards[0] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Yellow");
        dimensions.put("Background Color", "Green");
        dimensions.put("Shading", "Solid");
        dimensions.put("Shape", "Triangle");
        dimensions.put("Number", "One");

        cards[1] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Yellow");
        dimensions.put("Background Color", "Black");
        dimensions.put("Shading", "Solid");
        dimensions.put("Shape", "Triangle");
        dimensions.put("Number", "One");

        cards[2] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Green");
        dimensions.put("Background Color", "Purple");
        dimensions.put("Shading", "None");
        dimensions.put("Shape", "Oval");
        dimensions.put("Number", "Three");

        cards[3] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Red");
        dimensions.put("Background Color", "Yellow");
        dimensions.put("Shading", "Stripe");
        dimensions.put("Shape", "Squiggle");
        dimensions.put("Number", "Two");

        cards[4] = new Card(dimensions);

        for (int i=5; i<9; i++) { //TODO: update to total cards
            String color = colors.get(new Random().nextInt(colors.size()));
            String backgroundcolor = backgroundcolors.get(new Random().nextInt(backgroundcolors.size()));
            String shadingVal = shading.get(new Random().nextInt(shading.size()));
            String shapeVal = shape.get(new Random().nextInt(shape.size()));
            String number = numbers.get(new Random().nextInt(numbers.size()));

            dimensions = new HashMap<>();
            dimensions.put("Color", color);
            dimensions.put("Background Color", backgroundcolor);
            dimensions.put("Shading", shadingVal);
            dimensions.put("Shape", shapeVal);
            dimensions.put("Number", number);

            cards[i] = new Card(dimensions);
        }
    }

	@Test
    public void testSolverGame() {

    }

}