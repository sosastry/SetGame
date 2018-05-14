// TODO: Print out sets nicely  (Set 1, set 2...) or no sets if none exist

import java.util.*;

public class Solver {

    private Card[] cards;

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }


    /**
     * Compares dimensions of three cards to make sure the dimensions match the set rules (dimension values are either
     * all the same or all unique).
     *
     * Line 29: Picks the first card to pull dimensions for, to compare against the other cards
     *
     * @param cards
     * @return true if the given set of cards is a set
     */
    public boolean isSet(Card[] cards) {
        boolean isSet = false;
        Set<String> dimensions = cards[0].getDimensionsMap().keySet();
        List<String> dimensionValues = new ArrayList<>();

        for (String dimension : dimensions) {

            for (Card card : cards) {
                if (!card.getDimensionsMap().containsKey(dimension)) {
                    return false;
                }

                dimensionValues.add(card.getDimensionsMap().get(dimension));
            }

            isSet &= isSetHelper(dimensionValues);
        }

        return isSet;
    }

    /* Returns true if the given dimension values are unique across cards (e.g. all cards have different color)
    * OR if the given dimension values match across the cards (e.g. all cards have color red) */
    public boolean isSetHelper(List<String> dimensionValues) {
        boolean uniqueValues = false;
        boolean sameValues = false;

        for (int i=0; i < dimensionValues.size(); i++) {
            for (int j = i+1; j< dimensionValues.size(); j++) {
                uniqueValues &= !dimensionValues.get(i).equals(dimensionValues.get(j));
                sameValues &= dimensionValues.get(i).equals(dimensionValues.get(j));
            }
        }

        return uniqueValues || sameValues;

    }

    /* Generates all possible combinations of 3 given array of cards*/
    public void generateCardCombinations(int setCardCount) {
        //arr, data, 0, n-1, 0, r
        int[] temp = new int[setCardCount];
        cardCombinationsUtil(temp, 0, 2, 0, setCardCount);
    }

    /* Helper function that generates combinations of cards recursively */
    public void cardCombinationsUtil(int temp[], int beginning, int end, int curr, int setCardCount) {

        // Reached end of a given combination (three elements)
        if (curr == setCardCount) {
            //System.out.print(data[j]
            // Return if it's a set
            Card[] cardTemp = new Card[setCardCount];

            for (int i = 0; i < setCardCount; i++) {
                cardTemp[i] = cards[temp[i]];
            }

            if (isSet(cardTemp)) {
                for (int i = 0; i < cardTemp.length; i++) {
                    System.out.println("Card " + i);
                    System.out.println(cardTemp[i]);
                }
                return;
            }
        }

        for (int i = beginning; i <= end && end - i + 1 >= 3 - curr; i++) {
            temp[curr] = beginning;
            cardCombinationsUtil(temp, beginning + 1, end, curr + 1, setCardCount);
        }
    }

    /* Randomly generates array of cards with stub card data for testing*/
    // TODO: assumptions: assuming "None" of a dimension will be listed (e.g no shading)
    public Card[] generateCardsForTesting(int totalCards) {

        Card [] cards = new Card[totalCards];

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

        String color, backgroundcolor, shadingVal, shapeVal, number;

        for (int i = 0; i<totalCards; i++) {
            /* Random randomizer = new Random();
            String random = list.get(randomizer.nextInt(list.size()));*/

            color = colors.get(new Random().nextInt(colors.size()));
            backgroundcolor = backgroundcolors.get(new Random().nextInt(backgroundcolors.size()));
            shadingVal = shading.get(new Random().nextInt(shading.size()));
            shapeVal = shape.get(new Random().nextInt(shape.size()));
            number = numbers.get(new Random().nextInt(numbers.size()));

            Map<String,String> dimensions = new HashMap<>();
            dimensions.put("Color", color);
            dimensions.put("Background Color", backgroundcolor);
            dimensions.put("Shading", shadingVal);
            dimensions.put("Shape", shapeVal);
            dimensions.put("Number", number);

            cards[i] = new Card(dimensions);
        }

        return cards;

    }


    /* Args: number of cards in collection (multiple of 3), number of cards that make up a set combo */
    public static void main(String[] args) {
        int totalCardCount = Integer.parseInt(args[0]);
        int setCardCount = Integer.parseInt(args[1]);

        //TODO: account for duplicate cards

        //TODO: check for second arg being >= 3
        Solver solver = new Solver();
        Card [] cards = solver.generateCardsForTesting(totalCardCount);
        solver.setCards(cards);
        solver.generateCardCombinations(setCardCount);
    }
}