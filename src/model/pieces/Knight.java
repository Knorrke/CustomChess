package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Knight extends Piece {
	
	public Knight(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic(){
		setMoveLogic(new MoveLogic(getBoard(), this, "2,1|1,2"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "knight" + getColor() + ".png"));
	}
}
