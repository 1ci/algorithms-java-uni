/**
 * @author Hristo Hristov
 */
package coursework;

class Box {

	final int width;
	final int height;
	
	Box(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "Box [width=" + width + ", height=" + height + "]";
	}
}
