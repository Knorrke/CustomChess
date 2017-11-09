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
		setMoveLogic(new MoveLogic(getBoard(), this, "1,+1;C;P|1,+1;E;E|0,+1;M;P|0,+2;MNF"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "pawn" + getColor() + ".png"));
	}
}
