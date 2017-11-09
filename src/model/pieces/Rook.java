package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Rook extends Piece {
	
	public Rook(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic(){
		setMoveLogic(new MoveLogic(getBoard(), this, "n,0;F|0,n;F"));
	}
	
	@Override
	public void initializeView() {
		setView(new PieceView(this, "rook" + getColor() + ".png"));
	}
}
