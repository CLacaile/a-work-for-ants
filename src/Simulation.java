import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Simulation {

	// Attributes
	protected int numberOfIteration;
	protected int total_agent_number;
	protected int total_task_number;
	protected float relevanceSum;
	protected ArrayList<Agent> agents = new ArrayList<>();
	protected ArrayList<Task> tasks = new ArrayList<>();
	protected static Random randomGenerator = new Random();
	protected History history = new History();

	// Constructor
	public Simulation() {
		computeRelevanceSum();
		relevanceSum=getRelevanceSum();
	}

	// Getters
	public int getNumberOfIteration() {
		return numberOfIteration;
	}
	public float getRelevanceSum() {
		return relevanceSum;
	}

	public int getTotal_agent_number() {
		return total_agent_number;
	}

	public int getTotal_task_number() {
		return total_task_number;
	}

	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public History getHistory() { return history;}

	// Setters
	public void setNumberOfIteration(int numberOfIteration) {
		this.numberOfIteration = numberOfIteration;
	}

	public void setRelevanceSum(float relevanceSum) {
		this.relevanceSum = relevanceSum;
	}

	public void setTotal_agent_number(int total_agent_number) {
		this.total_agent_number = total_agent_number;
	}

	public void setTotal_task_number(int total_task_number) {
		this.total_task_number = total_task_number;
	}

	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	// Methods

	/**
	 * This method computes the relevance sum and set the value of this.relevanceSum attribute
	 */
	public void computeRelevanceSum() {
		Float sum = new Float(0);
		for (int i=0; i<total_task_number; i++) {
			sum += tasks.get(i).getTaskRelevanceAtIndex(-1);
		}
		setRelevanceSum(sum);
	}

	/**
	 * This method is used to create a new task
	 * @param id the id of the task
	 */
	public void createTask(int id){
		Task task = new Task(id, total_task_number);
		tasks.add(task);
		relevanceSum-=task.getTasksRelevances().getRelevanceArrayList().get(0);
	}

	/**
	 * This method is used to create a given number of tasks. It uses createTask.
	 * @param nbOfTasks the number of tasks to create
	 */
	public void createNTasks(int nbOfTasks){
		for(int i = 0; i<nbOfTasks; i++){
			createTask(i+1);
		}
	}

	/**
	 * This method assigns a task to perform to each agent of the simulation. It uses Agent.pickTask() function.
	 */
	public void assignTasks(){
		for(int i=0; i<this.getTotal_agent_number(); i++) {
			this.getAgents().get(i).pickTask(this.getTasks());
		}
	}

	/**
	 * This method is used to create a new agent
	 * @param id the id of the agent to create
	 */
	public void createAgent(int id){
		Agent agent = new Agent(id, tasks.size());
		agents.add(agent);
	}

	/**
	 * This method is used to create a given number of agents
	 * @param nbOfAgents the number of agents to create
	 */
	public void createNAgents(int nbOfAgents){
		for(int i = 0; i<nbOfAgents; i++){
			createAgent(i+1);
		}
	}

	/**
	 * This method is used to create a random float number between 0 and 1
	 * @param seed the seed
	 * @return a float between 0 and 1
	 */
	public static Float randomFloatGenerator(Random seed) {
		return seed.nextFloat();
	}

	/**
	 * This method is used to create a random float number between min and max
	 * @param seed the seed
	 * @param min the min float
	 * @param max the max float
	 * @return a float between 0 and 1
	 */
	public static Float randomFloatInRange(Random seed, Float min, Float max) {
		return min + seed.nextFloat() * (max - min);
	}

	/**
	 * This method is used to create a random float number between a min and a max value
	 * @param min the min value
	 * @param max the max value
	 * @return an integer between min and max
	 */
	public static int randomIntInRange(Random seed, int min, int max) {
		return seed.nextInt((max - min) + 1) + min;
	}

	/**
	 * This method is used to export the History values to a .csv file
	 * @throws IOException
	 */
	public void exportHistoryToCsv() throws IOException {
		String csvFile = "history.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtil.writeLine(writer, Arrays.asList("sep=,"));
		CSVUtil.writeLine(writer, Arrays.asList("Iteration", "Agent ID", "Task ID", "Thresholds", "Task Relevance"), ',', '"');

		for(int i=0; i<numberOfIteration; i++){
			for(int j=0; j<agents.size(); j++){
				for(int k=0; k<total_agent_number; k++){
					for(int l=0; l < total_task_number; l++){
						CSVUtil.writeLine(writer,
								Arrays.asList(
										String.valueOf(i+1),
										String.valueOf(k+1),
										String.valueOf(l+1),
										String.valueOf(history.getThresholds().get(j).get(l)),
										String.valueOf(history.getRelevances().get(i).get(l))),
								',',
								'"'
						);
					}

				}

			}


		}
		writer.flush();
		writer.close();
	}
}
