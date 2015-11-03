package model;

import model.pieces.Piece;
import player.PlayerColor;

public class PieceFactory {
	
	public static Piece newPiece(Board board,String className, PlayerColor color, int[] pos){
		try {
			Piece piece = Class.forName(className).asSubclass(Piece.class).newInstance();
			initializePiece(piece, board, color, pos);
			return piece;
		} catch (ClassNotFoundException e) {
			//FileLoader loader = new FileLoader();
			//TODO: Read description of piece from game file and create CustomPiece
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
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

	private static void initializePiece(Piece piece, Board board, PlayerColor color, int[] pos) {
		piece.setColor(color);
		piece.setBoard(board);
		piece.setPosition(pos);
		piece.initializeMoveLogic();
		piece.initializeView();
	}

}
