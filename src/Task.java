import java.util.List;

public class Task {

	// Attributes
	private int id;
	private Relevance tasksRelevances;

	// Constructor
	public Task(int id, int nbOfIterations) {
		this.id = id;
		tasksRelevances = new Relevance(nbOfIterations);
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
}


