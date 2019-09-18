/**
 * @author Hristo Hristov
 */
package coursework;

import java.util.List;

class Algorithms {

	/** 
	 * When processing the next box, see if it fits in the same
	 * truck as the last box. Make a new truck only if it does not.
	 * @return number of trucks used to put the boxes
	 */
	static int nextFit(List<Truck> trucks, List<Box> boxes) {
		int trucksUsed = 1;
		// Makes the first truck to work with and keeps track of the last truck used
		Truck truck = new Truck();
		trucks.add(truck);
		// Keeps track of the last pile used
		Pile pile = null;
		
		for (Box box : boxes) {
			if (!putBox(truck, canFitOnPile(truck, pile, box), box)) {
				// Could not find available space for this box
				if (putBoxInNewTruck(trucks, box)) {
					// The box was able to fit in a new truck
					truck = trucks.get(trucks.size()-1);
					trucksUsed++;
				}
			}
			// Keep track of the last pile used
			pile = (truck.piles.size() > 0) ? truck.piles.get(truck.piles.size()-1) : null;
		}
		
		// Check if any boxes were put
		if (trucks.get(0).piles.isEmpty()) {
			// The first truck had no piles created, therefore no boxes were put
			trucks.remove(0);
			trucksUsed = 0;
		}
		return trucksUsed;
	}
	
	/**
	 * Rather than checking just the last truck, we check all previous trucks
	 * to see if the next box will fit. Make a new truck only when it does not.
	 * Allow the use of space in earlier trucks that would be ignored by Next fit.
	 * @return number of trucks used to put the boxes
	 */
	static int firstFit(List<Truck> trucks, List<Box> boxes) {
		int trucksUsed = 0;
		// Used to iterate through existing trucks
		int truckIdx;
		// Keeps a reference to the truck currently being used
		Truck truck;
		
		for (Box box : boxes) {
			// Check all previous trucks for space
			for (truckIdx = 0; truckIdx < trucks.size(); truckIdx++) {
				truck = trucks.get(truckIdx);
				if (putBox(truck, canFitOnPile(truck, box), box))
					break; // The box was put successfully
			}
			if (truckIdx == trucks.size()) {
				// Could not find available space for this box
				if (putBoxInNewTruck(trucks, box)) {
					// The box was able to fit in a new truck
					trucksUsed++;
				}
			}
		}
		return trucksUsed;
	}
	
	/**
	 * Checks if a box is within valid truck bounds
	 * @return true if the box is smaller or equal to the truck's dimensions, false otherwise
	 */
	static boolean canFit(Truck truck, Box box) {
		return (box.height <= truck.height && box.width <= truck.width);
	}
	
	/**
	 * Checks if a box can fit on any pile of boxes in the truck
	 * @return a pile index at which the box can be put or null if it cannot fit on any of the piles
	 */
	static Pile canFitOnPile(Truck truck, Box box) {
		for (Pile pile : truck.piles)
			if (canPutOnTop(truck, pile, box))
				return pile;
		return null;
	}
	
	/**
	 * Checks if a box can fit on a specified pile of boxes in the truck
	 * @return the same pile index at which the box can be put or null if it cannot fit on the specified pile
	 */
	static Pile canFitOnPile(Truck truck, Pile pile, Box box) {
		if (pile == null)
			return null;
		if (canPutOnTop(truck, pile, box))
			return pile;
		return null;
	}
	
	/**
	 * Checks if a box can be put on top of another one
	 * @return true if the box can be put on top, false otherwise
	 */
	static boolean canPutOnTop(Truck truck, Pile pile, Box box) {
		int heightSum = 0;
		for (Box b : pile.boxes)
			heightSum += b.height;
		
		// Check if there's enough height and width
		return (box.height <= truck.height - heightSum &&
				pile.boxes.get(pile.boxes.size() - 1).width >= box.width);
	}
	
	/**
	 * Attempts to put a box in a truck
	 * @return true if managed to put the box, false otherwise
	 */
	static boolean putBox(Truck truck, Pile pile, Box box) {
		if (truck.isTruckFull())
			return false;
		if (!canFit(truck, box))
			return false;
		
		// If pile is null, try making a new one
		if (pile == null) {
			if (canMakePile(truck, box)) {
				// Make a new pile
				pile = new Pile();
				truck.piles.add(pile);
			} else {
				return false;
			}
		}
		return pile.boxes.add(box);
	}
	
	/**
	 * Attempts to put a box into a newly created truck
	 * @return true on success, false otherwise
	 */
	static boolean putBoxInNewTruck(List<Truck> trucks, Box box) {
		Truck truck = new Truck();
		
		if (putBox(truck, null, box)) {
			trucks.add(truck);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if there's enough space for a box to create a new pile in a truck
	 * @return true if there's enough space, false otherwise
	 */
	static boolean canMakePile(Truck truck, Box box) {
		int widthSum = 0;
		for (Pile pile : truck.piles)
			widthSum += pile.boxes.get(0).width;
		
		// Check if there's enough width
		return (box.width <= truck.width - widthSum);
	}
}
