import java.util.*;

public class Solver {

    private Card[] cards;
    private int totalSetsCounter = 0;

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getTotalNumSets() {
        return this.totalSetsCounter;
    }

    /**
     * Compares dimensions of the cards in a combination to make sure the dimensions match the set rules (dimension values are either
     * all the same or all unique). Picks the first card to pull dimensions for, to compare against the other cards
     * @param cards that makeup a potential set
     * @return true if the given set of cards is a set
     */
    public boolean isSet(Card[] cards) {
        boolean isSet = true;
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
            dimensionValues.clear();
        }

        return isSet;
    }

    /**
     * @param dimensionValues
     * @return true if the given dimension values are unique across cards (e.g. all cards have different color)
     * OR if the given dimension values match across the cards (e.g. all cards have color red)
     */
    public boolean isSetHelper(List<String> dimensionValues) {
        boolean uniqueValues = true;
        boolean sameValues = true;

        for (int i=0; i < dimensionValues.size(); i++) {
            for (int j = i+1; j< dimensionValues.size(); j++) {
                uniqueValues &= !(dimensionValues.get(i).equals(dimensionValues.get(j)));
                sameValues &= dimensionValues.get(i).equals(dimensionValues.get(j));
            }
        }

        return uniqueValues || sameValues;

    }

    /**
     * Prepares call to helper function that generates combinations of possible "sets" of size setCardCount
     * @param setCardCount size of set (as determined by user)
     */
    public void generateCardCombinations(int setCardCount) {

        int[] temp = new int[setCardCount];
        int[] cardIndeces = new int[cards.length];

        for (int i=0; i<cards.length; i++) { // Generate array with number of cards to do combiation on indeces
            cardIndeces[i] = i;
        }

        cardCombinationsUtil(cardIndeces, temp, 0, cardIndeces.length-1, 0, setCardCount);
    }

    /**
     * Generates all possible combinations of cards of size setCardCount.
     * @param cardIndeces Combination values generated using card indeces (e.g. 1-3)
     * @param temp temporary array to hold each interim combination
     * @param beginning starting index of the combination
     * @param end  last index of the combination
     * @param curr current index in "cardIndeces" that we're working with
     * @param setCardCount size of the set
     */
    public void cardCombinationsUtil(int cardIndeces[], int temp[], int beginning, int end, int curr, int setCardCount) {
        if (curr == setCardCount) {
            Card[] cardTemp = new Card[setCardCount];

            // For each combination of indeces, map indeces to cards in the Cards array
            // and verify whether that set is valid
            for (int i=0; i<setCardCount; i++) {
                cardTemp[i] = cards[temp[i]];
            }

            if (isSet(cardTemp)) {
                totalSetsCounter++;
                System.out.println("----- Set " + totalSetsCounter + " Found -----");
                printSet(cardTemp);
            }
            return;
        }

        for (int i = beginning; i <= end && (end-i + 1 >= setCardCount - curr); i++) {
            temp[curr] = cardIndeces[i];
            cardCombinationsUtil(cardIndeces, temp, i+1, end, curr+1, setCardCount);
        }
    }

    /**
     * Prints out set of cards in nice format
     * @param set set of cards that compose a valid set to print out
     */
    public void printSet(Card set[]) {
        for (int i = 0; i < set.length; i++) {
            System.out.println("Card " + i);
            System.out.println(set[i]);
        }
    }

    /**
     * Randomly generates array of cards with stub card data for testing
     * @param totalCards total number of cards in set game
     * @return array of cards with randomly-generated attributes/dimensions
     */
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

        /* Generates random set of cards from options above */
        for (int i = 0; i<totalCards; i++) {
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
        if (args.length == 0 || args.length == 1) {
            System.out.println("Usage: first arg should be number of cards in collection (multiple of 3) and second arg should be number of cards that make up a set (>=3).");
            return;
        }

        int totalCardCount = Integer.parseInt(args[0]);
        int setCardCount = Integer.parseInt(args[1]);

        if (setCardCount < 3) {
            System.out.println("Usage: Second arg, number of cards in a set, must be at least 3");
            return;
        }

        //TODO: account for duplicate cards
        Solver solver = new Solver();
        Card [] cards = solver.generateCardsForTesting(totalCardCount);
        solver.setCards(cards);
        solver.generateCardCombinations(setCardCount);

        if (solver.getTotalNumSets() == 0) {
            System.out.println("No sets found.");
        } else {
            System.out.println("----------------");
            System.out.println(solver.getTotalNumSets() + " total sets found.");
        }
    }
}