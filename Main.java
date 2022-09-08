package battleship;

import java.util.Scanner;

public class Main {
    static int shotRow = -1;
    static int shotCol = -1;

    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        changeTurn();

        Player player2 = new Player("Player 2");
        changeTurn();

        while (true) {
            if(gameTurn(player2,player1)){
                return;
            }
            if(gameTurn(player1,player2)){
                return;
            }

        }
    }

    public static void changeTurn() {
        System.out.println("Press Enter and pass the move to another player");
        new Scanner(System.in).nextLine();
    }

    public static void getShot(){
        do {
            String s = new Scanner(System.in).nextLine();
            shotRow = Coordinates.mapCharToInteger(s.charAt(0));
            shotCol = Integer.parseInt(s.substring(1)) - 1;
            if (shotCol < 0 || shotCol > 9 || shotRow < 0 || shotRow > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        } while (shotCol < 0 || shotCol > 9 || shotRow < 0 || shotRow > 9);
    }
    public static boolean gameTurn(Player playerGettingAttacked,Player playerAttacking){
        playerGettingAttacked.getBattlefield().printFogOfWar();
        System.out.println(playerAttacking);
        getShot();
        playerGettingAttacked.takeHit(shotRow, shotCol);
        if (playerGettingAttacked.hasLost()) {
            System.out.println("You sank the last ship. You won. Congratulations!");
            return true;
        }
        changeTurn();
        return false;
    }
}
