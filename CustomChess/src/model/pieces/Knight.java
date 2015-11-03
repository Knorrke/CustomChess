package model.pieces;

import controller.MoveLogic;
import view.PieceView;

public class Knight extends Piece {
	
	@Override
	public void initializeMoveLogic(){
		MoveLogic ml = new MoveLogic(board, this, "2,1|1,2");
		ml.addAdaptersAutomatically();
		moveLogic = ml;
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "knight" + getColor() + ".png");
	}
}
