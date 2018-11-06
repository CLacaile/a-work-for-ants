import java.util.List;

public abstract class History {

	// Attributes
	private List<List<Integer>> thresholds;

	private List<Relevance> relevances;

	// Getters
	public List<List<Integer>> getThresholds() {
		return thresholds;
	}

	public List<Relevance> getRelevances() {
		return relevances;
	}

	// Setters
	public void setThresholds(List<List<Integer>> thresholds) {
		this.thresholds = thresholds;
	}

	public void setRelevances(List<Relevance> relevances) {
		this.relevances = relevances;
	}
}
