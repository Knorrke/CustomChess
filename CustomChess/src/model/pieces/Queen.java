package model.pieces;

import controller.MoveLogic;
import view.PieceView;

public class Queen extends Piece {

	@Override
	public void initializeMoveLogic(){
		MoveLogic ml = new MoveLogic(board, this, "n,n,F|n,0,F|0,n,F");
		ml.addBehavioursAutomatically();
		moveLogic = ml;
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "queen" + getColor() + ".png");
	}
}
