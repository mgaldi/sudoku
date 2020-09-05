Programmer: Marco Galdi
Class: CS245
Date: 09/04/2020

<b> Functions </b>

The program has 5 functions excluding the main function.
The enterBoard function which returns void and takes no parameters initializes the board and takes the input from the command line. The input is taken as "num num num" row by row. There is no input validation for enterBoard, invalid input is not checked.
The public fillBoard function which returns a boolean and takes no parameters initialize the private function fillBoard(int row, int col) that has a different signature. The function looks for the first occurence of a 0 and tries by placing a 1 by using the function isSafe. Once the number is placed, the function calls itself recursively. If some number down the stack appears to not be safe to place, the previous function call sets the number to 0 and tries again with the next number.
The isSafe function which returns a boolean and takes as parameters the row, the column and the number to assign, checks if the cell at position row,col has no other number equal to num horizontally, vertically, and in the 3x3 sub-board.
The printBoard function which returns void and takes no parameters, is used to print the whole board.

<b> Complexity</b>

In terms of space complexity, the most signifcant variable is the 2D array used for the board with complexity O(n^2). The other space is used is less significant than n^2.

Time complexity for the function enterBoard and isSafe is O(n^2)
For the function fillBoard() I used the recursion tree approach in order to determine its time complexity.

Usually some cells are already occupied. Let's assume x cells are occupied. The operations will be made on the remaining cells
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

Time complexity for the function fillBoard will be O(9^m)