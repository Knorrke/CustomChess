package model.pieces.decorator;

import model.pieces.Piece;

public class DecoratedPieceFactory {

	public static AbstractDecorator newDecoratedPiece(String decorator, Piece wrappedPiece) {
		switch(decorator){
		case "Mighty": return new Mighty(wrappedPiece);
		case "KnightRiding": return new KnightRiding(wrappedPiece);
		default: return null;
		}
	}
}
