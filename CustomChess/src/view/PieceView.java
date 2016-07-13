package view;

import model.pieces.Piece;

public class PieceView implements PieceViewInterface {
	
	//private Image pieceImage;
	private Piece piece;
	
	public PieceView(Piece piece, String filename) {
		this.piece = piece;
		//this.pieceImage = Toolkit.getDefaultToolkit().getImage("assets/pieces/"+filename);
	}

	@Override
	public void drawPiece() {
		System.out.println(piece.toString());
		//TODO: view
	}
}
