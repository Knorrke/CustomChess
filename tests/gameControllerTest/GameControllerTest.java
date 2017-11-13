package gameControllerTest;


import static helper.Helper.pos;
import static org.junit.Assert.*;

import org.junit.Test;

import base.NoConsoleTest;
import gameController.GameController;
import gameController.StandardGameController;
import model.Board;
import model.pieces.Pawn;
import model.pieces.Piece;
import player.PlayerColor;

public class GameControllerTest extends NoConsoleTest {

	@Test
	public void standardGameTest() {
		GameController gameController = new StandardGameController();
	    assertEquals("Should be whites turn", PlayerColor.WHITE, gameController.getCurrentPlayer());
	    
	    Piece pawn = gameController.getBoard().getPieceOfSquare(new int[] {4,1});
	    assertNotNull("Piece on e2 should be not null" , pawn);
	    assertTrue("Piece should be a pawn", pawn instanceof Pawn);
	    
	    assertTrue("Pawn should be able to move e2-e4", gameController.move(pawn , new int[] {4,3}));
	    assertEquals("Should be blacks turn after white move", PlayerColor.BLACK, gameController.getCurrentPlayer());
	}
	
	@Test
	public void standardGameIntegrityTest() {
		GameController gameController = new StandardGameController();
		assertTrue(gameController.move(new int[] {4,1}, new int[] {4,3}));
		assertTrue(gameController.move(new int[] {4,6}, new int[] {4,4}));
		assertTrue(gameController.move(new int[] {5,1}, new int[] {5,3}));
		assertTrue(gameController.move(new int[] {3,7}, new int[] {7,3}));
		//check
		assertFalse("White can't move A-pawn in check", gameController.move(new int[] {0,1}, new int[] {0,3}));
		assertTrue("White can intermit the check", gameController.move(new int[] {6,1}, new int[] {6,2}));		
	}
	
	@Test
	public void moveResetTest() {
		GameController gameController = new StandardGameController();
		gameController.move(pos("e2"),pos("e4"));
		moveResetTestCheckFirstMove(gameController.getBoard());
		gameController.move(pos("e7"),pos("e5"));
		moveResetTestCheckSecondMove(gameController.getBoard());
		gameController.move(pos("f2"),pos("f4"));
		moveResetTestCheckThirdMove(gameController.getBoard());
		
		assertEquals("Should have three moves in list",3, gameController.getMoves().size());
		
		moveResetTestCheckFirstMove(gameController.getBoardAtMove(0));
		moveResetTestCheckSecondMove(gameController.getBoardAtMove(1));
		moveResetTestCheckThirdMove(gameController.getBoard());
		
		assertEquals("Should have three moves in list",3, gameController.getMoves().size());
		assertEquals("Should be blacks turn", PlayerColor.BLACK, gameController.getCurrentPlayer());
		gameController.revertLastMove();
		moveResetTestCheckSecondMove(gameController.getBoard());

		assertEquals("Should have two moves in list",2, gameController.getMoves().size());
		assertEquals("Should be whites turn", PlayerColor.WHITE, gameController.getCurrentPlayer());
	}
	
	
	private void moveResetTestCheckFirstMove(Board board) {
		assertNull(board.getPieceOfSquare(pos("e2")));
		assertNotNull(board.getPieceOfSquare(pos("e4")));

		assertNotNull(board.getPieceOfSquare(pos("e7")));
		assertNull(board.getPieceOfSquare(pos("e5")));
		
		assertNotNull(board.getPieceOfSquare(pos("f2")));
		assertNull(board.getPieceOfSquare(pos("f4")));
	}
	
	private void moveResetTestCheckSecondMove(Board board) {
		assertNull(board.getPieceOfSquare(pos("e2")));
		assertNotNull(board.getPieceOfSquare(pos("e4")));

		assertNull(board.getPieceOfSquare(pos("e7")));
		assertNotNull(board.getPieceOfSquare(pos("e5")));
		
		assertNotNull(board.getPieceOfSquare(pos("f2")));
		assertNull(board.getPieceOfSquare(pos("f4")));
	}
	
	private void moveResetTestCheckThirdMove(Board board) {
		assertNull(board.getPieceOfSquare(pos("e2")));
		assertNotNull(board.getPieceOfSquare(pos("e4")));

		assertNull(board.getPieceOfSquare(pos("e7")));
		assertNotNull(board.getPieceOfSquare(pos("e5")));
		
		assertNull(board.getPieceOfSquare(pos("f2")));
		assertNotNull(board.getPieceOfSquare(pos("f4")));
	}

}
