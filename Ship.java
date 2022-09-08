package battleship;


import java.util.Arrays;

public class Ship{
    private int size;
    private boolean destroyed = false;
    private Coordinates shipCoordinates;

    public int getHealth(){
        return this.size;
    }
    public void takeHit(){
        this.size--;
        if(size<=0){
            destroyed=true;
        }
    }

    public Coordinates getShipCoordinates() {
        return shipCoordinates;
    }

    public boolean isDestroyed() {
        return destroyed;
    }



    public Ship(int size, Coordinates shipCoordinates) {
        this.size = size;
        this.shipCoordinates = shipCoordinates;
    }

}
