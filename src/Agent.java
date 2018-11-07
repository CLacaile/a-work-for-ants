import java.util.ArrayList;
import java.util.List;

public class Agent {

    // Attributes
	private int id;
	private static float proba;
	private ArrayList<Float> thresholds = new ArrayList<>();            // History of agents over time
	private float thresholdSum=1;
	private ArrayList<Integer> threshold_decrements = new ArrayList<>();

	// Constructor
	public Agent(int id, int nbOfTasks) {
		this.id = id;
		for(int i = 0; i < nbOfTasks; i++){
			float random = Simulation.randomIntInRange(0, (int) Math.ceil(thresholdSum*100));
			random /= 100;
			thresholds.add(random);
			thresholdSum-=random;
		}
	}

	// Getters
    public int getId() {
        return id;
    }

    public ArrayList<Float> getThresholds() {
        return thresholds;
    }

    public List<Integer> getThreshold_decrements() {
        return threshold_decrements;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }


    public void setThresholds(ArrayList<Float> thresholds) {
        this.thresholds = thresholds;
    }


    public void setThreshold_decrements(ArrayList<Integer> threshold_decrements) {
        this.threshold_decrements = threshold_decrements;
    }

	// Methods
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


}
