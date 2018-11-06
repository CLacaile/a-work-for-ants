import java.util.List;

public abstract class Simulation {

	// Attributes
	private int simulation_time;

	private int total_agent_number;

	private int total_task_number;

	private List<Agent> agents;

	private List<Task> tasks;

	// Constructor
	/*public Simulation() {

	}*/

	// Getters
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
	public void setSimulation_time(int simulation_time) {
		this.simulation_time = simulation_time;
	}

	public void setTotal_agent_number(int total_agent_number) {
		this.total_agent_number = total_agent_number;
	}

	public void setTotal_task_number(int total_task_number) {
		this.total_task_number = total_task_number;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	// Methods
	public void export_CSV() {

	}
}
