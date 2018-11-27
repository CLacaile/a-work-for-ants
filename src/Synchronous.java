public class Synchronous extends Simulation {

	public void runSimulation() {
	    //TODO modify this function so it reflects the changes made in the task performing mgmt in Agent
		this.computeRelevanceSum();
		//while(this.relevanceSum > (new Float(0.01))) {
			for (int i = 0; i < numberOfIteration; i++) {
				for (int j = 0; j < total_agent_number; j++) {
					agents.get(j).performPickedTask(); // TODO invert line 8 and 9
					agents.get(j).pickTask(this.tasks);
				}
				computeRelevanceSum();
				System.out.println("t" + i + " : relevanceSum = " + this.relevanceSum);
			}
		//}
	}
}
