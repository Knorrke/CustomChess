package model.pieces;

import controller.MoveLogic;

public class Rook extends Piece {
	public void initializeMoveLogic(){
		moveLogic = new MoveLogic(this, "n,0,F|0,n,F");
	}
}
