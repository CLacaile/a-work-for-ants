import java.util.ArrayList;
import java.util.List;

public class History {

	/**
	 * A list of agent thresholds throughout time
	 */
	private ArrayList<ArrayList<Float>> thresholds = new ArrayList<>();

	private ArrayList<ArrayList<Float>> relevances = new ArrayList<>();

	public History(){

	}

	public ArrayList<ArrayList<Float>> getThresholds() {
		return thresholds;
	}

	public void setThresholds(ArrayList<ArrayList<Float>> thresholds) {
		this.thresholds = thresholds;
	}

	public ArrayList<ArrayList<Float>> getRelevances() {
		return relevances;
	}

	public void setRelevances(ArrayList<ArrayList<Float>> relevances) {
		this.relevances = relevances;
	}

	public void fillThresholds(List<Agent> agentLis){
		for(int i=0;i < agentLis.size(); i++){
			thresholds.add(agentLis.get(i).getThresholds());
		}
	}

	public void fillRelevance(List<Task> taskList){
		for(int i=0;i < taskList.size(); i++){
			relevances.add(taskList.get(i).getRelevance().getRelevance());
		}


	}
}
