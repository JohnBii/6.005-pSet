package hello;

public class Cat extends Animal {
	
	private String name;
	
	public Cat(String nameInput){
		super(4);
		this.name = nameInput;
	}
	
	public Cat(String nameInput, int numberOfLegs){
		super(numberOfLegs);
		this.name = nameInput;
	}
	
	@Override
	public void meow() {
		System.out.println("Meow");
	}

	@Override
	public void bark() {
		// DO NOTHING COS IT
		// CAN'T BARK
		// Give a message to user
	}

}
