package model;

import model.pieces.Piece;
import player.PlayerColor;

public class Board {

	private Square[][] squares;
	private final static int X=0,Y=1;
	
	public Board(int width, int height){
		squares = new Square[width][height];
	}

	public Square[][] getFields() {
		return squares;
	}

	public boolean isPieceOnSquare(PlayerColor color, int[] newPos) {
		if(newPos.length != 2){
			throw new IllegalArgumentException("Wrong position in " + this.getClass());
		}
		
		Piece piece = squares[newPos[X]][newPos[Y]].getPiece();
		return piece != null && piece.getColor().equals(color);
	}

	public boolean isAttacked(PlayerColor attackerColor, int[] newPos) {
		if(newPos.length != 2){
			throw new IllegalArgumentException("Wrong position in " + this.getClass());
		}
		Square sq = squares[newPos[X]][newPos[Y]];
		Piece piece = sq.getPiece();
		
		try{
			sq.setPiece(PieceFactory.newPiece(this, "model.pieces.Dummy", attackerColor.getOppositColor(), newPos));
			for(int i=0; i<squares.length; i++){
				for(int j=0; j<squares.length; j++){
					if(squares[i][j].getPiece().moveCorrect(newPos)){
						
						return true;
					}
				}
			}
			
			return false;
		}finally{
			sq.setPiece(piece);
		}
	}
}
