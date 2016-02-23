package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Pawn extends Piece {
	
	public Pawn(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic(){
		setMoveLogic(new MoveLogic(getBoard(), this, "1,+1;C|0,+1;M|0,+2;MBF"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "pawn" + getColor() + ".png"));
	}
}
