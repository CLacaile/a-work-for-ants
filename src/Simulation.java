import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Simulation {

	// Attributes
	private int simulation_time;
	private int numberOfIteration;
	private int total_agent_number;
	private int total_task_number;
	private float relevanceSum;
	private ArrayList<Agent> agents = new ArrayList<>();
	private ArrayList<Task> tasks = new ArrayList<>();

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
	public void createTask(int id){
		Task task = new Task(id, relevanceSum);
		tasks.add(task);
		relevanceSum-=task.getRelevance().getRelevance().get(0);
	}

	public void createNTasks(int nbOfTasks){
		for(int i = 0; i<nbOfTasks; i++){
			createTask(i+1);
		}
	}

	public void createAgent(int id){
		Agent agent = new Agent(id, tasks.size());
		agents.add(agent);
	}

	public void createNAgents(int nbOfAgents){
		for(int i = 0; i<nbOfAgents; i++){
			createAgent(i+1);
		}
	}

	public static Float randomFloatGenerator() {
		Random generator = new Random();

		return generator.nextFloat();
	}

	public static int randomIntInRange(int min, int max) {
		Random generator = new Random();
		return generator.nextInt((max - min) + 1) + min;
	}

	public void exportHistoryToCsv(History history) throws IOException {
		String csvFile = "history.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtil.writeLine(writer, Arrays.asList("sep=,"));
		CSVUtil.writeLine(writer, Arrays.asList("Iteration", "Agent ID", "Task ID", "Thresholds", "Task Relevance"), ',', '"');

		for(int i=0; i<numberOfIteration; i++){
			for(int j=0; j<agents.size(); j++){
				for(int k=0; k<total_agent_number; k++){
					for(int l=0; i < total_task_number; l++){
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
