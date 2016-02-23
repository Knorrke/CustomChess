package model;

import java.util.HashMap;

import model.pieces.Bishop;
import model.pieces.Dummy;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;
import player.PlayerColor;

/**
 * Factory to create Pieces
 * 
 * @author Benjamin
 */
public class PieceFactory {

	private static HashMap<String, Class<? extends Piece>> map = new HashMap<>();

	/**
	 * Creates a new Piece of the specified class, with the specified properties
	 * 
	 * @param board
	 *            Board holding the piece
	 * @param className
	 *            Class of piece to create as String
	 * @param color
	 *            Color of player controlling the piece
	 * @param pos
	 *            Position of Piece on Board
	 * @return new piece
	 */
	public static Piece newPiece(Board board, String className, PlayerColor color, int[] pos) {
		Piece piece = null;
		switch (className) {
		case "Bishop":
			piece = new Bishop(color, board, pos);
			break;
		case "Dummy":
			piece = new Dummy(color, board, pos);
			break;
		case "King":
			piece = new King(color, board, pos);
			break;
		case "Knight":
			piece = new Knight(color, board, pos);
			break;
		case "Pawn":
			piece = new Pawn(color, board, pos);
			break;
		case "Queen":
			piece = new Queen(color, board, pos);
			break;
		case "Rook":
			piece = new Rook(color, board, pos);
			break;
		default:
			Class<? extends Piece> pieceClass = loadClass(className);
			try {
				piece = pieceClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			break;
		}
		return piece;
	}

	/**
	 * Loads the specified class. Uses memoization to speed up the process.
	 * 
	 * @param className
	 *            Name of class to load
	 * @return Class
	 */
	private static Class<? extends Piece> loadClass(String className) {
		if (!map.containsKey(className)) {
			try {
				map.put(className, Class.forName(className).asSubclass(Piece.class));
			} catch (ClassNotFoundException e) {
				// FileLoader loader = new FileLoader();
				// TODO: Read description of piece from game file and create
				// CustomPiece
				e.printStackTrace();
			}
		}
		return map.get(className);
	}
}
