package moveLogic.additionalActions;

import java.util.ArrayList;
import java.util.List;

public class AdditionalActionFactory {

	public static List<AdditionalAction> getActions(String actionsPart) {
		List<AdditionalAction> list = new ArrayList<AdditionalAction>();
		for(char action : actionsPart.toCharArray()) {
			AdditionalAction act = getAction(action);
			if (act != null) list.add(act);
		}
		return list;
	}

	private static AdditionalAction getAction(char action) {
		switch (action) {
		case 'R':
			return new CastleAction();
		case 'E':
			return new EnPassantAction();
		case 'P':
			return new PromotionAction();
		default:
			return null;
		}
	}

}
