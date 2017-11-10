package moveLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rule {
	
	private List<SingleRule> singleRules;
	
	public Rule() {
		this.singleRules = new ArrayList<>();
	}
	
	public Rule(List<SingleRule> singleRules) {
		this.singleRules = singleRules;
	}

	public List<SingleRule> getMatchingSingleRules(int[] newPos) {
		return singleRules.stream()
				.filter(rule -> rule.matches(newPos))
				.collect(Collectors.toList());
	}

	public void add(SingleRule singleRule) {
		singleRules.add(singleRule);
	}
}
