**Programmer:** Marco Galdi

**Class:** CS245

**Date:** 09/04/2020

### Functions

The program has 5 functions excluding the `main` function.

The `enterBoard` function, which does not provide a return type and takes no parameters, initializes the board and receives input from the command line. The input is taken as *"num num num"* row by row. There is no input validation for `enterBoard`.

The `fillBoard` function, which returns a boolean value and takes no parameters, is responsible of making the first call to the function `fillBoard(int row, int col)` which has a different signature. The function looks for the first occurence of a `0` in the board starting from the coordinates of the current cell passed as arguments and attempts to replace it with a progressively growing number going from `1` to `9` until either a successful attempt is made or the maximum number is exceeded. The function checks if a number is safe to be placed by calling the `isSafe(int row, int col, int num)` function. If a number is placed successfully, the function calls itself recursively by passing the coordinates of the current cell as arguments. Otherwise, the number in the cell is set back to `0` and the function tries again with the next number; if the maximum number is exceeded, the function backtracks to the previous cell it worked on and tries to replace its current number with the next one, if any is available, otherwise it keeps backtracking. The function returns either if it is not able to completely fill the board or each empty cell is filled.

**Note:** if an invalid board (e.g. the same number appears twice in the same row) is fed through the `enterBoard` function, given that there is no input validation the function might still be able to complete the sudoku without noticing the incorrectness of the board since it is designed to skip pre-filled filled cells.


The `isSafe` function, which returns a boolean and takes as parameters the row, the column and the number to assign, checks if the cell denoted by the position `(row, col)` has no other number equal to `num` in the row, in the column and in the `3x3` sub-board in which the denoted cell resides.

The `printBoard` function, which has no return type and receives no parameters, is used to print the whole board on the console.

### Complexity

In terms of space complexity, the most significant variable is the 2D array used for the board with complexity `O(n^2)`, where `n` is the length of the board. The space occupied by index variables is less significant than `n^2`.

Time complexity for the function `enterBoard` and isSafe is `O(n^2)`.

For the function `fillBoard()` I used the recursion tree approach in order to determine its time complexity.

Usually some cells are already occupied. Let's assume `x` cells are occupied. The operations will work upon the remaining cells.

```
m = n - x
T(m) = m^3 + 9T(m-1)
|Recursive Call |Tree 						|Operations |	
|				|	  						|			|
|T(m)			|m^3						|m^3		| 1   = 9^0
|T(m-1)			|(m-1)^3 + (m-1)^3	[9TIMES]|m^3		| 9	  = 9^1
|				|(m-2)^3 + (m-2)^3	[9TIMES]|m^3		| 81  = 9^2
														  .
														  .
														  .	  = 9^m
```

Time complexity for the function `fillBoard` is `O(n^m)`, where `m` is the number of unoccupied cells. 