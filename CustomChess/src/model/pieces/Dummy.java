package model.pieces;

import java.awt.Graphics;

import controller.MoveLogicInterface;
import view.PieceViewInterface;

public class Dummy extends Piece{

	@Override
	public void initializeMoveLogic() {
		moveLogic = new MoveLogicInterface(){
			@Override
			public boolean moveCorrect(int[] newPos) {
				return false;
			}
		};
	}

	@Override
	public void initializeView() {
		view = new PieceViewInterface() {
			@Override
			public void drawPiece(Graphics g) {
			}
		};
	}

}
