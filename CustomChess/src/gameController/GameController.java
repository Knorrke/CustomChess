package gameController;

import java.util.ArrayList;

import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public abstract class GameController {
	
	private ArrayList<PlayerColor> turnOrder = new ArrayList<>();
	private final Board board;
	
	public GameController(PlayerColor startingPlayer){
		turnOrder.add(startingPlayer);
		turnOrder.add(startingPlayer.getOppositColor());
		
		board = setUpBoard();
	}
	
	public PlayerColor getCurrentPlayer() {
		return turnOrder.get(0);
	}
	
	private PlayerColor nextPlayer(){
		turnOrder.add(turnOrder.remove(0));
		return getCurrentPlayer();
	}
	
	public boolean move(Piece piece, int[] newPos){
		assert piece.getBoard() == board;
		if(piece.getColor() == getCurrentPlayer() && piece.moveCorrect(newPos)) {
			board.setPieceToNewPosition(piece,newPos);
			nextPlayer();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract Board setUpBoard();

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
}
