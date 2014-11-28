package com.pgame.tzur;

public class Paradrop extends baseGameCharacter {
	//inherits from the base game character, which means it has some of the same fields as it
	// we don't need to duplicate the code or the constructor for the class, we just calll "super"
	//on the current constructor
	
	//dropped == is the current occurrence of the class is dropping?
	private boolean dropped = false;
	private int speed = 1;

	public boolean isDropped() {
		return dropped;
	}

	public void setDropped(boolean dropped) {
		this.dropped = dropped;
	}

	public Paradrop(int x, int y, String path) {
		super(x, y, path);
		// TODO Auto-generated constructor stub
	}

	public void fall() {
		setY(getY() + speed);
		if (getX() < -140) {
			setX(500);
		}
	}

	//calculates the paradrop location, and then returns an outcome.
	// 1 - the boat catched it
	// 2 - still dropping
	// 0 - fell to the sea
	public int check_catch(Boat boat) {
		if (this.getY() > boat.getY() && (this.getX() < boat.getX() + 130)
				&& (getX() > boat.getX() - 40)) {
			dropped = false;
			return 1;
		} else if (this.getY() > 450) {
			dropped = false;
			return 0;
		}
		return 2;
	}

}
