import java.util.List;
import java.util.Random;

public class Agent {

	// Attributes
	private int id;
	private List<Integer> thresholds;					// History of agents over time
	private List<Integer> threshold_decrements;
	private Random seedThresh = new Random();

	// Constructor
	public Agent(int id, int nbTasks) {
		this.setId(id);
		for(int i=0; i<nbTasks; i++) {
            thresholds.add(seedThresh.nextInt(100));
        }
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
