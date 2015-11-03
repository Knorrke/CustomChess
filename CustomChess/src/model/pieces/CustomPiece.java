package model.pieces;

import controller.MoveLogic;
import view.PieceView;

public class CustomPiece extends Piece{

	private final String rule;
	private final String name;
	
	public CustomPiece(String name, String rule){
		super();
		this.name = name;
		this.rule = rule;
	}

	@Override
	public void initializeMoveLogic() {
		MoveLogic ml = new MoveLogic(board, this,rule);
		ml.addAdaptersAutomatically();
		moveLogic = ml;
	}

	@Override
	public void initializeView() {
		view = new PieceView(this,name + getColor() + ".png");
	}
	
	
}
