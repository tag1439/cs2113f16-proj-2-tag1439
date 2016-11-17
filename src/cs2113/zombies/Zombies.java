package cs2113.zombies;

import cs2113.util.Helper;

import java.util.ArrayList;

/**
 * Created by tag14 on 11/15/2016.
 */
public class Zombies {

    int xcoord;
    int ycoord;
    int direction;
    City city;

    public Zombies(int xcoord, int ycoord, City city){//makes a huma    n with a position (x.y)
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.city=city;
        direction =0;//initializes direction to zerp, but updates it in move

    }
    public void moveZombie() {
        ArrayList<Humans> humanArray = City.arrayH;
        ArrayList<Zombies> zombieArray = City.arrayZ;

        ArrayList<Humans> humTemp = new ArrayList<>();
        for (Humans h : humanArray) {
            if (Math.abs(xcoord - h.xcoord) + Math.abs(ycoord - h.getYcoord()) <= 1) {
                humTemp.add(h);
            }
        }
        for (Humans h : humTemp) {

            City.arrayH.remove(h);
            City.arrayZ.add(new Zombies(h.xcoord, h.ycoord, city));
        }
        if (detect(10)) {
            move();
        } else {
            int pickDir = Helper.nextInt(10);
            if (pickDir > 1) {
                move();
            } else {
                do {
                    pickDir = Helper.nextInt(4);
                } while (pickDir == this.direction);
                //if zero, a new number is generated for the 4 possible directions that it changes to
                //this is the 10% chance
                this.direction = pickDir;

            }
        }
    }
    public boolean detect(int range){

        if (direction==0){
            for(Humans h:City.arrayH){
                if(h.getXcoord()==xcoord) {
                    if(h.getYcoord()-ycoord<range&&h.getYcoord()-ycoord<0)
                    return true;
                }
            }
        }
        else  if (direction==1){
            for(Humans h:City.arrayH){
                if(h.getXcoord()==xcoord) {
                    if(h.getYcoord()-ycoord<range&&h.getYcoord()-ycoord>0)
                        return true;
                }
            }
        }
        else  if (direction==2){
            for(Humans h:City.arrayH){
                if(h.getYcoord()==ycoord) {
                    if(h.getXcoord()-xcoord<range&&h.getXcoord()-xcoord<0)
                        return true;
                }
            }
        }
        else  if (direction==1){
            for(Humans h:City.arrayH){
                if(h.getYcoord()==ycoord) {
                    if(h.getXcoord()-xcoord<range&&h.getXcoord()-xcoord>0)
                        return true;
                }
            }
        }
        return false;
    }
    public void move(){

        if ((ycoord - 1 > 0) && City.walls[xcoord][ycoord - 1] == false && this.direction == 0 /*&& Humans.getYcoord() < 11 + this.ycoord*/)
            this.ycoord--;
        if ((ycoord + 1 < City.walls[0].length - 1) && City.walls[xcoord][ycoord + 1] == false && this.direction == 1 /*&& Humans.getYcoord() > 11 + this.ycoord*/)
            this.ycoord++;
        if ((xcoord - 1 > 0) && City.walls[xcoord - 1][ycoord] == false && this.direction == 2 /*&& Humans.getXcoord() < 11 + this.xcoord*/)
            this.xcoord--;
        if ((xcoord + 1 < City.walls.length - 1) && City.walls[xcoord + 1][ycoord] == false && this.direction == 3 /*&& Humans.getXcoord() > 11 + this.xcoord*/)
            this.xcoord++;
    }
    public int getXcoord(){//gets the x coordinate
        return this.xcoord;//this method is found in City's draw method
    }
    public int getYcoord(){//gets the y coordinate
        return this.ycoord;//this method is found in City's draw method
    }
}
