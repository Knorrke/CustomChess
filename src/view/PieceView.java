package view;

import static helper.Helper.console;

import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

import model.pieces.Piece;
import player.PlayerColor;

public class PieceView implements ViewInterface {
	
	//private Image pieceImage;
	private Piece piece;
	
	public PieceView(Piece piece, String filename) {
		this.piece = piece;
		//this.pieceImage = Toolkit.getDefaultToolkit().getImage("assets/pieces/"+filename);
	}

	@Override
	public void draw() {
		String name = "";
		console.setAttribute(Attribute.BOLD);
		console.setForegroundColor(piece.getColor() == PlayerColor.WHITE ? FColor.BLUE : FColor.RED);
		switch(piece.getClass().getSimpleName()) {
		case "Rook" : name = "R"; break;
		case "King" : name = "K"; break;
		case "Knight" : name = "N"; break;
		case "Queen" : name = "Q"; break;
		case "Pawn" : name = "P"; break;
		case "Bishop": name = "B"; break;
		default: name = "?"; break;
		}
		console.print(name);
		console.setAttribute(Attribute.CLEAR);
	}
}
