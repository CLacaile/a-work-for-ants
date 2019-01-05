import java.util.Random;

public class Synchronous extends Simulation {

	public Synchronous(Random seed) {
		super(seed);
	}

	public void runSimulation() {
		for(int i=0; i<numberOfIteration; i++) {
			System.out.println("--------------- Iteration #"+i+" : ---------------");
			for(int j=0; j<this.total_agent_number; j++) {
				this.agents.get(j).liveLife(this.tasks);
			}
			this.computeRelevanceSum();
			System.out.println("Relevance sum: "+this.getRelevanceSum());
			// Fix for the relevance problem

		}
	}
}