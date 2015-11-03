package model.pieces;

import controller.MoveLogic;
import view.PieceView;

public class King extends Piece {

	@Override
	public void initializeMoveLogic(){
		MoveLogic ml = new MoveLogic(board, this, "1,1|1,0|0,1|2,0,B*F");
		ml.addAdaptersAutomatically();
		moveLogic = ml;
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "king" + getColor() + ".png");
	}
}
