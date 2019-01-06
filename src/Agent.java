import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent {

    // Attributes
	private int id;
	private State state;
	private ArrayList<Float> thresholds = new ArrayList<>();            // A threshold per task
	private Task nextTask; //to change to nextTask
    private ArrayList<Float> ratio = new ArrayList<>();
    private ArrayList <Task> eligibleTasks = new ArrayList<>();
    private ArrayList<Float> eligibleThresholds = new ArrayList<>();
    private ArrayList<Integer> pickedTasksID = new ArrayList<>();
    private Random seed;


    public enum State {
        Init,   // no task chosen
        Idle,   // task has been chosen but not performed yet
        Working,
        Dead;
    }

	// Constructor
	public Agent(int id, int nbOfTasks, int nbOfIterations) {
		this.id = id;
		this.state = State.Init;
		this.seed = Simulation.seed;

		// Setting of the thresholds
        float temp_sum = 0;
        for(int i = 0; i < nbOfTasks; i++){
			float random = Simulation.randomFloatGenerator(Simulation.seed);
			thresholds.add(random);
			temp_sum+=random;
		}

		// Normalize the thresholds so that the sum is 1
		for(int i = 0; i < nbOfTasks; i++){
			thresholds.set(i, thresholds.get(i) / temp_sum);
		}

		// Init the ratios
        for(int i=0; i<nbOfTasks; i++){
            ratio.add(i, 0f);
        }

		// Setting the picked task to the null task
        this.nextTask = new Task(0, nbOfTasks); // is it a good idea ?

        // Init the pickedTasks list
        for(int i= 0; i<nbOfIterations; i++) {
            this.pickedTasksID.add(i, -1);
        }
	}

	// Getters
    public int getId() {
        return id;
    }

    public ArrayList<Float> getThresholds() {
        return thresholds;
    }

    public Float getLastThreshold() { return this.thresholds.get(this.thresholds.size()-1); }


    public Task getNextTask() {
        return this.nextTask;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setNextTask(Task nextTask) {
        this.nextTask = nextTask;
    }

    public ArrayList<Float> getRatio() {
        return ratio;
    }

    public void setRatio(ArrayList<Float> ratio) {
        this.ratio = ratio;
    }

    public ArrayList<Task> getEligibleTasks() {
        return eligibleTasks;
    }

    public void setEligibleTasks(ArrayList<Task> eligibleTasks) {
        this.eligibleTasks = eligibleTasks;
    }

    public ArrayList<Float> getEligibleThresholds() {
        return eligibleThresholds;
    }

    public void setEligibleThresholds(ArrayList<Float> eligibleThresholds) {
        this.eligibleThresholds = eligibleThresholds;
    }

    public Random getSeed() {
        return seed;
    }

    public void setSeed(Random seed) {
        this.seed = seed;
    }

    public ArrayList<Integer> getPickedTasksID() {
        return this.pickedTasksID;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setThresholds(ArrayList<Float> thresholds) {
        this.thresholds = thresholds;
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
	public boolean performTask(int iteration, Task task, ArrayList<Task> tasks) {
	    //int taskIndex = task.getId();
	    Float previousTaskRelevance = task.getTaskRelevanceAtIndex(-1);
	    // Check if the task still has relevance left
        if (!previousTaskRelevance.equals(new Float(0))) {
            // Update the other tasks
            float toAdd = (float) (0.05/(tasks.size()-1));
            for(int i = 0; i<tasks.size();i++){
                Float previousTaskRelevances = tasks.get(i).getTaskRelevanceAtIndex(-1);
                tasks.get(i).getTasksRelevances().getRelevanceArrayList().add(previousTaskRelevances+toAdd);
            }
            // Update the task
            task.getTasksRelevances().getRelevanceArrayList().set(iteration, previousTaskRelevance - new Float(0.05));
        }
        // If the task is done
        else {
            return true;
        }
		return false;
	}

    public boolean newPerformTask(int iteration, Task task, ArrayList<Task> tasks) {
        //int taskIndex = task.getId();
        Float previousTaskRelevance = task.getTaskRelevanceAtIndex(iteration);
        // Check if the task still has relevance left
        if (/*(previousTaskRelevance - (new Float(0.05))) >= (new Float(0.01))*/true) {
            task.getTasksRelevances().getRelevanceArrayList().set(iteration, previousTaskRelevance - new Float(0.05));
            // update the remaining relevances of the task with the new value
            for(int i=iteration; i<task.getTasksRelevances().getRelevanceArrayList().size(); i++) {
                task.getTasksRelevances().getRelevanceArrayList().set(i, previousTaskRelevance - new Float(0.05));
            }
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
	 * Implementation of roulette wheel selection algorithm on a list of tasks to determine the
	 * task to perform by the agent
	 * @param tasks the list of tasks to perform
	 * @return a task to perform
	 */
	public Task pickTask(List<Task> tasks) {
		float t = eligibleThresholds.get(0);
		Random seed = new Random();
		float a = seed.nextFloat();
		int i = 0;
		while (t < a) {
			i++;
			t += eligibleThresholds.get(i);
		}
		this.nextTask = eligibleTasks.get(i);
		return this.nextTask;
	}

    /**
     * Implementation of roulette wheel selection algorithm on a list of doable tasks. It determines the task to perform
     * by the agent. The list eligibleTasks must be computed before using computeEligibleTasks() !
     * @return the next task
     */
    public Task pickEligibleTask() {
        float t = eligibleTasks.get(0).getTaskRelevanceAtIndex(-1);
        //Random seed = new Random();
        float a = Simulation.randomFloatGenerator(Simulation.seed);
        int i = 0;
        // go through the eligible task table
        while (t < a) {
            i++;
            // if t will never be over a
            if(i == eligibleTasks.size()) {
                this.nextTask = this.eligibleTasks.get(i-1);
                return this.eligibleTasks.get(i-1);
            }
            // continue
            t += eligibleTasks.get(i).getTaskRelevanceAtIndex(-1);
        }
        this.nextTask = this.eligibleTasks.get(i);
        return this.eligibleTasks.get(i);
    }

    /**
     * Compute the ratios relevance/threshold of the agent
     * @param tasks the tasks of the simulation
     */
	public void computeRatio(List<Task> tasks){
	    int i = 0;
	    for(Task t : tasks) {
	        ratio.set(t.getId(), t.getTaskRelevanceAtIndex(-1)/this.thresholds.get(t.getId()));
            i++;
	    }
    }

    /**
     * Compute a list of doable tasks for the agent. A task is doable when the ratio is > 1. Uses computeRatio function.
     * @param tasks the list of tasks to perform
     */
    public void computeEligibleTasks(List<Task> tasks) {
        computeRatio(tasks);
        for(int i = 0; i < this.ratio.size(); i++)
        {
            if(this.ratio.get(i) > 1)
            {
                this.eligibleTasks.add(tasks.get(i));
                this.eligibleThresholds.add(thresholds.get(i));
            }
        }
        // if there is no eligible task, choose the null task
        if(this.eligibleTasks.isEmpty()) {
            this.eligibleTasks.add(tasks.get(0));
        }
    }

    /**
     * This function determines if the agent should go to the next state or not according to a random value
     * @param threshold the decision threshold
     * @return true if it should go to the next state, false otherwise
     */
	public boolean nextState(float threshold) {
	    //Random seed = new Random();
	    //float random = seed.nextFloat();
	    float random = Simulation.randomFloatGenerator(Simulation.seed);
	    if(random <= threshold)
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
	public void liveLife(int iteration, ArrayList<Task> tasks) {
        float random = Simulation.randomFloatGenerator(Simulation.seed);
	    switch (this.state) {
            case Init:
                System.out.println("Init!");
                this.state = State.Idle;
                break;
            case Idle:
                this.computeEligibleTasks(tasks);
                this.pickEligibleTask();
                this.pickedTasksID.set(iteration, this.nextTask.getId());
                // do nothing else
                System.out.println("Idle! Picked task #" + this.getNextTask().getId());
                if(random > (new Float(0.2)))
                    this.state = State.Working;
                else
                    this.state = State.Idle;
                break;
            case Working:
                // perform task
                this.newPerformTask(iteration, this.nextTask,tasks);
                System.out.println("Working on task #"+this.getNextTask().getId()+" !");
                // decide what to do next:
                if(random > (new Float(0.30)) == true )
                    this.state = State.Working;
                else if(random > (new Float(0.05)) == true )
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
