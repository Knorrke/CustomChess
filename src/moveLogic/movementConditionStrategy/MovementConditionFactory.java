package moveLogic.movementConditionStrategy;

public class MovementConditionFactory {

	public static MovementCondition getBehaviour(String movementRule) {
		String[] ruleparts = movementRule.split(",");

		if (ruleparts.length > 2) {
			throw new IllegalArgumentException("Wrong dimension in rule");
		} else if (ruleparts.length == 2) {
			if (ruleAllowsAll(ruleparts)) {
				return new AllowAll();
			} else if (ruleAllowsDiagonal(ruleparts)) {
				return new OnlyDiagonal(ruleparts);
			} else {
				return new OnlyCorrectDistance(ruleparts);
			}
		} else {
			return new OnlyCorrectSquare(movementRule);
		}
	}

	/**
	 * Checks if the rule is of form "n,m"
	 * 
	 * @param ruleparts
	 * @return
	 */
	private static boolean ruleAllowsAll(String[] ruleparts) {
		return ruleparts[0].equals("n") && ruleparts[1].equals("m");
	}

	/**
	 * Checks if the rule is of form "n,n"
	 * 
	 * @param ruleparts
	 * @return
	 */
	private static boolean ruleAllowsDiagonal(String[] ruleparts) {
		return ruleparts[0].equals("n") && ruleparts[1].equals("n")
				|| ruleparts[0].equals("n0") && ruleparts[1].equals("n0");
	}
}
