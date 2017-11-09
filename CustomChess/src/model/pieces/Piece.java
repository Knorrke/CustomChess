package model.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Board;
import model.Drawable;
import model.pieces.interfaces.MoveLogicInitializerInterface;
import model.pieces.interfaces.ViewInitializerInterface;
import moveLogic.MoveLogicInterface;
import moves.Move;
import player.PlayerColor;
import view.ViewInterface;

/**
 * Abstract class for all Pieces, provides color, board and position
 * and an accesspoint to the MoveLogic.
 * 
 * Subclasses must implement the methods of {@link MoveLogicInitializerInterface}
 * and {@link ViewInitializerInterface}
 * @author Benjamin
 */
public abstract class Piece implements MoveLogicInitializerInterface, ViewInitializerInterface, Drawable{
	
	protected PlayerColor color;
	protected int posX, posY;
	private MoveLogicInterface moveLogic;
	private ViewInterface view;
	private Board board;
	private boolean moved;
	
	public Piece(PlayerColor color, Board board, int[] pos){
		this.color = color;
		this.board = board;
		setPosition(pos);
		moved = false;
		initializeMoveLogic();
		initializeView();
	}
	
	/**
	 * @return the color
	 */
	public PlayerColor getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(PlayerColor color) {
		this.color = color;
	}
	
	/**
	 * @return the position as Integer array, where x is element 0 and y is element 1 
	 */
	public int[] getPosition(){
		return new int[] {posX, posY};
	}
	
	/**
	 * Sets the x and y position
	 * @param posX
	 * @param posY
	 */
	public void setPosition(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Sets the x and y position
	 * @param pos Array with pos[0] being the x position and pos[1] being the y position
	 */
	public void setPosition(int[] pos){
		if(pos.length != 2){
			throw new IllegalArgumentException();
		}else{
			this.posX = pos[0];
			this.posY = pos[1];
		}
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Move> getPossibleMoves(int[] newPos) {
		return moveLogic.getPossibleMoves(newPos);
	}
	
	public void setMoveLogic(MoveLogicInterface moveLogic){
		this.moveLogic = moveLogic;
	}
	
	@Override
	public void draw(){
		view.draw();
	}
	
	public void setView(ViewInterface view){
		this.view = view;
	}

	/**
	 * @return the moved
	 */
	public boolean isMoved() {
		return moved;
	}

	/**
	 * @param moved the moved to set
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	/**
	 * Returns all the classes of this piece. Override this to return default types.
	 * @return
	 */
	public Set<Class<?>> getType() {
		Set<Class<?>> set = new HashSet<>();
		set.add(this.getClass());
		set.add(Piece.class);
		return set;
	}

	/**
	 * @return the moveLogic
	 */
	public MoveLogicInterface getMoveLogic() {
		return moveLogic;
	}
}
