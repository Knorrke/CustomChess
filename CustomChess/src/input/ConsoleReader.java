package input;

import static helper.Helper.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gameController.GameController;

public class ConsoleReader {
	
	private GameController gameController;
	
	public ConsoleReader(GameController gctrl) {
		this.gameController = gctrl;
	}
	
	public void inputReceiving() {
		Thread receiver = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						String str = reader.readLine();
						handle(str);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		};
		receiver.start();
	}

	public void handle(String input) throws IllegalArgumentException {
		if (!input.contains(" ")) {
			throw new ParserException(input);
		}
		
		String[] elements = input.split(" ");
		int[][] positions = new int[2][];
		for(int i=0; i<elements.length; i++) {
			if (elements[i].contains(",")) {
				String[] tmp = elements[i].split(",");
				positions[i] = new int[] {Integer.parseInt(tmp[0]) - 1, Integer.parseInt(tmp[1]) - 1};
			}else {
				positions[i] = pos(elements[i]);
				if (positions[i][0] < 0 || positions[i][0] > gameController.getBoard().getSquares().length) {
					throw new ParserException(input);
				}
			}
		}
		
		boolean correct = gameController.move(positions[0], positions[1]);
		if (!correct) {
			console.println("You can't move " + input);
		}
	}
	
	private class ParserException extends IllegalArgumentException {
		private static final long serialVersionUID = 1L;

		public ParserException(String input) {
			super("Couldn't parse " + input + ". Please use the form x1,y1;x2,y2 or the form e2;e4");
		}
	}
}
