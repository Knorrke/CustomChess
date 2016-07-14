package gameController.gameConditionsStrategy;

import java.util.ArrayList;

import model.Board;
import model.Square;
import model.pieces.Piece;
import model.pieces.decorator.Mighty;

public class MightyNotAttacked implements GameCondition {

	@Override
	public boolean isGameIntegrityEnsured(Board board, Piece piece, int[] newPos) {
		Square sq = board.getSquares()[newPos[0]][newPos[1]];
		Piece capturedPiece = sq.getPiece();
		int[] oldPos = piece.getPosition();
		
		try{
			board.setPieceToNewPosition(piece, newPos);
			ArrayList<Piece> mightyPieces = new ArrayList<>();
			Square[][] squares = board.getSquares();
			for(Square[] row : squares) {
				for(Square square : row) {
					if(square.hasPiece() &&
						square.getPiece().getColor().equals(piece.getColor()) &&
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
		} finally {
			board.setPieceToNewPosition(piece, oldPos);
			sq.setPiece(capturedPiece);
		}
	}
}
