import java.util.List;

public class Agent {

	// Attributes
	private int id;
	private List<Integer> thresholds;					// History of agents over time
	private List<Integer> threshold_decrements;

	// Constructor
	public Agent() {
		this.setId(1);
	}

	// Getters
	public int getId() {
		return id;
	}

	public List<Integer> getThresholds() {
		return thresholds;
	}

	public List<Integer> getThreshold_decrements() {
		return threshold_decrements;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setThresholds(List<Integer> thresholds) {
		this.thresholds = thresholds;
	}

	public void setThreshold_decrements(List<Integer> threshold_decrements) {
		this.threshold_decrements = threshold_decrements;
	}

	// Methods
	public boolean do_task(Task task) {
		return false;
	}

	/**
	 * Implementation of roulette wheel selection algorithm to determine the 
	 * agent task to perform
	 */
	private Task roulette_wheel(List<Task> tasks) {
		return null;
	}



}
