package model.pieces;

import model.Board;
import player.PlayerColor;

public class Dummy extends Piece{

	public Dummy(PlayerColor color, Board board, int[] pos) {
		super(color, board, pos);
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(pos-> false);
	}

	@Override
	public void initializeView() {
		setView(()->{});
	}
}
