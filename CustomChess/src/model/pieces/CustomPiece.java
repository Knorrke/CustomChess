package model.pieces;

import controller.MoveLogic;
import player.PlayerColor;
import view.PieceView;

public class CustomPiece extends Piece{

	private final String rule;
	private final String name;
	
	public CustomPiece(String name, String rule,PlayerColor color, int posX, int posY){
		super(color, posX, posY);
		this.name = name;
		this.rule = rule;
	}

	@Override
	public void initializeMoveLogic() {
		moveLogic = new MoveLogic(this,rule);
	}

	@Override
	public void initializeView() {
		view = new PieceView(this,name + getColor() + ".png");
	}
	
	
}
