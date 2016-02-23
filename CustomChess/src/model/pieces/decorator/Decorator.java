package model.pieces.decorator;

import model.pieces.Piece;

public abstract class Decorator extends Piece {
	public Piece wrappedPiece;
	
	public Decorator(Piece wrappedPiece){
		super(wrappedPiece.getColor(),wrappedPiece.getBoard(), wrappedPiece.getPosition());
		this.wrappedPiece = wrappedPiece;
	}
	
	@Override
	public boolean moveCorrect(int[] newPos) {
		return super.moveCorrect(newPos) && wrappedPiece.moveCorrect(newPos);
	}
	
	 @Override
	public void initializeView() {
		setView(wrappedPiece::draw);
	}
	 
	@Override
	public void initializeMoveLogic(){
		setMoveLogic(wrappedPiece::moveCorrect);
	}
}
