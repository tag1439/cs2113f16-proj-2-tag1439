package cs2113.zombies;

import cs2113.util.Helper;

import java.awt.Color;
import java.util.ArrayList;


public class City {

	/** walls is a 2D array with an entry for each space in the city.
	 *  If walls[x][y] is true, that means there is a wall in the space.
	 *  else the space is free. Humans should never go into spaces that
	 *  have a wall.
	 */
	public static boolean walls[][];
	private int width, height;
	ArrayList<Humans> arrayH = new ArrayList<Humans>();

	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 */
	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];

		randomBuildings(numB);
		populate(numP);
	}


	/**
	 * Generates numPeople random people distributed throughout the city.
	 * People must not be placed inside walls!
	 *
	 * @param numPeople the number of people to generate
	 */
	private void populate(int numPeople)
	{
		// Generate numPeople new humans randomly placed around the city.
		int xpos;
		int ypos;
		for(int i = 0; i<numPeople; i++){//this for loop makes 200 humans, assings them random
			xpos = Helper.nextInt(width);//x and y values, then places them in an array list
			ypos = Helper.nextInt(height);//
			while(walls[xpos][ypos] == true) {
				xpos = Helper.nextInt(width);//x and y values, then places them in an array list
				ypos = Helper.nextInt(height);

			}
			Humans human = new Humans(xpos, ypos, this);
			arrayH.add(human);



		}

	}


	/**
	 * Generates a random set of numB buildings.
	 *
	 * @param numB the number of buildings to generate
	 */
	private void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;

		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++) {
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

			for(int r = ty; r < ty + th; r++) {
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++) {
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}

	/**
	 * Updates the state of the city for a time step.
	 */
	public void update() {
		// Move humans, zombies, etc
		for(int i = 0; i<arrayH.size(); i++)//this for loop goes through the arraylist
		{
			int x = arrayH.get(i).getXcoord();
			int y = arrayH.get(i).getYcoord();

				arrayH.get(i).moveHuman();        //and moves each human
		}
		}

	/**
	 * Draw the buildings and all humans.
	 */
	public void draw(){
		/* Clear the screen */
		ZombieSim.dp.clear(Color.black);
		drawWalls();
		ZombieSim.dp.setPenColor(Color.GREEN);//sets the color of the humans
		for(int i =0; i<arrayH.size(); i++)//goes through the loop to draw each human at its coordinates
		{
			int x = arrayH.get(i).getXcoord();
			int y = arrayH.get(i).getYcoord();
			ZombieSim.dp.drawDot(x, y);

		}


		}
	/**
	 * Draw the buildings.
	 * First set the color for drawing, then draw a dot at each space
	 * where there is a wall.
	 */
	private void drawWalls() {
		ZombieSim.dp.setPenColor(Color.DARK_GRAY);
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(walls[c][r])
				{
					ZombieSim.dp.drawDot(c, r);
				}
			}
		}
	}


}
