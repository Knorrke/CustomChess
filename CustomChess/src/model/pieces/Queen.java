package model.pieces;

import controller.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Queen extends Piece {
	
	public Queen(PlayerColor color, int posX, int posY) {
		super(color, posX, posY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "n,n,F|n,0,F|0,n,F");
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "queen" + getColor() + ".png");
	}
}
