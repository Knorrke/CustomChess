package gameController;

import model.Board;
import model.PieceFactory;
import model.Square;
import model.pieces.decorator.DecoratedPieceFactory;
import player.PlayerColor;

public class StandardGameController extends GameController {

	public StandardGameController() {
		super(PlayerColor.WHITE);
	}

	@Override
	public Board setUpBoard() {
		Board board = new Board(8,8);
		Square[][] squares = board.getSquares();
		
		for(int i=0; i<8; i++){
			squares[i][1].setPiece(PieceFactory.newPiece(board,"Pawn",PlayerColor.WHITE,new int[] {i,1}));
			squares[i][6].setPiece(PieceFactory.newPiece(board, "Pawn", PlayerColor.BLACK, new int[] {i,6}));
		}

		squares[0][0].setPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.WHITE,new int[] {0,0}));
		squares[7][0].setPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.WHITE,new int[] {7,0}));
		squares[0][7].setPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.BLACK,new int[] {0,7}));
		squares[7][7].setPiece(PieceFactory.newPiece(board, "Rook", PlayerColor.BLACK,new int[] {7,7}));

		squares[1][0].setPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.WHITE, new int[] {1,0}));
		squares[6][0].setPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.WHITE, new int[] {6,0}));
		squares[1][7].setPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.BLACK, new int[] {1,7}));
		squares[6][7].setPiece(PieceFactory.newPiece(board, "Knight", PlayerColor.BLACK, new int[] {6,7}));

		squares[2][0].setPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.WHITE, new int[] {2,0}));
		squares[5][0].setPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.WHITE, new int[] {5,0}));
		squares[2][7].setPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.BLACK, new int[] {2,7}));
		squares[5][7].setPiece(PieceFactory.newPiece(board, "Bishop", PlayerColor.BLACK, new int[] {5,7}));
		
		squares[3][0].setPiece(PieceFactory.newPiece(board, "Queen", PlayerColor.WHITE, new int[] {3,0}));
		squares[3][7].setPiece(PieceFactory.newPiece(board, "Queen", PlayerColor.BLACK, new int[] {3,7}));
		
		squares[4][0].setPiece(DecoratedPieceFactory.newDecoratedPiece("Mighty",
				PieceFactory.newPiece(board, "King", PlayerColor.WHITE, new int[] {4,0})));
		squares[4][7].setPiece(DecoratedPieceFactory.newDecoratedPiece("Mighty",
				PieceFactory.newPiece(board, "King", PlayerColor.BLACK, new int[] {4,7})));
		
		return board;
	}
}
