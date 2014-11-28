package com.pgame.tzur;

public class Plane extends baseGameCharacter {
	//inherits from the base game character, which means it has some of the same fields as it
	// we don't need to duplicate the code or the constructor for the class, we just calll "super"
	//on the current constructor
	
	// an integer which defines the speed for the plane
	private int speed = -1;

	public Plane(int x, int y, String path) {
		super(x, y, path);

	}

	// function that moves the plane through the sky, calculates if it has gone off-screen and then
	//locates it back to the right side of the screen
	public void movePlane() {
		setX(getX() + speed);
		if (getX() < -140) {
			setX(500);
		}
	}

}
