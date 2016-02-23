package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Queen extends Piece {

	public Queen(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic(){
		setMoveLogic(new MoveLogic(getBoard(), this, "n,n;F|n,0;F|0,n;F"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "queen" + getColor() + ".png"));
	}
}
