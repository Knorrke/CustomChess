package model.pieces.decorator;

import model.pieces.Piece;

public class DecoratedPieceFactory {

	public static Decorator newDecoratedPiece(String decorator, Piece wrappedPiece) {
		switch(decorator){
		case "Mighty": return new Mighty(wrappedPiece);
		default: return null;
		}
	}
}
