package moveLogic.additionalActions;

import static helper.Helper.X;
import static helper.Helper.Y;
import static helper.Helper.pos;

import gameController.GameController;
import model.Board;
import model.pieces.King;
import model.pieces.Piece;

public class CastleAction implements AdditionalAction {

	@Override
	public void execute(GameController game, Piece piece, int[] newPos) {
		if(!piece.getType().contains(King.class)){
			return;
		}
		Board board = game.getBoard();
		
		switch(newPos[X]) {
		case 2: Piece rook = board.getPieceOfSquare(pos(0, newPos[Y]));
				board.setPieceToNewPosition(rook, pos(3, newPos[Y]));
				break;
		case 6: rook = board.getPieceOfSquare(pos(7, newPos[Y]));
				board.setPieceToNewPosition(rook, pos(5, newPos[Y]));
				break;
		}
	}
}
