import java.util.List;

public class Task {

	// Attributes
	private int id;
	private Relevance relevance;
	private List<Integer> agents;

	// Constructor
	public Task(int id, float relevanceSum) {
		this.id = id;
		relevance = new Relevance(relevanceSum);
	}

	// Getters
	public int getId() {
		return id;
	}

	public Relevance getRelevance() {
		return relevance;
	}

	public List<Integer> getAgents() {
		return agents;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setRelevance(Relevance relevance) {
		this.relevance = relevance;
	}

	public void setAgents(List<Integer> agents) {
		this.agents = agents;
	}

	// Methods
	/**
	 * Change la relevance selon le nb d'agent qui ont perform la tache
	 */
	public void task_done() {

	}
}


