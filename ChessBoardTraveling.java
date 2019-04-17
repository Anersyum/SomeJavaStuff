import java.util.Scanner;
import java.util.ArrayList;

public class ChessBoardTraveling {

	static void GetPositions(String position) {
		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		int destinationX, destinationY;
		
		for (int i = 0; i < position.length(); i++) {
			if (Character.isDigit(position.charAt(i))) {
				coordinates.add((int)(position).charAt(i));
			}		
		}
		destinationX = coordinates.get(2) - coordinates.get(0);
		destinationY = coordinates.get(3) - coordinates.get(1);
		
		ChessBoardPaths(destinationX, destinationY);	
	}
	
	static void ChessBoardPaths(int destX, int destY) {
		int[][] allPaths = new int[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if((i == 0) || (j == 0)) 
					allPaths[i][j] = 1;
				else {
					allPaths[i][j] = allPaths[i - 1][j] + allPaths[i][j - 1];
				}
			}
		}
		System.out.println("All possible combinations are: " + allPaths[destX][destY]);
		
	}


	public static void main(String[] args) 
	{
		String path;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter to what place on the chessboard you want to know how many possible routes there\nare " 
			+ "going only down and right.\n(the format is 1144 (from the coordinate 1 1 to  the 4 4 coordinate)");
		path = input.nextLine(); 
		input.close();
		
		GetPositions(path);
		
	}

}
