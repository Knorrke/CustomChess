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
			boolean choose = true;
			for (Move move : moves) {
				console.print(move.toString());
			}
			while(choose) {
				console.println("Which move do you want to select");
				try {
					int index = Integer.parseInt(reader.readLine()); 
					if (index < moves.size() ) {						
						chosenMove = moves.get(index);
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
