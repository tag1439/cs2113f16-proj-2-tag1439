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
        if(detect(10)){
            if(direction==0)
                direction=1;
            if(direction==1)
                direction=0;
            if(direction==3)
                direction=2;
            if(direction==2)
                direction=3;

            move();
            move();
            return;
        }
        int pickDir = Helper.nextInt(10);//generates a random number

            if (pickDir > 0) {
                move();
            }
            else{
                do {
                    pickDir = Helper.nextInt(4);
                }while(pickDir==this.direction);
                    //if zero, a new number is generated for the 4 possible directions that it changes to
                    //this is the 10% chance
                    this.direction = pickDir;

                }
    }

    public boolean detect(int range){

        if (direction==0){
            for(Zombies h:City.arrayZ){
                if(h.getXcoord()==xcoord) {
                    if(h.getYcoord()-ycoord<range&&h.getYcoord()-ycoord<0)
                        return true;
                }
            }
        }
        else  if (direction==1){
            for(Zombies h:City.arrayZ){
                if(h.getXcoord()==xcoord) {
                    if(h.getYcoord()-ycoord<range&&h.getYcoord()-ycoord>0)
                        return true;
                }
            }
        }
        else  if (direction==2){
            for(Zombies h:City.arrayZ){
                if(h.getYcoord()==ycoord) {
                    if(h.getXcoord()-xcoord<range&&h.getXcoord()-xcoord<0)
                        return true;
                }
            }
        }
        else  if (direction==1){
            for(Zombies h:City.arrayZ){
                if(h.getYcoord()==ycoord) {
                    if(h.getXcoord()-xcoord<range&&h.getXcoord()-xcoord>0)
                        return true;
                }
            }
        }
        return false;
    }
    public void move(){

        //if not zero or one, it will continue in the direction it is facing
        if ((ycoord - 1 > 0) && City.walls[xcoord][ycoord - 1] == false && this.direction == 0) {

            this.ycoord--;
        }
        if ((ycoord + 1 < City.walls[0].length-1) && City.walls[xcoord][ycoord + 1] ==false && this.direction == 1) {

            this.ycoord++;
        }
        if ((xcoord-1 > 0) && City.walls[xcoord-1][ycoord] == false && this.direction == 2) {

            this.xcoord--;
        }
        if ((xcoord+1 < City.walls.length-1) && City.walls[xcoord+1][ycoord] ==false && this.direction == 3) {

            this.xcoord++;
        }
    }
    public int getXcoord(){//gets the x coordinate
        return this.xcoord;//this method is found in City's draw method
    }
    public int getYcoord(){//gets the y coordinate
        return this.ycoord;//this method is found in City's draw method
    }

}

