import java.util.ArrayList;
import java.util.List;

public class Agent {

	private int id;

	private static float proba;

	/**
	 * History of the agent thresholds over time
	 *
	 */
	private ArrayList<Float> thresholds = new ArrayList<>();

	private float thresholdSum=1;

	private ArrayList<Integer> threshold_decrements = new ArrayList<>();

	public Agent(int id, int nbOfTasks) {
		this.id = id;
		for(int i = 0; i < nbOfTasks; i++){
			float random = Simulation.randomIntInRange(0, (int) Math.ceil(thresholdSum*100));
			random /= 100;
			thresholds.add(random);
			thresholdSum-=random;
		}
	}

	public float sumThresholds(){
		float sum=0;
		for (int i = 0; i < thresholds.size(); i++){
			sum=+ thresholds.get(i);
		}

		return sum;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Float> getThresholds() {
		return thresholds;
	}

	public void setThresholds(ArrayList<Float> thresholds) {
		this.thresholds = thresholds;
	}

	public List<Integer> getThreshold_decrements() {
		return threshold_decrements;
	}

	public void setThreshold_decrements(ArrayList<Integer> threshold_decrements) {
		this.threshold_decrements = threshold_decrements;
	}
}
