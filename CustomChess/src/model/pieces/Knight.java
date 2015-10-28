package model.pieces;

import controller.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class Knight extends Piece {
	
	public Knight(PlayerColor color, int posX, int posY) {
		super(color, posX, posY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "2,1|1,2");
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "knight" + getColor() + ".png");
	}
}
