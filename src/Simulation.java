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
	protected static Random seed;
	protected History history = new History();

	// Constructor
	//public Simulation() {}

	public Simulation(Random seed) {
		computeRelevanceSum();
		this.seed = seed;
	}

	// Getters
	public int getNumberOfIteration() {
		return numberOfIteration;
	}

	public float getRelevanceSum() {
		computeRelevanceSum();
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

	public Random getSeed() { return seed;}

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
		Task task = new Task(id, numberOfIteration);
		tasks.add(task);
	}

	/**
	 * This method is used to create a given number of tasks and normalizes the relevance of each task so the sum is 1.
	 * It uses createTask().
	 * @param nbOfTasks the number of tasks to create
	 */
	public void createNTasks(int nbOfTasks){
		float temp_sum = 0;

		// create "normal tasks"
		for(int i = 0; i<nbOfTasks; i++){
			createTask(i);
			float random = randomFloatGenerator(this.seed);
			tasks.get(i).getTasksRelevances().getRelevanceArrayList().set(0,random);
			temp_sum+=random;
		}

		// Normalize the relevances so that the sum is 1
		for(int i = 0; i < nbOfTasks; i++){
			tasks.get(i).getTasksRelevances().getRelevanceArrayList().set(0, tasks.get(i).getTasksRelevances().getRelevanceArrayList().get(0) / temp_sum);
		}

		// Duplicate the relevances
		for(int i=0; i<nbOfTasks; i++) {
			float previousRelevance = tasks.get(i).getTaskRelevanceAtIndex(0);
			for(int j=1; j<this.numberOfIteration;j++) {
				tasks.get(i).getTasksRelevances().getRelevanceArrayList().add(j, previousRelevance);
			}
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
		Agent agent = new Agent(id, tasks.size(), this.numberOfIteration);
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
	public void exportRatioToCsv() throws IOException {
		String csvFile = "ratio.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtil.writeLine(writer, Arrays.asList("sep=,"));
		CSVUtil.writeLine(writer, Arrays.asList("Iteration", "Agent ID", "Task ID", "Ratio"), ',', '"');

		for(int i=0; i<numberOfIteration; i++){
			for(int j=0; j<agents.size(); j++){
					for(int l=0; l < total_task_number; l++){
						CSVUtil.writeLine(writer,
								Arrays.asList(
										String.valueOf(i+1),
										String.valueOf(j+1),
										String.valueOf(l+1),
										String.valueOf(agents.get(j).getRatio().get(l))),
								',',
								'"'
						);
					}
			}
		}
		writer.flush();
		writer.close();
	}


	/**
	 * This method is used to export the History values to a .csv file
	 * @throws IOException
	 */
	public void exportRelevanceToCsv() throws IOException {
		String csvFile = "relevance.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtil.writeLine(writer, Arrays.asList("sep=,"));
		CSVUtil.writeLine(writer, Arrays.asList("Iteration", "Task ID", "Task Relevance"), ',', '"');

		for(int i=0; i<numberOfIteration; i++){
					for(int l=0; l < total_task_number; l++){
						CSVUtil.writeLine(writer,
								Arrays.asList(
										String.valueOf(i+1),
										String.valueOf(l+1),
										String.valueOf(history.getRelevances().get(l).get(i))),
								',',
								'"'
						);
					}
			}
		//jacques
		writer.flush();
		writer.close();
	}

	/**
	 * This method is used to export the History values to a .csv file
	 * @throws IOException
	 */
	public void exportTasksToCsv() throws IOException {
		String csvFile = "tasks.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtil.writeLine(writer, Arrays.asList("sep=,"));
		CSVUtil.writeLine(writer, Arrays.asList("Iteration", "Agent ID", "Picked Task ID"), ',', '"');

		for(int i=0; i<numberOfIteration; i++){
			for(int j=0; j<agents.size(); j++){
				CSVUtil.writeLine(writer,
						Arrays.asList(
								String.valueOf(i+1),
								String.valueOf(j+1),
								String.valueOf(agents.get(j).getPickedTasksID().get(i))),
						',',
						'"'
				);
			}
		}
		writer.flush();
		writer.close();
	}
}
