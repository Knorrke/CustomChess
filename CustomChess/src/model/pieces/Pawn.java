package model.pieces;

import controller.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Pawn extends Piece {
	
	public Pawn(PlayerColor color, int posX, int posY) {
		super(color, posX, posY);
	}

	@Override
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "1,+1,X|0,+1,M|0,+2,MBF");
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "pawn" + getColor() + ".png");
	}
}
