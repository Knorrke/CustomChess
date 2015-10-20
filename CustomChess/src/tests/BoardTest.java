package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Board;
import model.Field;

public class BoardTest {

	@Test
	public void setupTest() {
		Field field = new Field();
		assertEquals("created Fields should be empty", field.getPiece(), null);
		
		Board board = new Board(8,8);
		assertEquals("Should create an empty board with 8x8 Fields",board.getFields().length, 8);
	}

}
