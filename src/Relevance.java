import java.util.ArrayList;

public class Relevance {

	// Attributes
	private ArrayList<Float> relevance = new ArrayList<>();
	private int min;
	private int max;
	private boolean periodical;

	// Constructor
	public Relevance(int nbOfIterations) {
		// Setting of the relevances (constant value)
			relevance.add(new Float(0));

	}

	// Getters
	public ArrayList<Float> getRelevanceArrayList() {
		return relevance;
	}
}
