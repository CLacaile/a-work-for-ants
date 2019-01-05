import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        int nbOfTasks = 10;
        int nbOfAgents = 10;
        int nbOfMaxIterations = 10;

        Synchronous simulation = new Synchronous();
        simulation.setNumberOfIteration(nbOfMaxIterations);

        //creation of the Tasks
        simulation.setTotal_task_number(nbOfTasks);
        simulation.createNTasks(nbOfTasks);

        //creation of the Agents
        simulation.setTotal_agent_number(nbOfAgents);
        simulation.createNAgents(nbOfAgents);

        //Prints tasks & relevances
        for(Task t : simulation.getTasks()) {
            System.out.println("Task id : " + t.getId() + " Task Relevance at t0 : " + t.getTasksRelevances().getRelevanceArrayList().get(0));
        }
        System.out.println("Relevance sum : " + simulation.getRelevanceSum());

        //Prints Agents & thresholds & threshold_decrements per task
        for(Agent a : simulation.getAgents()) {
            for (Task t : simulation.getTasks()) {
                System.out.println("Agent id : " + a.getId() + " Task " + t.getId() +
                        " threshold at t0 : " + a.getThresholds().get(t.getId()) +
                        " threshold_decrement at t0: " + a.getThreshold_decrements().get(t.getId()));
            }
            System.out.println("Tresholds sum : " + a.sumThresholds());
        }

        // Synchronous Simulation launch
        simulation.runSimulation();

        // History mgmt
        simulation.getHistory().fillThresholds(simulation.getAgents());
        simulation.getHistory().fillRelevance(simulation.getTasks());

        // Export
        try {
            simulation.exportHistoryToCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
