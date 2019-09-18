/**
 * @author Hristo Hristov
 */
package coursework;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Test {

	public static void main(String[] args) {
		
		System.out.print("Generating boxes...");
		List<Box> boxes = generateBoxes(10000, Truck.DEFAULT_WIDTH-1, Truck.DEFAULT_HEIGHT-1);
		System.out.println(boxes.size() + " boxes generated.");
		//System.out.println(boxes.toString());
		
		List<Truck> trucks = null;
		int trucksUsed = 0;
		trucks = new ArrayList<Truck>();
		
		System.out.println("Calling nextFit...");
		long startTime = System.nanoTime();
		trucksUsed = Algorithms.nextFit(trucks, boxes);
		long estimatedTime = System.nanoTime() - startTime;
		System.out.printf("nextFit - trucksUsed: %d Estimated time (secs): %d nano, %d micro, %d milli\n", 
				trucksUsed, estimatedTime, estimatedTime / 1000, estimatedTime / 1000000);
		
		trucks = new ArrayList<Truck>();
		System.out.println("Calling firstFit...");
		startTime = System.nanoTime();
		trucksUsed = Algorithms.firstFit(trucks, boxes);
		estimatedTime = System.nanoTime() - startTime;
		System.out.printf("firstFit - trucksUsed: %d Estimated time (secs): %d nano, %d micro, %d milli\n", 
				trucksUsed, estimatedTime, estimatedTime / 1000, estimatedTime / 1000000);
	}
	
	/**
	 * Used to generate boxes with random widths and heights
	 * @param amount of boxes to generate
	 * @param maxWidth
	 * @param maxHeight
	 * @return a list of boxes
	 */
	static List<Box> generateBoxes(int amount, int maxWidth, int maxHeight) {
		// Used to generate random widths and heights
		Random rand = new Random();
		
		List<Box> boxes = new ArrayList<Box>();
		while (amount > 0) {
			boxes.add( new Box(rand.nextInt(maxWidth), rand.nextInt(maxHeight)) );
			amount--;
		}
		return boxes;
	}
}
