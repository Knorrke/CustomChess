package model.pieces;

import controller.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class King extends Piece {

	public King(PlayerColor color, int posX, int posY) {
		super(color, posX, posY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "1,1|1,0|0,1|2,0,B*F");
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "king" + getColor() + ".png");
	}
}
