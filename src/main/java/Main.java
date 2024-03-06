import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static int getXRow(Board board) {
        boolean isValid;
        int xRow;
        System.out.println("Enter the row you want to place in: ");
        do {
            isValid = true;
            System.out.println("You can choose between 1 and 7: ");
            xRow = scanner.nextInt();
            if (xRow < 1 || xRow > 7) {
                isValid = false;
            }
            if (isValid && (board.getPlayerInSquare(xRow, 6) != 0)) {
                System.out.println("This row is already full. Please choose again: ");
                isValid = false;
            }
        } while (!isValid);
        return xRow;
    }

    public static int getAvailableInRow(Board board, int row) {
        int line = 1;
        while (board.getPlayerInSquare(row, line) != 0) {
            line++;
            if (line >= 6) {
                break;
            }
        }
        return line;
    }

    public static boolean didPlayerWinRow(Board board, int player) {
        boolean winner = false;
        int count;
        for (int x = 1; x <= 7; x++) {
            count = 0;
            for (int y = 1; y <= 6; y++) {
                if (board.getPlayerInSquare(x, y) == player) {
                    count++;
                    if (count == 4) {
                        winner = true;
                        break;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return winner;
    }

    public static boolean didPlayerWinLine(Board board, int player) {
        boolean winner = false;
        int count;
        for (int y = 1; y <= 6; y++) {
            count = 0;
            for (int x = 1; x <= 7; x++) {
                if (board.getPlayerInSquare(x, y) == player) {
                    count++;
                    if (count == 4) {
                        winner = true;
                        break;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return winner;
    }

    public static boolean didPlayerWinDiagonal(Board board, int player) {
        boolean winner = false;
        int count1, count2;
        for (int y = 1; y <= 6; y++) {
            count1 = 0;
            for (int x = 1; x <= 7; x++) {
                if (y + count1 <= 6) {
                    count1 = board.getPlayerInSquare(x, y + count1) == player ? count1 + 1 : 0;
                }
                if (count1 == 4) {
                    winner = true;
                    break;
                }
            }
        }
        if(!winner) {
            for (int y = 6; y >= 1; y--) {
                count2 = 0;
                for (int x = 1; x <= 7; x++) {
                    if (y - count2 >= 1) {
                        count2 = board.getPlayerInSquare(x, y - count2) == player ? count2 + 1 : 0;
                    }
                    if (count2 == 4) {
                        winner = true;
                        break;
                    }
                }
            }
        }
        return winner;
    }

    public static boolean didPlayerWin(Board board, int player1, int player2) {
        boolean firstPlayer = didPlayerWinRow(board, player1) ||
                didPlayerWinLine(board, player1) || didPlayerWinDiagonal(board, player1);
        boolean secondPlayer = didPlayerWinRow(board, player2) ||
                didPlayerWinLine(board, player2) || didPlayerWinDiagonal(board, player2);
        return firstPlayer || secondPlayer;
    }

    public static boolean isBoardFull(Board board) {
        boolean isFull = true;
        for (int x = 1; x <= 7; x++) {
            for (int y = 1; y <= 6; y++) {
                if (board.getPlayerInSquare(x, y) == 0) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public static boolean isGameOver(Board board, int player1, int player2) {
        return didPlayerWin(board, player1, player2) || isBoardFull(board);
    }

    public static void main(String[] args) {
        Board board = new Board();
        int xRow;
        int yLine;
        int count = 1;
        boolean didPlayerWin = false;
        while (!isGameOver(board, 1, 2)) {
            xRow = getXRow(board);
            yLine = getAvailableInRow(board, xRow);
            if (count % 2 == 1) {
                board.placeSquare(xRow, yLine, 1);
            } else {
                board.placeSquare(xRow, yLine, 2);
            }
            if (didPlayerWin(board, 1, 2)) {
                didPlayerWin = true;
                System.out.println("Winner!");
            }
            count++;
        }
        if (!didPlayerWin) {
            System.out.println("The board is full, no one won. Game over!");
        }
    }

    //Method 1: Board board = new Board();
    //This method shows the initial empty board


    //Method 2: placeSquare (int x, int y, int player)
    //This method gets 3 arguments
    //x is the x position of the square to be placed
    //y is the y position of the square to be placed
    //player can be either of value 1 or 2. For the value 1, a red square is being placed, For the value 2, a
    // yellow
    // square


    //Method 3: int getPlayerInSquare (int x, int y)
    //This method gets 2 arguments
    //x is the x position of the asked square
    //y is the y position of the asked square
    //The method returns an int value: if the value is 0 - the square is empty, if the value is 1 - the square is
    // occupied by
    //player 1, if the value is 2 - the square is occupied by player 2


}