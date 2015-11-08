package controller.moveLogicAdapter;

public class AdapterFactory {

	public static MoveLogicAdapterInterface getAdapter(char condition) {
		switch(condition){
			case 'C': return new OnlyCaptureAdapter();
			case 'M': return new OnlyMoveAdapter();
			case '!': return new NotAttackedSquareAdapter();
			case '#': return new OnlyAttackedAdapter();
			case '*': return new NotAttackedAdapter();
			case 'F': return new OnlyFreeWayAdapter();
			case 'J': return new OnlyJumpOverPieceAdapter();
			default : return null;
		}
	}
}
