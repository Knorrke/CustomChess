package model.pieces;

import controller.MoveLogicInterface;
import model.Board;
import model.pieces.interfaces.MoveLogicInitializerInterface;
import model.pieces.interfaces.ViewInitializerInterface;
import player.PlayerColor;
import view.PieceViewInterface;

public abstract class Piece implements MoveLogicInitializerInterface, ViewInitializerInterface{
	
	protected PlayerColor color;
	protected int posX, posY;
	protected MoveLogicInterface moveLogic;
	protected PieceViewInterface view;
	protected Board board;
	
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
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean moveCorrect(int[] newPos) {
		return moveLogic.moveCorrect(newPos);
	}
}
