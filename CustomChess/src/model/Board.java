package model;

import static helper.Helper.X;
import static helper.Helper.Y;
import static helper.Helper.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameController.GameController;
import model.pieces.Piece;
import moveLogic.additionalActions.AdditionalAction;
import player.PlayerColor;
import view.BoardView;
import view.ViewInterface;

public class Board implements Drawable {
	private Square[][] squares;
	private ViewInterface view;
	private Map<int[], List<AdditionalAction>> actionsThisMove = new HashMap<>();
	private GameController game;

	public Board(GameController game, int width, int height) {
		this(width, height);
		this.game = game;
	}
	
	public Board(int width, int height) {
		squares = new Square[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				squares[i][j] = new Square((i + j) % 2 == 0 ? SquareColor.BLACK : SquareColor.WHITE);
			}
		}
		view = new BoardView(this);
	}

	public Square[][] getSquares() {
		return squares;
	}

	public boolean isPieceOfColorOnSquare(PlayerColor color, int[] pos) {
		return isPieceOnSquare(pos) && squares[pos[X]][pos[Y]].getPiece().getColor().equals(color);
	}

	public boolean isPieceOnSquare(int[] pos) {
		if (pos.length != 2) {
			throw new IllegalArgumentException("Wrong position in " + this.getClass());
		}
		return squares[pos[X]][pos[Y]].hasPiece();
	}

	public boolean isAttacked(PlayerColor attackerColor, int[] pos) {
		if (pos.length != 2) {
			throw new IllegalArgumentException("Wrong position in " + this.getClass());
		}
		Square sq = squares[pos[X]][pos[Y]];
		Piece piece = sq.getPiece();
		try {
			sq.setPiece(PieceFactory.newPiece(this, "Dummy", attackerColor.getOppositColor(), pos));
			for (int i = 0; i < squares.length; i++) {
				for (int j = 0; j < squares[i].length; j++) {
					if (isPieceOfColorOnSquare(attackerColor, pos(i,j)) && squares[i][j].getPiece().moveCorrect(pos)) {
						return true;
					}
				}
			}

			return false;
		} finally {
			sq.setPiece(piece);
		}
	}

	public void setPieceToNewPosition(Piece piece, int[] newPos) {
		int[] oldPosition = piece.getPosition();
		Square oldSquareOfPiece = squares[oldPosition[X]][oldPosition[Y]];

		if(oldSquareOfPiece.getPiece() != piece) 
			throw new IllegalArgumentException("The piece " + piece.toString() + " is not on the correct position");
		
		piece.setPosition(newPos);
		squares[newPos[X]][newPos[Y]].setPiece(piece);
		if (!oldPosition.equals(newPos)) {
			oldSquareOfPiece.setPiece(null);
		}
	}

	public void executeMove(GameController gameController, Piece piece, int[] newPos) {
		setPieceToNewPosition(piece, newPos);
		piece.setMoved(true);
		if(actionsThisMove.containsKey(newPos)) {
			for(AdditionalAction action : actionsThisMove.get(newPos)) {
				action.execute(gameController, piece, newPos);
			}
		}
		actionsThisMove = new HashMap<>();
	}
	
	public Piece getPieceOfSquare(int[] pos) {
		return squares[pos[X]][pos[Y]].getPiece();
	}

	/**
	 * Adds the piece its position if this is possible Throws
	 * IllegalArgumentException if square is already taken.
	 * 
	 * @param piece
	 */
	public void addPiece(Piece piece) {
		int[] pos = piece.getPosition();
		if (squares[pos[X]][pos[Y]].hasPiece()) {
			throw new IllegalArgumentException("There is already a piece on this square");
		}

		squares[pos[X]][pos[Y]].setPiece(piece);
	}

	public void removePiece(Piece piece) {
		int[] pos = piece.getPosition();
		if (squares[pos[X]][pos[Y]].getPiece() != piece ) {
			throw new IllegalArgumentException("This piece is not on the board or on a different position");
		}

		squares[pos[X]][pos[Y]].setPiece(null);
	}
	
	@Override
	public void draw() {
		view.draw();
	}

	public int size(int direction) {
		switch (direction) {
		case X:
			return squares.length;
		case Y:
			return squares.length > 0 ? squares[0].length : 0;
		default:
			return 0;
		}
	}

	public void addPieces(Piece... pieces) {
		for(Piece piece : pieces) {
			addPiece(piece);
		}
	}

	public void registerAction(int[] newPos, List<AdditionalAction> additionalActions) {
		actionsThisMove.put(newPos, additionalActions);
	}
	
	public GameController getGameController() {
		return game;
	}

	public ArrayList<Piece> getAllPieces() {
		ArrayList<Piece> pieces = new ArrayList<>();
		for (Square[] row : getSquares()) {
			for(Square sq : row) {
				Piece piece = sq.getPiece();
				if(piece != null) {
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}
}
