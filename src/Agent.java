import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent {

    // Attributes
	private int id;
	private static float proba;
	private State state;
	private ArrayList<Float> thresholds = new ArrayList<>();            // A threshold per task
	private float thresholdSum=1;
	private ArrayList<Float> threshold_decrements = new ArrayList<>();
	private Task nextTask; //to change to nextTask

    public enum State {
        Init,   // no task chosen
        Idle,   // task has been chosen but not performed yet
        Working,
        Sleeping,
        Dead;
    }

	// Constructor
	public Agent(int id, int nbOfTasks) {
		this.id = id;
		this.state = State.Init;
		// Setting of the thresholds
        float temp_sum = 0;
        for(int i = 0; i < nbOfTasks; i++){
			float random = Simulation.randomFloatGenerator(Simulation.randomGenerator);
			thresholds.add(random);
			temp_sum+=random;
		}
		// Normalize the thresholds so that the sum is 1
		for(int i = 0; i < nbOfTasks; i++){
			thresholds.set(i, thresholds.get(i) / temp_sum);
		}
		// Generate threshold decrements in [0, threshold] // TODO max could be 0.01 for ex
		for(int i = 0; i < nbOfTasks; i++){
			Float max = thresholds.get(i);
			Float random = Simulation.randomFloatInRange(Simulation.randomGenerator, new Float(0), max);
			threshold_decrements.add(random);
		}
		// Setting the picked task to an empty one
        this.nextTask = new Task(-1, nbOfTasks); // is it a good idea ?
	}

	// Getters
    public int getId() {
        return id;
    }

    public ArrayList<Float> getThresholds() {
        return thresholds;
    }

    public Float getLastThreshold() { return this.thresholds.get(this.thresholds.size()-1); }

    public List<Float> getThreshold_decrements() {
        return threshold_decrements;
    }

    public Task getNextTask() {
        return this.nextTask;
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
        this.nextTask = pickedTask;
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
	    //int taskIndex = task.getId();
	    Float previousTaskRelevance = task.getTaskRelevanceAtIndex(-1);
	    // Setting new relevance to (previousTaskRelevance - 0.01) if the task has still relevance left
        if (!previousTaskRelevance.equals(new Float(0))) {
            task.getTasksRelevances().getRelevanceArrayList().add(previousTaskRelevance - new Float(0.01));
        }
        // If the task is done
        else {
            return true;
        }
		return false;
	}

    /**
     * This method is used to perform the previousTask picked by pickTask. It substracts 0.01 to the task relevance if
     * the task relevance is not 0
     * @return true if the task is performed ie. there is no relevance left, false otherwise
     */
    public boolean performPickedTask() {
        int taskIndex = this.nextTask.getId();
        Float previousTaskRelevance = this.nextTask.getTaskRelevanceAtIndex(taskIndex);
        // Setting new relevance to (previousTaskRelevance - 0.01) if the task has still relevance left
        if (!previousTaskRelevance.equals(new Float(0))) {
            this.nextTask.setRelevanceAtIndex(taskIndex, previousTaskRelevance - new Float(0.01));
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
		this.nextTask = tasks.get(i);
		return this.nextTask;
	}

    /**
     * This function determines if the agent should go to the next state or not according to a random value
     * @param threshold the decision threshold
     * @return true if it should go to the next state, false otherwise
     */
	public boolean nextState(float threshold) {
	    Random seed = new Random();
	    float random = seed.nextFloat();
	    if(threshold >= random)
	        return true;
	    else
	        return false;
    }

    /**
     * This function implements the life of an agent through states defined by the enum State. An agent state can be:
     * - init: it just has been created and got not task chosen (see constructor)
     * - idle: a task has been chosen but not performed yet
     * - working: it is performing the chosen task
     * - sleeping: a task has been chosen but is not being performed
     * - dead: it was either working and sleeping, and is now dead
     */
	public void liveLife(ArrayList<Task> tasks) {
	    switch (this.state) {
            case Init:
                System.out.println("Init!");
                this.state = State.Idle;
                break;
            case Idle:
                this.pickTask(tasks);
                System.out.println("Idle! Picked task #" + this.getNextTask().getId());
                if(this.nextState(new Float(0.7)))
                    this.state = State.Working;
                else
                    this.state = State.Sleeping;
                break;
            case Working:
                this.performTask(this.nextTask);
                System.out.println("Working on task #"+this.getNextTask().getId()+" !");
                // decide what to do next:
                if(this.nextState(new Float(0.90)) != true )
                    this.state = State.Working;
                else if(this.nextState(new Float(0.10)) != true )
                    this.state = State.Idle;
                else
                    this.state = State.Dead;
                break;
            case Sleeping:
                // do nothing
                System.out.println("Sleeping!");
                // decide what to do next:
                if(this.nextState(new Float(0.90)) != true )
                    this.state = State.Sleeping;
                else if(this.nextState(new Float(0.10)) != true )
                    this.state = State.Idle;
                else
                    this.state = State.Dead;
                break;
            case Dead:
                // do nothing
                System.out.println("Dead!");
                break;
        }
    }
}
