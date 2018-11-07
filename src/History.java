import java.util.ArrayList;
import java.util.List;

public class History {

	// Attributes
	private ArrayList<ArrayList<Float>> thresholds = new ArrayList<>(); //A list of agent thresholds throughout time

	private ArrayList<ArrayList<Float>> relevances = new ArrayList<>();

	// Constructor
	public History(){

	}

	// Getters
	public ArrayList<ArrayList<Float>> getThresholds() {
		return thresholds;
	}

	public ArrayList<ArrayList<Float>> getRelevances() {
		return relevances;
	}

	// Setters
	public void setThresholds(ArrayList<ArrayList<Float>> thresholds) {
		this.thresholds = thresholds;
	}

	public void setRelevances(ArrayList<ArrayList<Float>> relevances) {
		this.relevances = relevances;
	}

	// Methods
	public void fillThresholds(List<Agent> agentList){
		for(int i=0;i < agentList.size(); i++){
			thresholds.add(agentList.get(i).getThresholds());
		}
	}

	public void fillRelevance(List<Task> taskList){
		for(int i=0;i < taskList.size(); i++){
			relevances.add(taskList.get(i).getRelevance().getRelevance());
		}


	}
}
