package com.pgame.tzur;

public class Boat extends baseGameCharacter {
	//inherits from the base game character, which means it has some of the same fields as it
	// we don't need to duplicate the code or the constructor for the class, we just calll "super"
	//on the current constructor
	
	
	private int speed_right = 5;
	private int speed_left = -5;
	private boolean movingRight = false;
	private boolean movingLeft = false;

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public Boat(int x, int y, String path) {
		super(x, y, path);

	}

	public void moveLeft() {
		if (getX() < -15)
			return;
		else
			setX(getX() + speed_left);

	}

	public void stopLeft() {
		setMovingLeft(false);
	}

	public void moveRight() {
		if (getX() > 330)
			return;

		else
			setX(getX() + speed_right);
	}

	public void stopRight() {
		setMovingRight(false);
	}

}
