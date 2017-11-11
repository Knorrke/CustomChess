package gameControllerTest;

import static helper.Helper.pos;
import static org.junit.Assert.*;

import org.junit.Test;

import gameController.Chess960GameController;
import model.Board;
import model.PieceFactory;
import model.pieces.King;
import model.pieces.Piece;
import model.pieces.Rook;
import player.PlayerColor;

public class Chess960Test {

	private String[] testPieces;
	
	@Test
	public void castlingWithPiecesOnCorrectSquares() {
		testPieces = new String[] {"Knight", "Rook", "Mighty King", "Queen", "Bishop", "Rook", "Knight", "Bishop"};
		Chess960GameController gc = new Chess960TestController();
		
		Piece rook = gc.getBoard().getPieceOfSquare(pos("f1"));
		assertTrue(rook.getType().contains(Rook.class));
		Piece king = gc.getBoard().getPieceOfSquare(pos("c1"));
		assertTrue(king.getType().contains(King.class));
		
		assertTrue(gc.move(pos("d2"), pos("d4")));
		assertTrue(gc.move(pos("a7"), pos("a6")));
		assertTrue(gc.move(pos("d1"), pos("d3")));
		assertTrue(gc.move(pos("d7"), pos("d5")));
		
		assertTrue("Should be able to castle short with king already on c1", gc.moveAllowed(king, pos("c1")));
		
		assertTrue(gc.move(pos("e1"), pos("c3")));
		assertTrue(gc.move(pos("b7"), pos("b6")));
		assertTrue(gc.move(pos("g1"), pos("f3")));
		assertTrue(gc.move(pos("c7"), pos("c6")));
		
		assertTrue("Should be able to castle short with rook already on f1", gc.moveAllowed(king, pos("g1")));
		assertTrue(gc.move(pos("c1"), pos("g1")));
		assertEquals("Rook should be on f1", rook, gc.getBoard().getPieceOfSquare(pos("f1")));
		assertEquals("King should be on g1", king, gc.getBoard().getPieceOfSquare(pos("g1")));
		assertNull("c1 should be empty", gc.getBoard().getPieceOfSquare(pos("c1")));
		assertTrue("Rook should be marked as moved", rook.isMoved());
		assertTrue("King should be marked as moved", king.isMoved());
	}
	
	@Test
	public void castlingWithSwappingRookAndKing() {
		testPieces = new String[] {"Knight", "Queen", "Rook", "Mighty King", "Bishop", "Rook", "Knight", "Bishop"};
		Chess960GameController gc = new Chess960TestController();
		gc.getBoard().draw();
		
		Piece rook = gc.getBoard().getPieceOfSquare(pos("c1"));
		assertTrue(rook.getType().contains(Rook.class));
		Piece king = gc.getBoard().getPieceOfSquare(pos("d1"));
		assertTrue(king.getType().contains(King.class));
		
		assertTrue("Should be able to castle with rook on c1", gc.moveAllowed(king, pos("c1")));
		
		assertTrue(gc.move(pos("d1"), pos("c1")));
		assertEquals("Rook should be on d1", rook, gc.getBoard().getPieceOfSquare(pos("d1")));
		assertEquals("King should be on c1", king, gc.getBoard().getPieceOfSquare(pos("c1")));
		assertTrue("Rook should be marked as moved", rook.isMoved());
		assertTrue("King should be marked as moved", king.isMoved());
	}
	
	private class Chess960TestController extends Chess960GameController {
		@Override
		protected Board setUpBoard() {
			Board board = new Board(8,8);
			for (int i=0; i<8; i++) {
				
				board.addPiece(PieceFactory.newPiece(board, testPieces[i], PlayerColor.WHITE, pos(i, 0)));
				board.addPiece(PieceFactory.newPiece(board, testPieces[i], PlayerColor.BLACK, pos(i, 7)));
				board.addPiece(PieceFactory.newPiece(board,"Pawn", PlayerColor.WHITE, pos(i,1)));
				board.addPiece(PieceFactory.newPiece(board, "Pawn", PlayerColor.BLACK, pos(i,6)));
			}

			return board;
		}

		
	}

}
