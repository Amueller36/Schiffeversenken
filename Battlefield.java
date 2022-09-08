package battleship;

import java.util.Arrays;

public class Battlefield {
    private char[][] battleField;
    private Ship[] ships = new Ship[5];


    Battlefield(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Dont enter a negative size!");
        this.battleField = new char[size][size];
        for (char[] chars : battleField) {
            Arrays.fill(chars, '~');
        }
    }

    public void print() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char alphabet = 'A';
        for (char[] rows : battleField) {
            System.out.print(alphabet + " ");
            for (char columns : rows) {
                System.out.print(columns + " ");
            }
            System.out.println();
            alphabet++;
        }
    }

    public void initialize() {
        print();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
        Coordinates coordinates = new Coordinates(5);
        //Get Coordinates, check if they are valid, and then place the ships
        placeShipOnCoordinate(coordinates);
        ships[0] = new Ship(5, coordinates);
        this.print();

        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");
        do {
            coordinates = new Coordinates(4);
        } while (!allowedToPlaceShip(coordinates));

        placeShipOnCoordinate(coordinates);
        ships[1] = new Ship(4, coordinates);
        this.print();

        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");
        do {
            coordinates = new Coordinates(3);
        } while (!allowedToPlaceShip(coordinates));

        placeShipOnCoordinate(coordinates);
        ships[2] = new Ship(3, coordinates);
        this.print();

        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");
        do {
            coordinates = new Coordinates(3);
        } while (!allowedToPlaceShip(coordinates));


        placeShipOnCoordinate(coordinates);
        ships[3] = new Ship(3, coordinates);
        this.print();

        System.out.println("Enter the coordinates of the Destroyer (2 cells):\n");
        do {
            coordinates = new Coordinates(2);
        } while (!allowedToPlaceShip(coordinates));


        placeShipOnCoordinate(coordinates);
        ships[4] = new Ship(2, coordinates);
        this.print();

    }
    //Hides the ships from the player and only shows the hits and misses
    public void printFogOfWar() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char alphabet = 'A';
        for (char[] rows : battleField) {
            System.out.print(alphabet + " ");
            for (char columns : rows) {
                if (columns == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(columns + " ");
                }
            }
            System.out.println();
            alphabet++;
        }
    }

    public void takeHit(int shotRow, int shotCol) {
        if (battleField[shotRow][shotCol] == 'O') {
            battleField[shotRow][shotCol] = 'X';
            for (Ship ship : ships) {
                if (ship.getShipCoordinates().areHorizontal()) {
                    if (shotRow == ship.getShipCoordinates().getFirstRowIndice() && shotCol >= ship.getShipCoordinates().getFirstColIndice() && shotCol <= ship.getShipCoordinates().getSecondColIndice()) {
                        ship.takeHit();
                        battleField[shotRow][shotCol] = 'X';
                        if (ship.isDestroyed()) {
                            System.out.println("You sank a ship");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                    }
                } else if (ship.getShipCoordinates().areVertical()) {
                    if (shotCol == ship.getShipCoordinates().getFirstColIndice() && shotRow >= ship.getShipCoordinates().getFirstRowIndice() && shotRow <= ship.getShipCoordinates().getSecondRowIndice()) {
                        ship.takeHit();
                        battleField[shotRow][shotCol] = 'X';
                        if (ship.isDestroyed()) {
                            System.out.println("You sank a ship");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                    }
                }
            }
        } else {
            battleField[shotRow][shotCol] = 'M';
            System.out.println("You missed!");
        }
    }
    //Check if all ships are destroyed to determine if player lost
    public boolean lostTheGame(){
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void placeShipOnCoordinate(Coordinates coordinates) {
        if (coordinates.areHorizontal()) {
            for (int i = coordinates.getSecondColIndice(); i >= coordinates.getFirstColIndice(); i--) {
                battleField[coordinates.getFirstRowIndice()][i] = 'O';
            }
        } else {
            for (int i = coordinates.getSecondRowIndice(); i >= coordinates.getFirstRowIndice(); i--) {
                battleField[i][coordinates.getFirstColIndice()] = 'O';
            }
        }
    }

    public boolean allowedToPlaceShip(Coordinates coordinates) {
        for (Ship ship : ships) {
            //find out if there are any coordinates of other ships near the one being placed
            if (ship != null) {
                for (int i = coordinates.getFirstRowIndice() - 1; i <= coordinates.getSecondRowIndice() + 1; i++) {
                    for (int j = coordinates.getFirstColIndice() - 1; j <= coordinates.getSecondColIndice() + 1; j++) {
                        if ((i == ship.getShipCoordinates().getFirstRowIndice() && j == ship.getShipCoordinates().getFirstColIndice()) ||
                                (i == ship.getShipCoordinates().getSecondRowIndice() && j == ship.getShipCoordinates().getSecondColIndice())) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
