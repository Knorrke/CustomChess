package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class King extends Piece {

	public King(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic(){
		setMoveLogic(new MoveLogic(getBoard(), this, "1,1|1,0|0,1|2,0;R;R"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "king" + getColor() + ".png"));
	}
}
