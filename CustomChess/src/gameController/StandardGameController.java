package gameController;


import static helper.Helper.*;
import gameController.gameConditionsStrategy.EndConditions.MateConditions;
import gameController.gameConditionsStrategy.IntegrityConditions.MightyNotAttacked;
import model.Board;
import model.PieceFactory;
import player.PlayerColor;

public class StandardGameController extends GameController {

	public StandardGameController() {
		super(PlayerColor.WHITE);
		super.addIntegrityConditions(new MightyNotAttacked());
		super.addEndConditions(new MateConditions());
	}

	@Override
	public Board setUpBoard() {
		Board board = new Board(this,8,8);
		
		for(int i=0; i<8; i++){
			board.addPiece(PieceFactory.newPiece(board,"Pawn",PlayerColor.WHITE,new int[] {i,1}));
			board.addPiece(PieceFactory.newPiece(board, "Pawn", PlayerColor.BLACK, new int[] {i,6}));
		}

		board.addPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.WHITE,new int[] {0,0}));
		board.addPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.WHITE,new int[] {7,0}));
		board.addPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.BLACK,new int[] {0,7}));
		board.addPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.BLACK,new int[] {7,7}));

		board.addPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.WHITE, new int[] {1,0}));
		board.addPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.WHITE, new int[] {6,0}));
		board.addPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.BLACK, new int[] {1,7}));
		board.addPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.BLACK, new int[] {6,7}));

		board.addPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.WHITE, new int[] {2,0}));
		board.addPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.WHITE, new int[] {5,0}));
		board.addPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.BLACK, new int[] {2,7}));
		board.addPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.BLACK, new int[] {5,7}));
		
		board.addPiece(PieceFactory.newPiece(board, "Queen", PlayerColor.WHITE, new int[] {3,0}));
		board.addPiece(PieceFactory.newPiece(board, "Queen", PlayerColor.BLACK, new int[] {3,7}));
		
		board.addPiece(PieceFactory.newPiece(board, "Mighty King", PlayerColor.WHITE, new int[] {4,0}));
		board.addPiece(PieceFactory.newPiece(board, "Mighty King", PlayerColor.BLACK, new int[] {4,7}));
		
		return board;
	}
	
	@Override
	protected void endHook() {
		console.print("Game Over. ");
		switch(state) {
		case WHITEWIN:
			console.println("White wins!");
			break;
		case BLACKWIN:
			console.println("Black wins!");
			break;
		case DRAW:
			console.println("Draw!");
			break;
		case RUNNING:
			console.println("Wait... no it is not... my bad!");
			break;
		default:
			break;
		}
	}
}
