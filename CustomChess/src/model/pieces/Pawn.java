package model.pieces;

import controller.MoveLogic;
import view.PieceView;

public class Pawn extends Piece {
	
	@Override
	public void initializeMoveLogic(){
		MoveLogic ml = new MoveLogic(board, this, "1,+1;C|0,+1;M|0,+2;MBF");
		ml.addBehavioursAutomatically();
		moveLogic = ml;
	}
	
	@Override
	public void initializeView() {
		view = new PieceView(this, "pawn" + getColor() + ".png");
	}
}
