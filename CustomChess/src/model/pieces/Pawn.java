package model.pieces;

import controller.MoveLogic;

public class Pawn extends Piece {
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "1,+1,X|0,+1,M|0,+2,MBF");
	}
}
