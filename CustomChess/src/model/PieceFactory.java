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
import model.pieces.decorator.DecoratedPieceFactory;
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
	 * @param name
	 *            Name of piece to create as String
	 * @param color
	 *            Color of player controlling the piece
	 * @param pos
	 *            Position of Piece on Board
	 * @return new piece
	 */
	public static Piece newPiece(Board board, String className, PlayerColor color, int[] pos) {
		if (className.contains(" ")) {
			String[] decorators = className.split(" ");

			Piece piece = getPiece(board, decorators[decorators.length - 1], color, pos);

			for (int i = decorators.length - 2; i >= 0; i--) {
				piece = DecoratedPieceFactory.newDecoratedPiece(decorators[i], piece);
			}

			return piece;
		} else {
			return getPiece(board, className, color, pos);
		}
	}

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
	private static Piece getPiece(Board board, String className, PlayerColor color, int[] pos) {
		switch (className) {
		case "Bishop":
			return new Bishop(color, board, pos);
		case "Dummy":
			return new Dummy(color, board, pos);
		case "King":
			return new King(color, board, pos);
		case "Knight":
			return new Knight(color, board, pos);
		case "Pawn":
			return new Pawn(color, board, pos);
		case "Queen":
			return new Queen(color, board, pos);
		case "Rook":
			return new Rook(color, board, pos);
		default:
			Class<? extends Piece> pieceClass = loadClass(className);
			try {
				return pieceClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return new Dummy(color, board, pos);
			}
		}
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
