package model.pieces;

import java.util.Collections;

import model.Board;
import player.PlayerColor;

public class Dummy extends Piece{

	public Dummy(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(pos-> Collections.emptyList());
	}

	@Override
	public void initializeView() {
		setView(()->{});
	}
}
