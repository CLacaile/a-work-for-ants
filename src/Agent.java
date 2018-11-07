import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent {

    // Attributes
	private int id;
	private static float proba;
	private ArrayList<Float> thresholds = new ArrayList<>();            // A threshold per task
	private float thresholdSum=1;
	private ArrayList<Integer> threshold_decrements = new ArrayList<>();
	private Task pickedTask;

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

    public Task getPickedTask() {
        return pickedTask;
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

    public void setPickedTask(Task pickedTask) {
        this.pickedTask = pickedTask;
    }


	// Methods

	/**
	 * This method is used to compute the sum of the thresholds of the agent
	 * @return a float which is the sum of thresholds
	 */
	public float sumThresholds(){
		float sum=0;
		for (int i = 0; i < thresholds.size(); i++){
			sum=+ thresholds.get(i);
		}

		return sum;
	}

	/**
	 * This method is used to perform a given task
	 * @param task the task to perform
	 * @return true if the task is performed, false otherwise
	 */
	public boolean do_task(Task task) {
		return false;
	}

	/**
	 * Implementation of roulette wheel selection algorithm to determine the
	 * task to perform by the agent
	 * @param tasks the list of tasks to perform
	 * @return a task to perform
	 */
	public Task pickTask(List<Task> tasks) {
		float t = thresholds.get(0);
		Random seed = new Random();
		float a = seed.nextFloat();
		int i = 0;
		while (t < a) {
			i++;
			t += thresholds.get(i);
		}
		this.setPickedTask(tasks.get(i));
		return tasks.get(i);
	}



}
