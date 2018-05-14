/* Class to store individual card information for each attribute */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Card {

	/* Dimensions hashmap that stores arbitrary number of dimensions and their respective values for the card
	* Ex: "Color": "Red", "Shape": "Oval", "BackgroundColor" : "Red" */
	private Map<String, String> dimensionsMap;


	public Map<String, String> getDimensionsMap() {
		return dimensionsMap;
	}

	// Initializes Dimensions Map
	public Card(Map<String,String> dimensionsMap) {
		this.dimensionsMap = new HashMap<>();
		this.dimensionsMap = dimensionsMap;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (String dimension : this.dimensionsMap.keySet()) {
			sb.append(dimension + ": " + dimensionsMap.get(dimension) + "\n");
		}

		return sb.toString();
	}
}
