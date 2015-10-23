package model;

import model.pieces.PieceInterface;
import player.PlayerColor;

public class PieceFactory {
	
	public static PieceInterface newPiece(String className, PlayerColor color){
		try {
			PieceInterface piece = Class.forName(className).asSubclass(PieceInterface.class).newInstance();
			piece.setColor(color);
			piece.initializeMoveLogic();
			
			return piece;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
