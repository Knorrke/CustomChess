package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Bishop extends Piece {
	
	public Bishop(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic(){
		setMoveLogic(new MoveLogic(getBoard(), this, "n,n;F"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "bishop" + getColor() + ".png"));
	}
}
