package objects;


/**
 * 
 * @author Hau Lian
 *
 */
public class Point {
	
	/**
	 * 
	 */
	private final double x;
	
	/**
	 * 
	 */
	private final double y;
	
	/*
	 * START: Constructors
	 */
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * END: Constructors
	 */
	
	/*
	 * START: Getter Methods
	 */
	
	/**
	 * 
	 * @return
	 */
	public double getXCoordinate() {
		return this.x;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getYCoordinate() {
		return this.y;
	}
	
	/*
	 * END: Getter Methods
	 */
	
	/*
	 * START: Other Instance Methods
	 */
	
	public double distanceToPoint(Point p) {
		return Math.sqrt(Math.pow(this.x-p.x,2) + Math.pow(this.y-p.y, 2));
	}
	
	public Point switchXY() {
		return new Point(this.y, this.x);
	}
	
	/*
	 * END: Other Instance Methods
	 */
	
	public static boolean pointOnLine(Point p, Line l) {
		return p.getYCoordinate() == l.getSlope()*p.getXCoordinate() + l.getConstant();
	}
	
	public static double getSlope(Point a, Point b) {
		return (a.getYCoordinate()-b.getYCoordinate()) /
			   (a.getXCoordinate()-b.getXCoordinate());
	}
	
	
	
	/*
	 * START: Overriding Methods
	 */
	
	/**
	 * 
	 */
	public Point clone() {
		return this.clone();
		//return new Point(this.x, this.y);
	}
	
	/**
	 * 
	 */
	public boolean equalsA(Object o) {
		try{
			Point in = (Point) o;
			return this.x == in.x && 
				   this.y == in.y;
		}
		catch ( Exception e ){
			return false;
		}
	}
	
	/**
	 * (x,y)
	 */
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	/*
	 * END: Overriding Methods
	 */
	
	
	
}
