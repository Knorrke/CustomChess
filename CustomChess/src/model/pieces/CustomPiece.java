package model.pieces;

import model.Board;
import moveLogic.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class CustomPiece extends Piece{

	private final String rule;
	private final String name;
	
	public CustomPiece(String name, String rule, Board board, PlayerColor color, int[] pos){
		super(color, board, pos);
		this.name = name;
		this.rule = rule;
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(new MoveLogic(getBoard(), this,rule));
	}

	@Override
	public void initializeView() {
		setView(new PieceView(this,name + getColor() + ".png"));
	}
	
	
}
