package model.pieces;

import controller.MoveLogic;

public class King extends Piece {

	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "1,1|1,0|0,1|2,0,B*F");
	}
}
