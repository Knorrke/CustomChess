package view;

import model.pieces.Piece;

public class PieceView implements ViewInterface {
	
	//private Image pieceImage;
	private Piece piece;
	
	public PieceView(Piece piece, String filename) {
		this.piece = piece;
		//this.pieceImage = Toolkit.getDefaultToolkit().getImage("assets/pieces/"+filename);
	}

	@Override
	public void draw() {
		System.out.print(piece.getClass().getSimpleName().charAt(0));
		//TODO: view
	}
}
