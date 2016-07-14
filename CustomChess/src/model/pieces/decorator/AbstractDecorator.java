package model.pieces.decorator;

import java.util.Set;

import model.pieces.Piece;

public abstract class AbstractDecorator extends Piece {
	public Piece wrappedPiece;
	
	public AbstractDecorator(Piece wrappedPiece){
		super(wrappedPiece.getColor(),wrappedPiece.getBoard(), wrappedPiece.getPosition());
		this.wrappedPiece = wrappedPiece;
	}
	
	@Override
	final public void draw() {
		super.draw();
		wrappedPiece.draw();
		drawHook();
	}
	
	 @Override
	 /**
	  * Default implementation. Uses view of wrapped Piece. 
	  * Override this to add a customized view
	  */
	public void initializeView() {
		setView(()->{});
	}
	 
	/**
	 * Hook for drawing on top of piece
	 */
	public void drawHook() {}
	
	@Override
	/**
	 * Returns a set of types of the wrapped piece as well as the runtime class of this decorator
	 */
	public Set<Class<?>> getType() {
		Set<Class<?>> set = wrappedPiece.getType();
		set.add(this.getClass());
		return set;
	}
}
