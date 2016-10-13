package aExecute;

import gameController.GameController;
import gameController.StandardGameController;
import input.ConsoleReader;

public class Main {
	
	public Main(String[] args) {
	//TODO: load game from arguments
	}
	
	public Main() {
		GameController game = new StandardGameController();
		game.getBoard().draw();
		ConsoleReader reader = new ConsoleReader(game);
		reader.inputReceiving();
	}

	public static void main(String[] args) {
		if(args.length > 0) {			
			new Main(args);
		} else {
			new Main();
		}
	}
}
