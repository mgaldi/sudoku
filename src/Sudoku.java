import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {

    public static final int BOARD_SIZE = 9;
    public static final int SUB_BOARD_SIZE = (int) Math.sqrt(BOARD_SIZE);

    private static int[][] board;

    public static void main(String[] args) {

        board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9},
        };

        Sudoku sudoku = new Sudoku();
        // sudoku.enterBoard();
        if (sudoku.fillBoard()) {
            System.out.println("Sudoku solved");
            sudoku.printBoard();
        } else {
            System.out.println("Sudoku not solved");
        }
    }


    private void enterBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("Enter numbers for row n.%d:", (i + 1));
            String input = in.nextLine();
            String[] splitInput = input.split("\\s+");
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = Integer.parseInt(splitInput[j]);
        }
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
                // if the end of the row is reached the second loop in the method
                // should begin from 0 instead of the value of the 'col' argument
                if (j == BOARD_SIZE - 1)
                    col = 0;
            }
        }
        return true;

        /*
        if (row == BOARD_SIZE)
            return true;
        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
                int i, j = 0;
                board[row][col] = num;
                rowsLoop:
                for (i = 0; i < BOARD_SIZE; i++) {
                    for (j = 0; j < BOARD_SIZE; j++) {
                        if (board[i][j] == 0)
                            break rowsLoop;
                    }
                }
                if (fillBoard(i, j))
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
         */
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

}
