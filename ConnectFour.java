import java.util.Scanner;
import java.util.Arrays;

public class ConnectFour {

	public static boolean isConsecutiveFour(char[][] gameBoard, char color) {

		int counter = 0;

		for (int row = 0; row < gameBoard.length; row++) {
			for (int column = 0; column < (gameBoard[0].length - 3); column++) {
				
				// for horizontal matching
				for (int k = 0; k < 4; k++) {
					if (color == gameBoard[row][k + column])
						counter++;
					else
						break;
					
					if (counter == 4)
						return true;
				}

				counter = 0;
			}
		}

		for (int row = 0; row < (gameBoard.length - 3); row++) {
			for (int column = 0; column < (gameBoard[0].length - 3); column++) {	

				// for vertical matching
				for (int k = 0; k < 4; k++) {
					if (color == gameBoard[k + row][column]) {
						counter++;
					}
					else 
						break;

					if (counter == 4)
						return true;
				}
				counter = 0;
				
				// right diagonal matching
				for (int k = 0; k < 4; k++) {
					if (color == gameBoard[row + k][column + k])
						counter++;
					else 
						break;

					if (counter == 4)
						return true;
				}
				counter = 0;
				
			}
			
			// left diagonal matching
			for (int column = (gameBoard[0].length - 1); column > 2; column--) {	
				for (int k = 0; k < 4; k++) {
					if (color == gameBoard[k + row][column - k])
						counter++;

					if (counter == 4)
						return true;
				}
				counter = 0;
			}
		}
		return false;
	}
	
	
	public static void drawGameBoard(char[][] gameBoard) {
		
		for(int i = 0; i < gameBoard.length; i++) {
			
			for (int j = 0; j < gameBoard[0].length; j++)
				System.out.print(Character.toString(gameBoard[i][j]) + "|");
			
			System.out.println();
		}
	}
	
	public static void putPieceInBoard(int column, char color, char[][] gameBoard){
		
		int rows = gameBoard.length;
		
		for (int i = (rows - 1); i >= 0; i--) {
			
			if (gameBoard[i][column] != ' ')
				continue;
			else {
				gameBoard[i][column] = color;
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		char[][] gameBoard = new char[6][7];
		int turn = 1;
		int playerMove;
		char color = 'R';
		
		for (int i = 0; i < gameBoard.length; i++)
			Arrays.fill(gameBoard[i], ' ');
		
		 do {
			drawGameBoard(gameBoard);
			
			if (turn == 1) {	
				color = 'R';
				System.out.print("Drop a red disk at column (0 - 6): ");
				turn *= -1;
			} else {
				color = 'Y';
				System.out.println("Drop a yellow disk at column (0 - 6): ");
				turn *= -1;
			}
			playerMove = input.nextInt();
			putPieceInBoard(playerMove, color, gameBoard);
			
		} while (!isConsecutiveFour(gameBoard, color));
		
		input.close();
		
		turn *= -1;
		drawGameBoard(gameBoard);
		
		if (turn == 1)
			System.out.println("Red player wins!");
		else
			System.out.println("Yellow player wins!");
			

	}

}
