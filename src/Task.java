import java.util.List;

public class Task {

	// Attributes
	private int id;
	private Relevance relevances;
	private List<Integer> agents;

	// Constructor
	public Task(int id, int nbOfTasks) {
		this.id = id;
		relevances = new Relevance(nbOfTasks);
	}

	// Getters
	public int getId() {
		return id;
	}

	public Relevance getRelevances() {
		return relevances;
	}

	public List<Integer> getAgents() {
		return agents;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setRelevances(Relevance relevances) {
		this.relevances = relevances;
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


