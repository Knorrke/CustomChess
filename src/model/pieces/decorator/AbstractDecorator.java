package model.pieces.decorator;

import java.util.Set;

import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public abstract class AbstractDecorator extends Piece {
	public Piece wrappedPiece;
	
	public AbstractDecorator(Piece wrappedPiece){
		super(wrappedPiece.getColor(),wrappedPiece.getBoard(), wrappedPiece.getPosition());
		this.wrappedPiece = wrappedPiece;
		initializeMoveLogic();
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
	 
	 @Override
	 /**
	  * Default implementation. Doesn't add any logic.
	  * Override this to add a customized logic
	  */
	public void initializeMoveLogic() {
		if (wrappedPiece != null) 
			setMoveLogic(wrappedPiece.getMoveLogic());
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
	
	@Override
	public void setBoard(Board board) {
		super.setBoard(board);
		if (wrappedPiece != null) wrappedPiece.setBoard(board);
	}
	
	@Override
	public void setColor(PlayerColor color) {
		super.setColor(color);
		if (wrappedPiece != null) wrappedPiece.setColor(color);
	}
	
	@Override
	public void setMoved(boolean moved) {
		super.setMoved(moved);
		if (wrappedPiece != null) wrappedPiece.setMoved(moved);
	}
	
	@Override
	public void setPosition(int posX, int posY) {
		super.setPosition(posX, posY);
		if (wrappedPiece != null) wrappedPiece.setPosition(posX, posY);
	}
	
	@Override
	public void setPosition(int[] pos) {
		super.setPosition(pos);
		if (wrappedPiece != null) wrappedPiece.setPosition(pos);
	}
}
