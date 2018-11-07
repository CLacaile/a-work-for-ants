import java.util.ArrayList;

public class Relevance {

	// Attributes
	private ArrayList<Float> relevance = new ArrayList<>();
	private int min;
	private int max;
	private int seed;
	private boolean periodical;

	// Constructor
	public Relevance(float relevanceSum) {
		float random = Simulation.randomIntInRange(0, (int) Math.ceil(relevanceSum*100));
		random /= 100;
		relevance.add(random);
	}

	// Getters
	public ArrayList<Float> getRelevance() {
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
	public void setRelevance(ArrayList<Float> relevance) {
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
