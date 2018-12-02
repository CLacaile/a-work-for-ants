import java.util.ArrayList;

public class Relevance {

	// Attributes
	private ArrayList<Float> relevance = new ArrayList<>();
	private int min;
	private int max;
	private int seed;
	private boolean periodical;

	// Constructor
	public Relevance(int nbOfIterations) {
		// Setting of the relevances (constant value)
		for(int i = 0; i < nbOfIterations; i++){
			//float random = Simulation.randomFloatGenerator(Simulation.randomGenerator);
			relevance.add(new Float(0.5));
		}
	}

	// Getters
	public ArrayList<Float> getRelevanceArrayList() {
		return relevance;
	}

	public float getRelevanceSum() {
		float sum = new Float(0);
		for(int i=0; i<this.relevance.size(); i++) {
			sum+=this.relevance.get(i);
		}
		return sum;
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
	public void addRelevanceAtLastIteration(Float relevance) {
		this.relevance.add(this.relevance.size(), relevance);
	}

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
