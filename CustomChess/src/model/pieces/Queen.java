package model.pieces;

import controller.MoveLogic;

public class Queen extends Piece {
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "n,n,F|n,0,F|0,n,F");
	}
}
