package controller.specialMoveConditionStrategy;

public class SpecialMoveConditionFactory {

	public static SpecialMoveCondition getBehaviour(char condition) {
		switch(condition){
			case 'C': return new OnlyCapture();
			case 'M': return new OnlyMove();
			case '!': return new OnlyToNotAttackedSquare();
			case '#': return new OnlyIfAttacked();
			case '*': return new OnlyIfNotAttacked();
			case 'F': return new OnlyIfFreeWay();
			case 'J': return new OnlyIfJumpOverPiece();
			case 'N': return new OnlyIfNeverMoved();
			default : return null;
		}
	}
}
