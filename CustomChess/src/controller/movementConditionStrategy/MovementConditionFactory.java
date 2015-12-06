package controller.movementConditionStrategy;

public class MovementConditionFactory {

	public static MovementCondition getBehaviour(String movementRule) {
		String[] ruleparts = movementRule.split(",");
		
		if(ruleparts.length < 2){
			throw new IllegalArgumentException("Wrong dimension in rule");
		}
		
		if(ruleAllowsAll(ruleparts)){
			return new AllowAll();
		}else if(ruleAllowsDiagonal(ruleparts)){
			return new OnlyDiagonal();			
		}else{
			return new OnlyCorrectDistance(ruleparts);
		}
	}
	
	/**
	 * Checks if the rule is of form "n,m"
	 * @param ruleparts
	 * @return
	 */
	private static boolean ruleAllowsAll(String[] ruleparts){
		return ruleparts[0].equals("n") && ruleparts[1].equals("m");
	}
	/**
	 * Checks if the rule is of form "n,n"
	 * @param ruleparts
	 * @return
	 */
	private static boolean ruleAllowsDiagonal(String[] ruleparts) {
		return ruleparts[0].equals(ruleparts[1]);
	}
}
