package moves.additionalActions;

import moves.Move;

public class AdditionalActionFactory {

	public static Move getDecoratedMove(String actionsPart, Move decorated) {
		Move result = decorated;
		for(char action : actionsPart.toCharArray()) {
			result = getAction(action, result);
		}
		return result;
	}

	private static Move getAction(char action, Move decorated) {
		switch (action) {
		case 'R':
			return new CastleAction(decorated);
		case 'E':
			return new EnPassantAction(decorated);
		case 'P':
			return new PromotionAction(decorated);
		default:
			return decorated;
		}
	}

}
