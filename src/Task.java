import java.util.List;

public class Task {

	private int id;

	private Relevance relevance;

	private List<Integer> agents;

	public Task(int id, float relevanceSum) {
		this.id = id;
		relevance = new Relevance(relevanceSum);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Relevance getRelevance() {
		return relevance;
	}

	public void setRelevance(Relevance relevance) {
		this.relevance = relevance;
	}

	public List<Integer> getAgents() {
		return agents;
	}

	public void setAgents(List<Integer> agents) {
		this.agents = agents;
	}

	public void Task() {

	}

	/**
	 * Change la relevance selon le nb d'agent qui ont perform la tache
	 */
	public void task_done() {

	}
}


