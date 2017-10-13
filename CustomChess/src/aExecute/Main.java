package aExecute;

import gameController.Chess960GameController;
import gameController.GameController;
import gameController.StandardGameController;
import input.ConsoleReader;

public class Main {

	public Main(String[] args) {
		switch (args[0]) {
		case "standard":
		case "Standard":
			startGame(new StandardGameController());
			break;
		case "chess960":
		case "Chess960":
		case "960":
			startGame(new Chess960GameController());
			break;
		}
	}

	private void startGame(GameController game) {
		game.getBoard().draw();
		ConsoleReader reader = new ConsoleReader(game);
		reader.inputReceiving();
	}

	public Main() {
		startGame(new StandardGameController());
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			new Main(args);
		} else {
			new Main();
		}
	}
}
