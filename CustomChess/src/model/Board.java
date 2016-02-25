package model;

import model.pieces.Piece;
import player.PlayerColor;

public class Board {

	private final static int X=0,Y=1;

	private Square[][] squares;
	
	public Board(int width, int height){
		squares = new Square[width][height];
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				squares[i][j] = new Square();
			}
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

	public boolean isPieceOfColorOnSquare(PlayerColor color, int[] pos) {
		return isPieceOnSquare(pos) && 
				squares[pos[X]][pos[Y]].getPiece().getColor().equals(color);
	}
	
	public boolean isPieceOnSquare(int[] pos){
		if(pos.length != 2){
			throw new IllegalArgumentException("Wrong position in " + this.getClass());
		}
		return squares[pos[X]][pos[Y]].hasPiece();
	}

	public boolean isAttacked(PlayerColor attackerColor, int[] pos) {
		if(pos.length != 2){
			throw new IllegalArgumentException("Wrong position in " + this.getClass());
		}
		Square sq = squares[pos[X]][pos[Y]];
		Piece piece = sq.getPiece();
		
		try{
			sq.setPiece(PieceFactory.newPiece(this, "model.pieces.Dummy", attackerColor.getOppositColor(), pos));
			for(int i=0; i<squares.length; i++){
				for(int j=0; j<squares.length; j++){
					if(squares[i][j].getPiece().moveCorrect(pos)){
						
						return true;
					}
				}
			}
			
			return false;
		}finally{
			sq.setPiece(piece);
		}
	}

	public void setPieceToNewPosition(Piece piece, int[] newPos) {
		int[] oldPosition = piece.getPosition();
		Square oldSquareOfPiece = squares[oldPosition[X]][oldPosition[Y]];
		
		assert oldSquareOfPiece.getPiece() == piece;
		
		piece.setPosition(newPos);
		squares[newPos[X]][newPos[Y]].setPiece(piece);
		oldSquareOfPiece.setPiece(null);
	}
	
	public Piece getPieceOfSquare(int[] pos){
		return squares[pos[X]][pos[Y]].getPiece();
	}
}
