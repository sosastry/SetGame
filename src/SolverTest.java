import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SolverTest {

    private Card[] randomCards;
    private Card[] validSet;
    private Card[] invalidSet;

    /* Generates card arrays with valid set, invalid set and random set*/
	@Before
    public void setUp() {
	    randomCards = new Card[9];
	    validSet = new Card[3];
	    invalidSet = new Card[3];

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

        validSet[0] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Yellow");
        dimensions.put("Background Color", "Green");
        dimensions.put("Shading", "Solid");
        dimensions.put("Shape", "Triangle");
        dimensions.put("Number", "One");

        validSet[1] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Yellow");
        dimensions.put("Background Color", "Black");
        dimensions.put("Shading", "Solid");
        dimensions.put("Shape", "Triangle");
        dimensions.put("Number", "One");

        validSet[2] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Green");
        dimensions.put("Background Color", "Purple");
        dimensions.put("Shading", "None");
        dimensions.put("Shape", "Oval");
        dimensions.put("Number", "Three");

        randomCards[0] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Red");
        dimensions.put("Background Color", "Yellow");
        dimensions.put("Shading", "Stripe");
        dimensions.put("Shape", "Squiggle");
        dimensions.put("Number", "Two");

        randomCards[1] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Red");
        dimensions.put("Background Color", "Yellow");
        dimensions.put("Shading", "Stripe");
        dimensions.put("Shape", "Squiggle");
        dimensions.put("Number", "Two");

        invalidSet[0] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Green");
        dimensions.put("Background Color", "Yellow");
        dimensions.put("Shading", "Stripe");
        dimensions.put("Shape", "Squiggle");
        dimensions.put("Number", "Two");

        invalidSet[1] = new Card(dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Color", "Green");
        dimensions.put("Background Color", "Yellow");
        dimensions.put("Shading", "Stripe");
        dimensions.put("Shape", "Squiggle");
        dimensions.put("Number", "Two");

        invalidSet[2] = new Card(dimensions);

        for (int i=2; i<9; i++) {
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

            randomCards[i] = new Card(dimensions);
        }
    }

	@Test
    public void testValidSet() {
        Solver solver = new Solver();
        boolean isSet = solver.isSet(validSet);
        assertTrue(isSet);
    }

    @Test
    public void testInvalidSet() {
	    Solver solver = new Solver();
	    boolean isSet = solver.isSet(invalidSet);
	    assertFalse(isSet);
    }

    @Test
    public void testInitializeCardCollection() {
	    Solver solver = new Solver();
        Card[] cards = solver.generateCardCollection(5);
        assertEquals(5, cards.length);
    }

}