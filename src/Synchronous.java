public class Synchronous extends Simulation {

	public void runSimulation() {
		this.computeRelevanceSum();
		while(this.relevanceSum > (new Float(0.01))) {
			System.out.println("1");
			for (int i = 0; i < numberOfIteration; i++) {
				System.out.println("2");
				for (int j = 0; j < total_agent_number; j++) {
					System.out.println("3");
					agents.get(j).performPickedTask();
					agents.get(j).pickTask(this.tasks);
				}
				computeRelevanceSum();
				System.out.println("t" + i + " : relevanceSum = " + this.relevanceSum);
			}
		}
	}
}
