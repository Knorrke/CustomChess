package view;

import static helper.Helper.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import moves.Move;

public class SelectMoveView {
	public Move chooseMove(List<Move> moves) {
		Move chosenMove = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			boolean chooseAgain = true;
			int index = 0;
			for (Move move : moves) {
				console.print(String.format("%d: %s, ", index++, move));
			}
			while(chooseAgain) {
				console.println("Which move do you want to select");
				try {
					int chosenIndex = Integer.parseInt(reader.readLine()); 
					if (chosenIndex < moves.size() ) {						
						chosenMove = moves.get(chosenIndex);
						chooseAgain = false;
					}
				}catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return chosenMove;
	}
}
