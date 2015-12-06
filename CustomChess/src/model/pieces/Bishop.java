package model.pieces;

import controller.MoveLogic;
import view.PieceView;

public class Bishop extends Piece {
	
	@Override
	public void initializeMoveLogic(){
		MoveLogic ml = new MoveLogic(board, this, "n,n;F");
		ml.addBehavioursAutomatically();
		moveLogic = ml;
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "bishop" + getColor() + ".png");
	}
}
