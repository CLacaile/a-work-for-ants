import java.util.ArrayList;
import java.util.List;

public class History {

	// Attributes
	private ArrayList<ArrayList<Float>> thresholds = new ArrayList<>(); //A list of thresholds of agent at each iteration

	private ArrayList<ArrayList<Float>> relevances = new ArrayList<>(); //A list of relevance of task at each iteration

	private ArrayList<Float> relevanceSum = new ArrayList<>(); // A list of relevance sum at each iteration

	// Constructor
	public History(){

	}

	// Getters
	public ArrayList<ArrayList<Float>> getThresholds() {
		return this.thresholds;
	}

	public ArrayList<ArrayList<Float>> getRelevances() {
		return this.relevances;
	}

	public ArrayList<Float> getRelevanceSum() { return this.relevanceSum;}

	// Setters
	public void setThresholds(ArrayList<ArrayList<Float>> thresholds) {
		this.thresholds = thresholds;
	}

	public void setRelevances(ArrayList<ArrayList<Float>> relevances) {
		this.relevances = relevances;
	}

	public void setRelevanceSum(ArrayList<Float> relevanceSumList) { this.relevanceSum = relevanceSumList;}

	// Methods

    /**
     * Add the thresholds of an agent to the history
     * @param agent the agent
     */
    public void addThresholdsToHistory(Agent agent) {
	    this.thresholds.add(this.thresholds.size(), agent.getThresholds());
    }

    /**
     * Fill the history thresholds list
     * @param agentList the list of agents
     */
	public void fillThresholds(List<Agent> agentList){
		for(int i=0;i < agentList.size(); i++){
			thresholds.add(agentList.get(i).getThresholds());
		}
	}

    /**
     * Add the relevances of a task to the history
     * @param task the task
     */
	public void addRelevanceToHistory(Task task) {
	    this.relevances.add(this.relevances.size(), task.getTasksRelevances().getRelevanceArrayList());
    }
    /**
     * Fill the history relevance list
     * @param taskList the list of task
     */
	public void fillRelevance(List<Task> taskList){
		for(int i=0;i < taskList.size(); i++){
			relevances.add(taskList.get(i).getTasksRelevances().getRelevanceArrayList());
		}


	}
}
