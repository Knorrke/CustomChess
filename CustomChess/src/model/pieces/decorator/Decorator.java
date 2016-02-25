package model.pieces.decorator;

import java.awt.Graphics;

import model.pieces.Piece;

public abstract class Decorator extends Piece {
	public Piece wrappedPiece;
	
	public Decorator(Piece wrappedPiece){
		super(wrappedPiece.getColor(),wrappedPiece.getBoard(), wrappedPiece.getPosition());
		this.wrappedPiece = wrappedPiece;
	}
	
	@Override
	final public boolean moveCorrect(int[] newPos) {
		return super.moveCorrect(newPos) && wrappedPiece.moveCorrect(newPos);
	}
	
	@Override
	final public void draw(Graphics g) {
		super.draw(g);
		wrappedPiece.draw(g);
		
		//TODO: Add possibility to draw on top of piece.
	}
	
	 @Override
	 /**
	  * Default implementation. Uses view of wrapped Piece. 
	  * Override this to add a customized view
	  */
	public void initializeView() {
		setView(g->{});
	}
	 
	@Override
	/**
	 * Default implementation. No additional check.
	 * Override this to add a customized move logic
	 */
	public void initializeMoveLogic(){
		setMoveLogic(pos->true);
	}
}
