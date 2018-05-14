// TODO: Print out sets nicely  (Set 1, set 2...) or no sets if none exist

public class Solver {

	private Card[] cards;

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	/* Returns true if given set of 3 cards is a set */
	public boolean isSet(Card[] cards) {

		return (uniqueDimensionValues(cards, "Shape") || sameDimensionValues(cards, "Shape"))
			&& (uniqueDimensionValues(cards, "Color") || sameDimensionValues(cards, "Color"))
			&& (uniqueDimensionValues(cards, "Number") || sameDimensionValues(cards, "Number"))
			&& (uniqueDimensionValues(cards, "Shading") || sameDimensionValues(cards, "Shading"));

	}

	/* Returns true if the given dimension value is unique across three cards */
	public boolean uniqueDimensionValues(Card[] cards, String dimension) {
		switch(dimension) {
			case("Shape"): 
				return cards[0].getShape() != cards[1].getShape() &&
				cards[0].getShape() != cards[2].getShape() &&
				cards[1].getShape() != cards[2].getShape();

			case("Color"):
				return cards[0].getColor() != cards[1].getColor() &&
				cards[0].getColor() != cards[2].getColor() &&
				cards[1].getColor() != cards[2].getColor();
			case("Number"):
				return cards[0].getNumber() != cards[1].getNumber() &&
				cards[0].getNumber() != cards[2].getNumber() &&
				cards[1].getNumber() != cards[2].getNumber();
			case("Shading"):
				return cards[0].getShading() != cards[1].getShading() &&
				cards[0].getShading() != cards[2].getShading() &&
				cards[1].getShading() != cards[2].getShading();
		}
		return false;
	}


	/* Returns true if the given dimension value is the same across three cards */
	public boolean sameDimensionValues(Card[] cards, String dimension) {
		switch(dimension) {
			case("Shape"): 
				return cards[0].getShape() == cards[1].getShape() &&
				cards[0].getShape() == cards[2].getShape() &&
				cards[1].getShape() == cards[2].getShape();

			case("Color"):
				return cards[0].getColor() == cards[1].getColor() &&
				cards[0].getColor() == cards[2].getColor() &&
				cards[1].getColor() == cards[2].getColor();
			case("Number"):
				return cards[0].getNumber() == cards[1].getNumber() &&
				cards[0].getNumber() == cards[2].getNumber() &&
				cards[1].getNumber() == cards[2].getNumber();
			case("Shading"):
				return cards[0].getShading() == cards[1].getShading() &&
				cards[0].getShading() == cards[2].getShading() &&
				cards[1].getShading() == cards[2].getShading();
		}
		return false;
	}


	/* [BRUTE FORCE] Generates all possible combinations of 3 given array of cards*/
	public void generateCardCombinations() {
		//arr, data, 0, n-1, 0, r
		int[] temp = new int[3];
		cardCombinationsUtil(temp, 0, 2, 0);
	}

	/* Helper function that generates combinations of cards recursively */
	public void cardCombinationsUtil(int temp[], int beginning, int end, int curr) {

		// Reached end of a given combination (three elements)
		if (curr == 3) {
			//System.out.print(data[j]
			// Return if it's a set
			Card [] cardTemp = new Card[3];
			cardTemp[0] = cards[temp[0]];
			cardTemp[1] = cards[temp[1]];
			cardTemp[2] = cards[temp[2]];

			if (isSet(cardTemp)) {
				for (int i=0; i<cardTemp.length; i++) {
					System.out.println(cardTemp[i]);
				}
				return;
			}
		}

		for (int i=beginning; i<=end && end-i+1 >= 3-curr; i++) {
			temp[curr] = beginning;
			cardCombinationsUtil(temp, beginning+1, end, curr+1);
		}

	}
	

	/* Args: number of cards in collection (multiple of 3) */
	public static void main (String[] args) {
		int totalCardCount = Integer.parseInt(args[0]);
		Card[] cards = new Card[totalCardCount];

		for (int i=0; i < totalCardCount; i++) {
			cards[i] = new Card(Shape.getRandomShape(), Color.getRandomColor(),
					Number.getRandomNumber(), Shading.getRandomShading());
		}

		//TODO: account for duplicate cards
		Solver solver = new Solver();
		solver.setCards(cards);
		solver.generateCardCombinations();
	}
}