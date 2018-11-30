import java.util.ArrayList;
import java.util.List;

public class History {

	// Attributes
	private ArrayList<ArrayList<Float>> thresholds = new ArrayList<>(); //A list of agent thresholds throughout time

	private ArrayList<ArrayList<Float>> relevances = new ArrayList<>();

	private ArrayList<Float> relevanceSum = new ArrayList<>(); // A list of relevance sum through time

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
	public void fillThresholds(List<Agent> agentList){
		for(int i=0;i < agentList.size(); i++){
			thresholds.add(agentList.get(i).getThresholds());
		}
	}

	public void fillRelevance(List<Task> taskList){
		for(int i=0;i < taskList.size(); i++){
			relevances.add(taskList.get(i).getTasksRelevances().getRelevanceArrayList());
		}


	}
}
