package gameController;

import static helper.Helper.console;
import static helper.Helper.pos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import gameController.gameConditionsStrategy.EndConditions.MateConditions;
import gameController.gameConditionsStrategy.IntegrityConditions.MightyNotAttacked;
import model.Board;
import model.PieceFactory;
import player.PlayerColor;

public class Chess960GameController extends GameController {

	public Chess960GameController() {
		super(PlayerColor.WHITE);
		super.addIntegrityConditions(new MightyNotAttacked());
		super.addEndConditions(new MateConditions());
	}

	@Override
	protected Board setUpBoard() {
		Board board = new Board(this,8,8);
		
		Random rand = new Random();
		LinkedList<Integer> freePos = new LinkedList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7));

		Integer bish1 = freePos.remove(rand.nextInt(8)); //first bishop randomly on 1 of 8
		Integer bish2 = bish1%2 == 0 ? rand.nextInt(4)*2 + 1 : rand.nextInt(4)*2; //second bishop randomly on other color
		freePos.remove(bish2);
		Integer queen = freePos.remove(rand.nextInt(6)); //queen randomly on remaining 6
		Integer knight1 = freePos.remove(rand.nextInt(5)); //knight randomly on remaining 5
		Integer knight2 = freePos.remove(rand.nextInt(4)); //knight randomly on remaining 4
		Integer rook1 = freePos.remove(0); //remaining 3 squares are rook,king,rook
		Integer king = freePos.remove(0);
		Integer rook2 = freePos.remove(0);
		
		if(!freePos.isEmpty()) {
			throw new IllegalArgumentException("WHAT HAVE YOU DONE!! YOU KILLED ME\u2382\u2399!ad\u1920 fasdfa fawefj");
		}
		
		String[] pieces = new String[8];
		pieces[bish1] = "Bishop";
		pieces[bish2] = "Bishop";
		pieces[queen] = "Queen";
		pieces[knight1] = "Knight";
		pieces[knight2] = "Knight";
		pieces[rook1] = "Rook";
		pieces[rook2] = "Rook";
		pieces[king] = "Mighty King";
		for (int i=0; i<8; i++) {
			board.addPiece(PieceFactory.newPiece(board, pieces[i], PlayerColor.WHITE, pos(i, 0)));
			board.addPiece(PieceFactory.newPiece(board, pieces[i], PlayerColor.BLACK, pos(i, 7)));
			board.addPiece(PieceFactory.newPiece(board,"Pawn", PlayerColor.WHITE, pos(i,1)));
			board.addPiece(PieceFactory.newPiece(board, "Pawn", PlayerColor.BLACK, pos(i,6)));
		}

		return board;
	}

	@Override
	protected void endHook() {
		console.print("Game Over. ");
		switch(state) {
		case BLACKWIN:
			console.println("Black wins!");
			break;
		case DRAW:
			console.println("Draw!");
			break;
		case RUNNING:
			console.println("Wait... no it is not... my bad!");
			break;
		case WHITEWIN:
			console.println("White wins!");
			break;
		default:
			break;
		}
	}
}
