import java.util.List;

public class Task {

	// Attributes
	private int id;
	private Relevance tasksRelevances;
	private List<Integer> agents;

	// Constructor
	public Task(int id, int nbOfTasks) {
		this.id = id;
		tasksRelevances = new Relevance(nbOfTasks);
	}

	// Getters
	public int getId() {
		return id;
	}

	public Relevance getTasksRelevances() {
		return tasksRelevances;
	}

	/**
	 * Returns the task relevance at the instant t_index. If index = -1, the functions returns the last relevance
	 * @param index the instant of the relevance
	 * @return the relevance float at the instant t_index
	 */
	public Float getTaskRelevanceAtIndex(int index) {
		if (index < 0 ) {
			int sizeOfRelvevanceArrayList = tasksRelevances.getRelevanceArrayList().size();
			return tasksRelevances.getRelevanceArrayList().get(sizeOfRelvevanceArrayList-1);
		}
		return tasksRelevances.getRelevanceArrayList().get(index);
	}

	public List<Integer> getAgents() {
		return agents;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setTasksRelevances(Relevance tasksRelevances) {
		this.tasksRelevances = tasksRelevances;
	}

	public void setRelevanceAtIndex(int index, Float newRelevance) {
		tasksRelevances.getRelevanceArrayList().set(index, newRelevance);
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


