package gameController.gameConditionsStrategy.EndConditions;

import static helper.Helper.*;
import java.util.ArrayList;
import gameController.GameController;
import gameController.GameState;
import model.Board;
import model.pieces.Piece;
import model.pieces.decorator.Mighty;
import player.PlayerColor;

public class MateConditions implements GameEndCondition {

	@Override
	public GameState isEndConditionMet(GameController game) {
		Board board = game.getBoard();
		PlayerColor player = game.getCurrentPlayer();
		PlayerColor opponent = player.getOppositColor();
		
		ArrayList<Piece> playerPieces = board.getAllPieces();
		playerPieces.removeIf(piece -> piece.getColor().equals(opponent));
		
		for (Piece piece : playerPieces) {
			for(int i=0; i<board.size(X); i++) {
				for (int j=0; j<board.size(Y); j++) {				
					if (game.moveAllowed(piece, pos(i,j))) {
						return GameState.RUNNING;
					}
				}
			}
		}
		
		playerPieces.removeIf(piece-> !piece.getType().contains(Mighty.class));
		
		for (Piece mighty : playerPieces) {
			if (!board.isAttacked(mighty.getColor(), mighty.getPosition())) {
				return GameState.DRAW;
			}
		}
		return player == PlayerColor.WHITE ? GameState.BLACKWIN : GameState.WHITEWIN;
	}

	
}
