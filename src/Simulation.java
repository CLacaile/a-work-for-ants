import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Simulation {

	// Attributes
	protected int simulation_time;
	protected int numberOfIteration;
	protected int total_agent_number;
	protected int total_task_number;
	protected float relevanceSum;
	protected ArrayList<Agent> agents = new ArrayList<>();
	protected ArrayList<Task> tasks = new ArrayList<>();

	// Constructor
	public Simulation() {
		relevanceSum=1;
		numberOfIteration=1;
	}

	// Getters
	public float getRelevanceSum() {
		return relevanceSum;
	}

	public int getSimulation_time() {
		return simulation_time;
	}

	public int getTotal_agent_number() {
		return total_agent_number;
	}

	public int getTotal_task_number() {
		return total_task_number;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	// Setters
	public void setRelevanceSum(float relevanceSum) {
		this.relevanceSum = relevanceSum;
	}

	public void setSimulation_time(int simulation_time) {
		this.simulation_time = simulation_time;
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
			sum += tasks.get(i).getTaskRelevanceAtIndex(i);
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
	 * @return a float between 0 and 1
	 */
	public static Float randomFloatGenerator() {
		Random generator = new Random();
		return generator.nextFloat();
	}

	/**
	 * This method is used to create a random float number between min and max
	 * @return a float between 0 and 1
	 */
	public static Float randomFloatInRange(Float min, Float max) {
		Random generator = new Random();
		return min + generator.nextFloat() * (max - min);
	}

	/**
	 * This method is used to create a random float number between a min and a max value
	 * @param min the min value
	 * @param max the max value
	 * @return an integer between min and max
	 */
	public static int randomIntInRange(int min, int max) {
		Random generator = new Random();
		return generator.nextInt((max - min) + 1) + min;
	}

	/**
	 * This method is used to export the History values to a .csv file
	 * @param history
	 * @throws IOException
	 */
	public void exportHistoryToCsv(History history) throws IOException {
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
										String.valueOf(j+1),
										String.valueOf(k+1),
										String.valueOf(history.getThresholds().get(j).get(k)),
										String.valueOf(history.getRelevances().get(i).get(0))),
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
