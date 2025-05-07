package hello;

/**
 * This class creates animals
 * @author howhow
 *
 */
public abstract class Animal {
	
	protected int numLegs;
	
	public Animal(int numLegs){
		this.numLegs = numLegs;
	}
	
	public abstract void meow();
	public abstract void bark();
	

}
