import java.util.ArrayList;

public class Relevance {

	// Attributes
	private ArrayList<Float> relevance = new ArrayList<>();
	private int min;
	private int max;
	private int seed;
	private boolean periodical;

	// Constructor
	public Relevance(int nbOfTasks) {
		// Setting of the relevances
		float temp_sum = 0;
		for(int i = 0; i < nbOfTasks; i++){
			float random = Simulation.randomFloatGenerator(Simulation.randomGenerator);
			relevance.add(random);
			temp_sum+=random;
		}
		// Normalize the relevances so that the sum is 1
		for(int i = 0; i < nbOfTasks; i++){
			relevance.set(i, relevance.get(i) / temp_sum);
		}
	}

	// Getters
	public ArrayList<Float> getRelevanceArrayList() {
		return relevance;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getSeed() {
		return seed;
	}

	// Setters
	public void setRelevanceArrayList(ArrayList<Float> relevance) {
		this.relevance = relevance;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public boolean isPeriodical() {
		return periodical;
	}

	public void setPeriodical(boolean periodical) {
		this.periodical = periodical;
	}

	// Methods
	public void init_relevance(int min, int max, int seed, boolean periodical) {

	}

	public void update_relevance() {

	}


}
