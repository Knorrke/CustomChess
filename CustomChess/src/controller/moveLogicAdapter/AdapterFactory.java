package controller.moveLogicAdapter;

public class AdapterFactory {

	public static MoveLogicAdapterInterface getAdapter(char condition) {
		switch(condition){
			case 'C': return new OnlyCaptureAdapter();
			case 'M': return new OnlyMoveAdapter();
			case '!': return new OnlyNotAttackedAdapter();
			default : return null;
		}
	}
}
