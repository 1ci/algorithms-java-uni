/**
 * @author Hristo Hristov
 */
package coursework;

import java.util.List;
import java.util.ArrayList;

class Truck {
	
	static final int DEFAULT_WIDTH = 200;
	static final int DEFAULT_HEIGHT = 100;
	static final int DEFAULT_L = 100;

	final List<Pile> piles;
	final int width;
	final int height;
	final int L;
	
	Truck() {
		piles = new ArrayList<Pile>();
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		L = DEFAULT_L;
	}
	
	Truck(int width, int height) {
		piles = new ArrayList<Pile>();
		this.width = width;
		this.height = height;
		L = DEFAULT_L;
	}
	
	Truck(int width, int height, int L) {
		piles = new ArrayList<Pile>();
		this.width = width;
		this.height = height;
		this.L = L;
	}
	
	/**
	 * The number of boxes placed in a truck cannot exceed L (where L is given).
	 * @return true if the truck is full, false otherwise
	 */
	boolean isTruckFull() {
		int numOfBoxes = 0;
		
		for (Pile pile : piles)
			numOfBoxes += pile.boxes.size();
		
		return numOfBoxes == L;
	}
}
