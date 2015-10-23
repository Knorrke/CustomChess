package model.pieces;

import player.PlayerColor;

public interface PieceInterface {
	
	/**
	 * Should initialize the MoveLogic controller
	 */
	public void initializeMoveLogic();
	/**
	 * @return the color
	 */
	public PlayerColor getColor();

	/**
	 * @param color the color to set
	 */
	public void setColor(PlayerColor color);
	
	/**
	 * @return the position as Integer array, where x is element 0 and y is element 1 
	 */
	public int[] getPosition();
	
	/**
	 * Sets the x and y position
	 * @param posX
	 * @param posY
	 */
	public void setPosition(int posX, int posY);
	
	/**
	 * Sets the x and y position
	 * @param pos Array with pos[0] being the x position and pos[1] being the y position
	 */
	public void setPosition(int[] pos);
}
