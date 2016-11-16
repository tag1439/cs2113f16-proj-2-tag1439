package cs2113.zombies;
import cs2113.util.Helper;

import java.awt.*;
/**
 * Created by tag14 on 11/11/2016.
 */
public class Humans {

    int xcoord;
    int ycoord;
    int direction;

    public Humans(int xcoord, int ycoord, City city){//makes a human with a position (x.y)
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        direction = Helper.nextInt(4);//initializes direction to random

    }

    public void moveHuman(){
        int pickDir = Helper.nextInt(10);//generates a random number

            if (pickDir > 0) {
                //if not zero or one, it will continue in the direction it is facing
                if ((ycoord - 1 > 0) && City.walls[xcoord][ycoord - 1] == false && this.direction == 0) {
                    if (this.ycoord + 1 < Zombies.getYcoord() < 11 + this.ycoord)
                        this.ycoord -= 2;
                    else
                        this.ycoord--;
                }
                if ((ycoord + 1 < City.walls[0].length) && City.walls[xcoord][ycoord + 1] ==false && this.direction == 1) {
                    if (this.ycoord - 1 > Zombies.getYcoord() > 11 - this.ycoord)
                        this.ycoord += 2;
                    else
                        this.ycoord++;
                }
                if ((xcoord-1 > 0) && City.walls[xcoord-1][ycoord] == false && this.direction == 2) {
                    if (this.xcoord + 1 < Zombies.getXcoord() < 11 + this.xcoord)
                        this.xcoord -= 2;
                    else
                        this.xcoord--;
                }
                if ((xcoord+1 < City.walls.length) && City.walls[xcoord+1][ycoord] ==false && this.direction == 3) {
                    if (this.xcoord - 1 > Zombies.getXcoord() > 11 - this.xcoord)
                        this.xcoord += 2;
                    else
                        this.xcoord++;
                }
            }
            else{
                    pickDir = Helper.nextInt(4);
                    //if zero, a new number is generated for the 4 possible directions that it changes to
                    //this is the 10% chance
                    if (pickDir == 0)
                        this.direction = 0;

                    if (pickDir == 1)
                        this.direction = 1;

                    if (pickDir == 2)
                        this.direction = 2;

                    if (pickDir == 3)
                        this.direction = 3;

                }
    }
    public int getXcoord(){//gets the x coordinate
        return this.xcoord;//this method is found in City's draw method
    }
    public int getYcoord(){//gets the y coordinate
        return this.ycoord;//this method is found in City's draw method
    }

}

