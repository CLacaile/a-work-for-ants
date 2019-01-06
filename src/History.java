import java.util.ArrayList;
import java.util.List;

public class History {

	// Attributes
	private ArrayList<ArrayList<Float>> thresholds = new ArrayList<>(); //A list of thresholds of agent at each iteration

	private ArrayList<ArrayList<Float>> relevances = new ArrayList<>(); //A list of relevance of task at each iteration

	// Constructor
	public History(){

	}

	// Getters
	public ArrayList<ArrayList<Float>> getRelevances() {
		return this.relevances;
	}

	// Methods
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
     * Fill the history relevance list
     * @param taskList the list of task
     */
	public void fillRelevance(List<Task> taskList){
		for(int i=0;i < taskList.size(); i++){
			relevances.add(taskList.get(i).getTasksRelevances().getRelevanceArrayList());
		}


	}
}
