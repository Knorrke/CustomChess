package controller.moveLogicStrategy;

public class BehaviourFactory {

	public static MoveLogicBehaviour getBehaviour(char condition) {
		switch(condition){
			case 'C': return new OnlyToCapture();
			case 'M': return new OnlyMoveAdapter();
			case '!': return new OnlyToNotAttackedSquare();
			case '#': return new OnlyIfAttacked();
			case '*': return new OnlyIfNotAttacked();
			case 'F': return new OnlyIfFreeWay();
			case 'J': return new OnlyIfJumpOverPiece();
			default : return null;
		}
	}
}
