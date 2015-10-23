package model.pieces;

import controller.MoveLogic;

public class Bishop extends Piece {
	
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "n,n,F");
	}
}
