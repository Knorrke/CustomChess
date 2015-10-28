package model.pieces;

import controller.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Bishop extends Piece {
	
	public Bishop(PlayerColor color, int posX, int posY) {
		super(color, posX, posY);
	}

	@Override
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "n,n,F");
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "bishop" + getColor() + ".png");
	}
}
