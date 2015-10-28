package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import model.pieces.Piece;

public class PieceView implements PieceViewInterface {
	
	private Image pieceImage;
	private Piece piece;
	
	public PieceView(Piece piece, String filename) {
		this.piece = piece;
		this.pieceImage = Toolkit.getDefaultToolkit().getImage("assets/pieces/"+filename);
	}

	@Override
	public void drawPiece(Graphics g) {
		g.drawImage(pieceImage,piece.getPosition()[0],piece.getPosition()[1],null);
	}
}
