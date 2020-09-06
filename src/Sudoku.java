import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {

    public static final int BOARD_SIZE = 9;
    public static final int SUB_BOARD_SIZE = (int) Math.sqrt(BOARD_SIZE);

    private static int[][] board;

    private void enterBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("Enter numbers for row n. %d: ", (i + 1));
            String input = in.nextLine();
            String[] splitInput = input.split("\\s+");
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = Integer.parseInt(splitInput[j]);
        }
        in.close();
    }

    public boolean fillBoard() {
        return fillBoard(0, 0);
    }

    private boolean fillBoard(int row, int col) {
        for (int i = row; i < BOARD_SIZE; i++) {
            for (int j = col; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    for (int n = 1; n <= BOARD_SIZE; n++) {
                        if (isSafe(i, j, n)) {
                            board[i][j] = n;
                            if (fillBoard(i, j))
                                return true;
                            board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
            // when the end of the row is reached the variable 'col' is set to 0
            // in order to start iterating from the first element of the next row
            // (the variable 'col' is used in the second 'for' cycle in the method)
            col = 0;
        }
        return true;
    }

    private boolean isSafe(int row, int col, int num) {
        // check entire row
        for (int i = 0; i < BOARD_SIZE; i++)
            if (board[row][i] == num && i != col)
                return false;
        // check entire column
        for (int j = 0; j < BOARD_SIZE; j++)
            if (board[j][col] == num && j != row)
                return false;
        // check sub-board
        int subRowFirst = (row / SUB_BOARD_SIZE) * SUB_BOARD_SIZE;
        int subColFirst = (col / SUB_BOARD_SIZE) * SUB_BOARD_SIZE;
        for (int i = subRowFirst; i < subRowFirst + 3; i++)
            for (int j = subColFirst; j < subColFirst + 3; j++)
                if (board[i][j] == num && i != row && j != col)
                    return false;
        return true;
    }

    private void printBoard() {
        for (int[] row : board)
            System.out.println(Arrays.toString(row));
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.enterBoard();
        if (sudoku.fillBoard()) {
            System.out.println("Sudoku solved");
            sudoku.printBoard();
        } else {
            System.out.println("Sudoku not solved");
        }
    }

}
