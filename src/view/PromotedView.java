package view;

import static helper.Helper.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Board;
import model.PieceFactory;
import model.pieces.Dummy;
import model.pieces.Piece;

public class PromotedView {

	public Piece getPromotionPiece(Board board, Piece piece, int[] newPos) {
		Piece promoted = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			boolean choose = true;
			while(choose) {
				console.println("What do you want to promote to?");
				String classname =  reader.readLine();						
				promoted = PieceFactory.newPiece(board, classname, piece.getColor(), newPos);
				choose = promoted == null || promoted.getType().contains(Dummy.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return promoted;
	}
}
