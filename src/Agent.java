import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent {

    // Attributes
	private int id;
	private static float proba;
	private ArrayList<Float> thresholds = new ArrayList<>();            // A threshold per task
	private float thresholdSum=1;
	private ArrayList<Float> threshold_decrements = new ArrayList<>();
	private Task pickedTask; //to change to nextTask

	// Constructor
	public Agent(int id, int nbOfTasks) {
		this.id = id;
		// Setting of the thresholds
        float temp_sum = 0;
        for(int i = 0; i < nbOfTasks; i++){
			float random = Simulation.randomFloatGenerator();
			thresholds.add(random);
			temp_sum+=random;
		}
		// Normalize the thresholds so that the sum is 1
		for(int i = 0; i < nbOfTasks; i++){
			thresholds.set(i, thresholds.get(i) / temp_sum);
		}
		// TODO Setting of the threshold_decrements
		// Generate threshold decrements in [0, threshold]
		for(int i = 0; i < nbOfTasks; i++){
			Float max = thresholds.get(i);
			Float random = Simulation.randomFloatInRange(new Float(0), max);
			threshold_decrements.add(random);
		}
	}

	// Getters
    public int getId() {
        return id;
    }

    public ArrayList<Float> getThresholds() {
        return thresholds;
    }

    public List<Float> getThreshold_decrements() {
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


    public void setThreshold_decrements(ArrayList<Float> threshold_decrements) {
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
			sum += thresholds.get(i);
		}

		return sum;
	}

	/**
	 * This method is used to perform a given task. It substracts 0.01 to the task relevance if the task relevance is
     * not 0
	 * @param task the task to perform
	 * @return true if the task is performed ie. there is no relevance left, false otherwise
	 */
	public boolean performTask(Task task) {
	    int taskIndex = task.getId();
	    Float previousTaskRelevance = task.getTaskRelevanceAtIndex(taskIndex);
	    // Setting new relevance to (previousTaskRelevance - 0.01) if the task has still relevance left
        if (!previousTaskRelevance.equals(new Float(0))) {
            task.setRelevanceAtIndex(taskIndex, previousTaskRelevance - new Float(0.01));
        }
        // If the task is done
        else {
            return true;
        }
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
