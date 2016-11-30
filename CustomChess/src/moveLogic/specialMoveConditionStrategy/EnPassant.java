package moveLogic.specialMoveConditionStrategy;

import static helper.Helper.X;
import static helper.Helper.Y;

import java.util.ArrayList;
import java.util.Arrays;

import model.Board;
import model.pieces.Pawn;
import model.pieces.Piece;
import moves.Move;
import player.PlayerColor;

public class EnPassant implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		if (!piece.getType().contains(Pawn.class) || board.getGameController().getMoves().isEmpty()) {
			return false;
		}
		
		PlayerColor player = piece.getColor();
		
		ArrayList<Move> moves = board.getGameController().getMoves();
		Move lastMove = moves.get(moves.size()-1);

		int[][] enPassantOpponentMove = (player.equals(PlayerColor.WHITE) 
				? new int[][]{{newPos[X], newPos[Y]+1},{newPos[X], newPos[Y] - 1}} 
				: new int[][]{{newPos[X], newPos[Y]-1},{newPos[X], newPos[Y] + 1}});
		if (!(lastMove.getPiece().getType().contains(Pawn.class) 
				&& Arrays.equals(lastMove.getFrom(), enPassantOpponentMove[0]) 
				&& Arrays.equals(lastMove.getTo(), enPassantOpponentMove[1]))){
			return false;
		}
		return true;
	}

}
