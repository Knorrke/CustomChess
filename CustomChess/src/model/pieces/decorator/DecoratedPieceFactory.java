package model.pieces.decorator;

import model.pieces.Piece;

public class DecoratedPieceFactory {

	public static Piece newDecoratedPiece(String decorators, Piece wrappedPiece) {
		for(String decorator : decorators.split(" ")) {
			switch(decorator){
				case "Mighty": wrappedPiece = new Mighty(wrappedPiece);
				case "KnightRiding": wrappedPiece = new KnightRiding(wrappedPiece);
				default: break;
			}
		}
		
		return wrappedPiece;
	}
}
