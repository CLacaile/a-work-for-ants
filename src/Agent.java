public class Agent {

	private int id;

	/**
	 * History of the agent thresholds over time
	 * 
	 */
	private List<Integer> thresholds;

	private List<Integer> threshold_decrements;

	public void Agent() {

	}

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
