package gameController;

import java.util.ArrayList;
import java.util.Collections;

import gameController.gameConditionsStrategy.GameCondition;
import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public abstract class GameController {

	private ArrayList<PlayerColor> turnOrder = new ArrayList<>();
	protected ArrayList<GameCondition> gameIntegrityConditions = new ArrayList<>();

	private final Board board;

	public GameController(PlayerColor startingPlayer) {
		turnOrder.add(startingPlayer);
		turnOrder.add(startingPlayer.getOppositColor());

		board = setUpBoard();
	}

	public PlayerColor getCurrentPlayer() {
		return turnOrder.get(0);
	}

	private PlayerColor nextPlayer() {
		turnOrder.add(turnOrder.remove(0));
		return getCurrentPlayer();
	}

	/**
	 * Tries moving the specified piece to the specified position. Returns true
	 * if successfull, false otherwise.
	 * 
	 * @param piece
	 *            Piece to move
	 * @param newPos
	 *            Position to move the piece to
	 * @return
	 */
	public boolean move(Piece piece, int[] newPos) {
		assert piece.getBoard() == board;
		if (piece.getColor() == getCurrentPlayer() && piece.moveCorrect(newPos) && integrityCheck(piece, newPos)) {
			board.setPieceToNewPosition(piece, newPos);
			nextPlayer();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Tries moving the piece on specified position. Returns true if
	 * successfull, false otherwise
	 * 
	 * @param from
	 *            Position of piece
	 * @param to
	 *            Position to move piece to
	 */
	public boolean move(int[] from, int[] to) {
		return move(board.getPieceOfSquare(from), to);
	}

	protected abstract Board setUpBoard();

	/**
	 * Check additional conditions for the game. This is called when the
	 * specified piece tries to move.
	 * 
	 * @param piece
	 *            The piece being moved
	 * @param newPos
	 *            The new position
	 * @return
	 */
	public boolean integrityCheck(Piece piece, int[] newPos) {
		return gameIntegrityConditions.stream().map(cond -> cond.isGameIntegrityEnsured(board, piece, newPos)).reduce(true, Boolean::logicalAnd);
	}

	/**
	 * Add additional conditions for the game.
	 */
	public void addIntegrityConditions(GameCondition... conditions) {
		Collections.addAll(gameIntegrityConditions, conditions);
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
}
