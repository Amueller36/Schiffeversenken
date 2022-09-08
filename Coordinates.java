package battleship;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Coordinates {
    private Integer firstRowIndice;
    private Integer firstColIndice;
    private Integer secondRowIndice;
    private Integer secondColIndice;

    public Integer getFirstColIndice() {
        return firstColIndice;
    }

    public Integer getFirstRowIndice() {
        return firstRowIndice;
    }

    public Integer getSecondColIndice() {
        return secondColIndice;
    }

    public Integer getSecondRowIndice() {
        return secondRowIndice;
    }

    public Coordinates(int sizeOfShip) {
        sizeOfShip--;
        do {
            getInput();
            if (secondRowIndice - firstRowIndice != sizeOfShip && secondColIndice - firstColIndice != sizeOfShip) {
                System.out.println("Error! Wrong length of the Ship try again:\n");
            }
        } while (secondRowIndice - firstRowIndice != sizeOfShip && secondColIndice - firstColIndice != sizeOfShip);
    }

    public void getInput() {
        //Get input from user
        String[] coords;
        do {
            String s = new Scanner(System.in).nextLine();
            coords = s.split(" ");
            if (coords.length != 2) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            //Extract the coordinates from string
            firstRowIndice = mapCharToInteger(coords[0].charAt(0));
            firstColIndice = Integer.parseInt(coords[0].substring(1)) - 1;
            secondRowIndice = mapCharToInteger(coords[1].charAt(0));
            secondColIndice = Integer.parseInt(coords[1].substring(1)) - 1;

            //Check if input is valid
            if (firstColIndice < 0 || firstColIndice > 9 || secondColIndice < 0 || secondColIndice > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            //Check if ship is vertical or horizontal, else print error
            if (!areVertical() && !areHorizontal()) {
                System.out.println("Error! Wrong ship location! Try again:");
            }
            //Switch Coordinate Order if Input in wrong order.
            if (firstColIndice > secondColIndice) {
                int temp = secondColIndice;
                secondColIndice = firstColIndice;
                firstColIndice = temp;
            }
            if (firstRowIndice > secondRowIndice) {
                int temp = secondRowIndice;
                secondRowIndice = firstRowIndice;
                firstRowIndice = temp;
            }


        } while (coords.length != 2 && !areVertical() && !areHorizontal());
    }


    public static int mapCharToInteger(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            default:
                throw new IllegalArgumentException("Entered Wrong Value for row!");
        }
    }

    public boolean areHorizontal() {
        return Objects.equals(firstRowIndice, secondRowIndice);
    }

    public boolean areVertical() {
        return Objects.equals(firstColIndice, secondColIndice);
    }

}
