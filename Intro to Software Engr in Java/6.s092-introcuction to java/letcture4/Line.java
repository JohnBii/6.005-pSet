package objects;



/**
 * 
 * @author Hau Lian
 *
 */
public class Line{
	
	/*
	 * START: Instance Fields
	 */
	
	/**
	 * 
	 */
	private final double slope;
	// TODO: Implement this variable as specified in the specs
	
	/**
	 * 
	 */
	private final double constant;
	// TODO: Implement this variable as specified in the specs
	
	/*
	 * END: Instance Fields
	 */
	
	/*
	 * START: Constructor Methods
	 */
	
	public Line(int slope, int constant) {
		// TODO: Implement this method as specified in the specs
		this.slope = slope;
		this.constant = constant;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public Line(double a, double b, double c) {
		// TODO: Implement this method as specified in the specs
		this.slope = -a/b;
		this.constant = c;
	}
	
	public Line(double slope, double constant) {
		// TODO: Implement this method as specified in the specs
		this.slope = slope;
		this.constant = constant;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public Line(Point a, Point b) {
		// TODO: Implement this method as specified in the specs
		// ax + by = c
		// y = slope(x-x1) + y1
		// y = slope(x) - slope(x1) + y1
		this.slope = Point.getSlope(a, b);
		this.constant = Point.getSlope(a, b)*-a.getXCoordinate()+a.getYCoordinate();
	}	
	
	/*
	 * END: Constructor Methods
	 */
	
	/*
	 * START: Getter Methods
	 */
	
	public double getSlope() {
		// TODO: Implement this method as specified in the specs
		return this.slope;
	}
	
	public double getConstant() {
		// TODO: Implement this method as specified in the specs
		return this.constant;
	}
	
	/*
	 * END: Getter Methods
	 */
	
	/*
	 * START: Other Instance Methods
	 */
	
	public Line add(Line l) {
		// TODO: Implement this method as specified in the specs
		return new Line((this.slope+l.slope)/2, (this.constant+l.constant)/2);
	}
	
	public double evaluateXGiveY(double x) {
		// TODO: Implement this method as specified in the specs
		return this.slope*x-this.constant;
	}
	
	public double evaluateYGiveX(double y) {
		// TODO: Implement this method as specified in the specs
		return (y - this.constant) / this.slope;
	}
	
	public Line shiftX(double delta) {
		// TODO: Implement this method as specified in the specs
		return new Line(this.slope, this.constant - this.slope*delta);
	}
	
	public Line shiftY(double delta) {
		// TODO: Implement this method as specified in the specs
		return new Line(this.slope, this.constant+delta);
	}
	
	public Line inverse() {
		// TODO: Implement this method as specified in the specs
		return new Line(1/this.slope, -this.constant/this.slope);
	}
	
	/*
	 * END: Other Instance Methods
	 */
	
	/*
	 * START: Overriding Methods
	 */
	
	public Line clone() {
		// TODO: Implement this method as specified in the specs
		return new Line(this.slope, this.constant);
	}
	
	public boolean equals(Object o) {
		// TODO: Implement this method as specified in the specs
		try{
			Line l = (Line) o;
			return this.slope == l.slope && this.constant == l.constant;
		}
		catch ( Exception e ) {
			return false;
		}
	}
	
	public String toString() {
		// TODO: Implement this method as specified in the specs
		return "y = " + this.slope + " x + " + this.constant;
	}
	
	/*
	 * END: Overriding Methods
	 */
	
}
