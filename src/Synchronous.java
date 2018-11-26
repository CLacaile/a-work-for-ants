public class Synchronous extends Simulation {

	public void runSimulation() {
		this.computeRelevanceSum();
		while(this.relevanceSum > (new Float(0.01))) {
			for (int i = 0; i < numberOfIteration; i++) {
				for (int j = 0; j < total_agent_number; j++) {
					agents.get(j).performPickedTask();
					agents.get(j).pickTask(this.tasks);
				}
				computeRelevanceSum();
				System.out.println("t" + i + " : relevanceSum = " + this.relevanceSum);
			}
		}
	}
}
