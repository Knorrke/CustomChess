package model.pieces;

import controller.MoveLogic;

public class Knight extends Piece {
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "2,1|1,2");
	}
}
