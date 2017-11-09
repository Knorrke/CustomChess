package gameController.gameConditionsStrategy.IntegrityConditions;

import java.util.ArrayList;

import model.Board;
import model.Square;
import model.pieces.Piece;
import model.pieces.decorator.Mighty;
import moves.Move;

public class MightyNotAttacked implements GameIntegrityCondition {

	@Override
	public boolean isGameIntegrityEnsured(Board board, Move move) {
		try{
			move.execute(board);
			
			ArrayList<Piece> mightyPieces = new ArrayList<>();
			Square[][] squares = board.getSquares();
			for(Square[] row : squares) {
				for(Square square : row) {
					if(square.hasPiece() &&
						square.getPiece().getColor().equals(move.getPiece().getColor()) &&
						square.getPiece().getType().contains(Mighty.class)) {
						mightyPieces.add(square.getPiece());
					}
				}
			}
			
			for(Piece mighty : mightyPieces) {
				if(board.isAttacked(mighty.getColor().getOppositColor(), mighty.getPosition())) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			move.reverse(board);
		}
	}
}
