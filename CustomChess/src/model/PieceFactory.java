package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import file.FileLoader;
import model.pieces.Piece;
import player.PlayerColor;

public class PieceFactory {
	
	public static Piece newPiece(String className, PlayerColor color){
		try {
			Class<? extends Piece> pieceClass = Class.forName(className).asSubclass(Piece.class);
			Constructor<? extends Piece> constructor = pieceClass.getConstructor(PlayerColor.class, int.class, int.class);
			Piece piece = constructor.newInstance(color, 0,0);
			piece.setColor(color);
			piece.initializeMoveLogic();
			
			return piece;
		} catch (ClassNotFoundException e) {
			FileLoader loader = new FileLoader();
			//TODO: Read description of piece from game file and create CustomPiece
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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

}
