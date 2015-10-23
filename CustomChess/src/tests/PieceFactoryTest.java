package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PieceFactory;
import model.pieces.Pawn;
import model.pieces.PieceInterface;
import player.PlayerColor;

public class PieceFactoryTest {

	@Test
	public void test() {
		PlayerColor testcolor = PlayerColor.BLACK;
		PieceInterface piece = PieceFactory.newPiece("model.pieces.Pawn", testcolor);
		assertTrue("Should be an implementation of PieceInterface", piece instanceof PieceInterface);
		assertTrue("Should be a pawn", piece instanceof Pawn);
		assertEquals("Should be of color "+testcolor, piece.getColor(), testcolor);
	}

}
