import java.util.Arrays;
import java.util.Scanner;

public class EightQueens {

	public static void fillBoard(char[][] board) {
		for (int i = 0; i < board.length; i++)
			Arrays.fill(board[i], ' ');
		
	}
	
	public static void drawGameBoard(char[][] board) {	
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(Character.toString(board[i][j]) + "|");
			}
			System.out.println();
		}
	}
	
	public static boolean isPositionUsable(char[][] board, int row, int col) {
		if (board[row][col] == 'x' || board[row][col] == 'Q')
			return false;
		return true;
	}
	
	public static boolean isOutOfRange(char[][] board, int row, int col) {
		if (row > (board.length - 1) || col > (board.length - 1))
			return true;
		else if (row < 0 || col < 0)
			return true;
		
		return false;
	}
	
	public static boolean isBoardUsable(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ')
					return true;
			}
		}
		return false;
	}
	
	public static void disableRows(char[][] board, int queenRow) {
		int rightBoundary = board.length - 1;
		int leftBoundary  = 0;
		
		for (int i = queenRow; i >= leftBoundary; i--) {
			board[queenRow][i] = 'x';
		}
		
		for (int i = queenRow; i <= rightBoundary; i++) {
			board[queenRow][i] = 'x';
		}
	}
	
	public static void disableColumns(char[][] board, int queenCol) {
		int upperBoundary = board.length - 1;
		int lowerBoundary = 0;
		
		for (int i = queenCol; i >= lowerBoundary; i--) {
			board[i][queenCol] = 'x';
		}
		
		for (int i = queenCol; i <= upperBoundary; i++) {
			board[i][queenCol] = 'x';
		}
	}
	
	public static void disableDiagonals(char[][] board, int queenRow, int queenCol) {
		int rightBoundary = board.length - 1;
		int leftBoundary = 0;
		
		// left diagonal
		for (int i = (queenRow), j = (queenCol); 
				(i >= leftBoundary && j >= leftBoundary); i--, j-- ) {
			board[i][j] = 'x';
		}
		
		for (int i = (queenRow), j = (queenCol); 
				(i <= rightBoundary && j <= rightBoundary); i++, j++ ) {
			board[i][j] = 'x';
		}
		
		// right diagonal
		for (int i = (queenRow), j = (queenCol); 
				(i <= rightBoundary && j >= leftBoundary); i++, j-- ) {
			board[i][j] = 'x';
		}
		
		for (int i = (queenRow), j = (queenCol); 
				(i >= leftBoundary && j <= rightBoundary); i--, j++ ) {
			board[i][j] = 'x';
		}
	}
	
	public static void placeQueenAtPosition(char[][] board, int queenRow, int queenCol) {
		board[queenRow][queenCol] = 'Q';
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char[][] gameBoard = new char[8][8];
		int queenRow, queenCol;
		int queensPlaced = 0;
		
		System.out.println("Place the queen in the corresponding position.\n"
				+ "If you want to start over, type -1 for row and -1 for column");
		fillBoard(gameBoard);
		
		while (queensPlaced < 8) {
			drawGameBoard(gameBoard);
			if (!isBoardUsable(gameBoard))
				break;
			
			System.out.print("Input row: ");
			queenRow = input.nextInt();
			System.out.print("Input column: ");
			queenCol = input.nextInt();
			
			if (queenRow == -1 || queenCol == -1) {
				fillBoard(gameBoard);
				continue;
			}
			
			if (!isOutOfRange(gameBoard, queenRow, queenCol)) {
				if (isPositionUsable(gameBoard, queenRow, queenCol)) {
					disableColumns(gameBoard, queenCol);
					disableRows(gameBoard, queenRow);
					disableDiagonals(gameBoard, queenRow, queenCol);
					placeQueenAtPosition(gameBoard, queenRow, queenCol);
					queensPlaced++;
				}
				else {
					System.out.println("You can't put the queen at that position!!\n"
							+ "Try again!.");
				}
			}
			else {
				System.out.println("That position doesn't exist on the board!");
			}
		}
		
		if (queensPlaced == 8) {
			drawGameBoard(gameBoard);
			System.out.println("Congrats!");
		}
		else
			System.out.println("Try again harder next time!");
		
		input.close();
	}
	
	
}
