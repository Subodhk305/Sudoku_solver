import java.util.*;
class Solution {
   public boolean isSafe(char[][] board, int row, int col, int number) {
       //column
       for(int i=0; i<board.length; i++) {
           if(board[i][col] == (char)(number+'0')) {
               return false;
           }
       }
      
       //row
       for(int j=0; j<board.length; j++) {
           if(board[row][j] == (char)(number+'0')) {
               return false;
           }
       }
      
       //grid
       int sr = 3 * (row/3);
       int sc = 3 * (col/3);
      
       for(int i=sr; i<sr+3; i++) {
           for(int j=sc; j<sc+3; j++) {
               if(board[i][j] == (char)(number+'0')) {
                   return false;
               }
           }
       }
       return true;
   }
   
   public boolean helper(char[][] board, int row, int col) {
       if(row == board.length) {
           return true;
       }
       int nrow = 0;
       int ncol = 0;
       if(col == board.length-1) {
           nrow = row + 1;
           ncol = 0;
       } else {
           nrow = row;
           ncol = col + 1;
       }
       if(board[row][col] != '.') {
           if(helper(board, nrow, ncol)) {
               return true;
           }
       } else {
           //fill the place
           for(int i=1; i<=9; i++) {
               if(isSafe(board, row, col, i)) {
                   board[row][col] = (char)(i+'0');
                   if(helper(board, nrow, ncol))
                       return true;
                   else
                       board[row][col] = '.';
               }
           }
       }
       return false;
   }
   
   public void solveSudoku(char[][] board) {
       helper(board, 0, 0);
   }public static void main(String[] args) {
        char[][] sudokuBoard = new char[9][9];
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the initial Sudoku board (use '.' for empty cells):");
        for (int i = 0; i < 9; i++) {
            String row = scanner.nextLine();
            for (int j = 0; j < 9; j++) {
                sudokuBoard[i][j] = row.charAt(j);
            }
        }
        
        Solution solution = new Solution();
        solution.solveSudoku(sudokuBoard);
        
        // Print the solved Sudoku board
        System.out.println("Solved Sudoku:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}