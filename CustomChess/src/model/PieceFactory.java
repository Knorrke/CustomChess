package model;

import java.util.HashMap;

import model.pieces.Piece;
import player.PlayerColor;

/**
 * Factory to create Pieces
 * @author Benjamin
 */
public class PieceFactory {
	
	private static HashMap<String, Class<? extends Piece>> map = new HashMap<>();
	
	/**
	 * Creates a new Piece of the specified class, with the specified properties
	 * @param board Board holding the piece
	 * @param className Class of piece to create as String
	 * @param color Color of player controlling the piece
	 * @param pos Position of Piece on Board
	 * @return new piece
	 */
	public static Piece newPiece(Board board,String className, PlayerColor color, int[] pos){
		Class<? extends Piece> pieceClass = loadClass(className);
		try{
			Piece piece = pieceClass.newInstance();
			initializePiece(piece, board, color, pos);
			return piece;
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Loads the specified class. 
	 * Uses memoization to speed up the process.
	 * @param className Name of class to load
	 * @return Class
	 */
	private static Class<? extends Piece> loadClass(String className) {
		if(!map.containsKey(className)){
			try{
				map.put(className, Class.forName(className).asSubclass(Piece.class));
			} catch (ClassNotFoundException e) {
				//FileLoader loader = new FileLoader();
				//TODO: Read description of piece from game file and create CustomPiece
				e.printStackTrace();
			} 
		}
		return map.get(className);
	}

	/**
	 * Initializes the piece with the parameters.
	 * @param piece Piece to initialize
	 * @param board
	 * @param color
	 * @param pos
	 */
	private static void initializePiece(Piece piece, Board board, PlayerColor color, int[] pos) {
		piece.setColor(color);
		piece.setBoard(board);
		piece.setPosition(pos);
	}

}
