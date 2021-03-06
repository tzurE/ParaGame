package com.pgame.tzur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener {
	// The main Game class. here you can find the game loop, all the game
	// components, and the overrided paint function(of the swing library).
	// to make things less complicated, I have made the main game class a
	// keylistner, instead of creating a new class or an anonymous class, which
	// will make the code less readable

	// the main game components
	protected static Paradrop paradrop;
	protected static List<baseGameCharacter> game_ent = new ArrayList<baseGameCharacter>();
	private int score = 0, lives = 3;

	// this is an override of the swing paint function. it renders all of the
	// frames.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g1 = (Graphics2D) g;
		g1.setColor(Color.BLUE);
		g1.fillRect(ConstantStuff.LeftBound, ConstantStuff.UpperBound, ConstantStuff.WaterWidth, ConstantStuff.WaterHieght);
		//iterate over entities of the game and draw them - 
		for(baseGameCharacter ent: game_ent)
			g1.drawImage(ent.getImage(), ent.getX(), ent.getY(), null);
		g1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ConstantStuff.FontSize));
		
		//drawing the mini-gui of the scoring and lives left
		g1.drawString("Score: " + score, ConstantStuff.ScoreX, 35);
		g1.drawString("Lives: " + lives, ConstantStuff.LivesX, 35);
		if (paradrop != null) {
			if (paradrop.isDropped())
				g1.drawImage(paradrop.getImage(), paradrop.getX(),
						paradrop.getY(), null);
		}
		if (this.lives == 0) {
			g1.drawString("Game Over!", ConstantStuff.GameOverXY, ConstantStuff.GameOverXY);
			return;
		}
		g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Paradrop Game");
		int time = 0, outcome;
		//a boolean that get the value of true if A parachute is in the air right now
		boolean dropped = false;
		Plane plane;
		Boat boat;

		// initialize the main game components
		Game game = new Game();
		plane = new Plane(ConstantStuff.WaterWidth, ConstantStuff.LeftBound, "assets/plane.png");
		boat = new Boat(ConstantStuff.BoatX, ConstantStuff.BoatY, "assets/boat.png");
		game_ent.add(plane);
		game_ent.add(boat);

		frame.add(game);
		game.setBackground(Color.white);
		frame.setSize(ConstantStuff.WaterWidth, ConstantStuff.WinHeight);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(game);

		// main game loop. this is where the game is updated. 
		//the screen gets repainted every 15 millis secs.
		// iv'e searched the web and found that this frame rate is somewhat of
		// the ideal 2d games frame rate.
		while (true) {
			plane.movePlane();
			game.repaint();
			if (time >= 3000 && (dropped == false) && plane.getX() > 0
					&& plane.getX() < 400) {
				paradrop = new Paradrop(plane.getX(), plane.getY() + 80,
						"assets/para.png");
				paradrop.setDropped(true);
				dropped = true;
				time = 0;
			}
			
			//if there is a parachute in the air - did he touch the sea? 
			//did he touch the boat? the outcome integer determines that
			if (dropped) {
				paradrop.fall();
				outcome = paradrop.check_catch(boat);

				if (outcome == 1) {
					game.score += 1;
					dropped = false;
				} else if (outcome == 0) {
					game.lives--;
					dropped = false;
				}
				outcome = 2;
			}
			
			//if the player lost the game, the game stops and waits for an exit
			if (game.lives == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return;
			}
			try {
				Thread.sleep(15);
				time += 15;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// the keylistner interface methods. notice the differences between key
	// pressed and released.
	// while a key is pressed, the boat will keep going. but when that key is
	// released, it will immediately stop
	@Override
	public void keyPressed(KeyEvent e) {
		Boat boat = (Boat) game_ent.get(1);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			boat.moveLeft();
			boat.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			boat.moveRight();
			boat.setMovingRight(true);
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Boat boat = (Boat) game_ent.get(1);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			boat.setMovingLeft(false);
			break;

		case KeyEvent.VK_RIGHT:
			boat.setMovingRight(false);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
