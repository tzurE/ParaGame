package com.pgame.tzur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class baseGameCharacter {
	// at first I've defined a baseVehicle class, but than it occurred to me
	// that even the Parachutist has some of the fields as the boat and plane(location, sprite image)
	// so i've made all the components of the game inherit from the base game
	// character which contains all the valid info on the parts of the game

	// location on the game screen
	private int x;
	private int y;
	// the sprite image
	private BufferedImage image;

	// the class constructor, incharge of loading the images and managing first location
	public baseGameCharacter(int x, int y, String path) {
		super();
		this.x = x;
		this.y = y;
		File file = new File(path);
		try {
			BufferedImage img = ImageIO.read(file);
			this.image = img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("unable to load image");
			return;
		}

	}

	public int getX() {
		return x;
	}

	// some getters and setters

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
