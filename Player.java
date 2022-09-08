package battleship;

import java.util.Scanner;

public class Player {
    private Battlefield battlefield;
    private String name;

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public Player(String name) {
        this.name = name;
        this.battlefield = new Battlefield(10);
        System.out.println(name + ", place your ships on the game field");
        this.battlefield.initialize();
    }

    public void takeHit(int shotRow, int shotCol) {
        battlefield.takeHit(shotRow, shotCol);
    }
    public boolean hasLost(){
        return battlefield.lostTheGame();
    }
    @Override
    public String toString(){
        System.out.println("---------------------");
        this.battlefield.print();
        System.out.println(name+", it's your turn:\n");
        return "";
    }
}
