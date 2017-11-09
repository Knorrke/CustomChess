package gameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gameController.gameConditionsStrategy.EndConditions.GameEndCondition;
import gameController.gameConditionsStrategy.IntegrityConditions.GameIntegrityCondition;
import model.Board;
import model.pieces.Piece;
import moves.Move;
import player.PlayerColor;

public abstract class GameController {

	private ArrayList<PlayerColor> turnOrder = new ArrayList<>();
	protected ArrayList<GameIntegrityCondition> gameIntegrityConditions = new ArrayList<>();
	protected ArrayList<GameEndCondition> gameEndConditions = new ArrayList<>();
	private ArrayList<Move> moves = new ArrayList<>();

	private Board board;
	protected GameState state;

	public GameController(PlayerColor startingPlayer) {
		turnOrder.add(startingPlayer);
		turnOrder.add(startingPlayer.getOppositColor());

		board = setUpBoard();
		state = GameState.RUNNING;
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
		
		if (piece.getColor() != getCurrentPlayer()) return false;
		
		List<Move> possibleMoves = piece.getPossibleMoves(newPos);
		if (possibleMoves.isEmpty()) return false;
		
		//TODO: What if more than one possible moves?
		Move move = possibleMoves.get(0);		
		if (!integrityCheck(move)) return false;
		
		this.moves.add(move);
		move.execute(board);
		nextPlayer();
		board.draw();
		gameEndCheck();
		return true;
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
		if(board.isPieceOfColorOnSquare(getCurrentPlayer(), from)) {
			return move(board.getPieceOfSquare(from), to);
		} else {
			return false;
		}
	}

	protected abstract Board setUpBoard();

	/**
	 * Check additional conditions for the game. This is called when the
	 * specified piece tries to move.
	 * 
	 * @param move The move to be checked
	 * @return
	 */
	public boolean integrityCheck(Move move) {
		return gameIntegrityConditions.stream().map(cond -> cond.isGameIntegrityEnsured(board, move)).reduce(true, Boolean::logicalAnd);
	}

	/**
	 * Add additional conditions for the game.
	 */
	public void addIntegrityConditions(GameIntegrityCondition... conditions) {
		Collections.addAll(gameIntegrityConditions, conditions);
	}

	/**
	 * Checks if the game has ended
	 * @return true if game ended, false otherwise
	 */
	public boolean gameEndCheck() {
		for(GameEndCondition cond : gameEndConditions) {
			GameState newState = cond.isEndConditionMet(this);
			switch (newState) {
			case BLACKWIN:
			case WHITEWIN:
			case DRAW:
				setState(newState);
				return true;
			default:
				break;
			}
		}
		return false;
	}
	
	protected void setState(GameState newState) {
		state = newState;
		endHook();
	}

	protected void endHook() {};

	/**
	 * Add end conditions for the game
	 * @param conditions
	 */
	public void addEndConditions(GameEndCondition... conditions) {
		Collections.addAll(gameEndConditions, conditions);
	}
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Get a board in the state of the specified move
	 * 
	 *  @param n move number
	 *  @return a new Board instance at the specified number of moves
	 */
	public Board getBoardAtMove(int n) {
		if (n >= moves.size()) {
			return null;
		}
		
		Board saved = board; 
		try{
			board = setUpBoard();
			for(int i=0;i<=n;i++) {
				Move move = moves.get(i);
				move(move.getFrom(),move.getTo());
			}
			return board;
		} catch (Exception e) {
			throw e;
		} finally {
			board = saved;
		}
	}
	
	public ArrayList<Move> getMoves() {
		return moves;
	}

	public boolean moveAllowed(Piece piece, int[] newPos) {
		List<Move> moves = piece.getPossibleMoves(newPos);
		if (moves.isEmpty()) return false;
		
		//TODO: What if more than one possible moves?
		Move move = moves.get(0);		
		return integrityCheck(move);
	}
}
