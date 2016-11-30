package moveLogic.specialMoveConditionStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialMoveConditionFactory {

	public static SpecialMoveCondition getBehaviour(char condition) {
		switch (condition) {
		case 'C':
			return new OnlyCapture();
		case 'M':
			return new OnlyMove();
		case '!':
			return new OnlyToNotAttackedSquare();
		case '#':
			return new OnlyIfAttacked();
		case '*':
			return new OnlyIfNotAttacked();
		case 'F':
			return new OnlyIfFreeWay();
		case 'J':
			return new OnlyIfJumpOverPiece();
		case 'N':
			return new OnlyIfNeverMoved();
		case 'R':
			return new Castling();
		case 'E':
			return new EnPassant();
		case '(':
		case ')':
		case '+':
		case '&':
		default:
			return null;
		}
	}
	
	public static SpecialMoveCondition getBehaviour(String conditionPart) {
		SpecialMoveConditionParser parser = new SpecialMoveConditionParser(conditionPart);
		return parser.parse();
	}

	private final static class SpecialMoveConditionParser {
		private int progress;
		private String condition;

		public SpecialMoveConditionParser(String condition) {
			progress = 0;
			this.condition = condition;
		}

		public SpecialMoveCondition parse() {
			SpecialMoveCondition smc;
			int nextSpecialChar = max(condition.indexOf("(", progress), condition.indexOf("+", progress), condition.indexOf(")", progress));

			List<SpecialMoveCondition> list = new ArrayList<>();
			if (nextSpecialChar == -1) {
				for (char c : condition.substring(progress).toCharArray()) {
					SpecialMoveCondition next = getBehaviour(c);
					if (next != null)
						list.add(next);
				}
				progress = condition.length();
				return new And(list);
			} else {
				for (char c : condition.substring(progress, nextSpecialChar).toCharArray()) {
					SpecialMoveCondition next = getBehaviour(c);
					if (next != null)
						list.add(next);
				}
				progress = nextSpecialChar + 1;

				if (condition.charAt(nextSpecialChar) == '(') {
					list.add(parse());
					smc = new And(list);
				} else if (condition.charAt(nextSpecialChar) == '+') {
					smc = new Or(new And(list), parse());
				} else {
					smc = new And(list);
				}
				return smc;
			}
		}

		private int max(int... is) {
			return Arrays.stream(is).reduce(-1, Math::max);
		}

	}
}
