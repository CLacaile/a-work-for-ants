import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int nbOfTasks = 10;
        int nbOfAgents = 10;
        int nbOfMaxIterations = 10;

        Random seed = new Random(1);
        Synchronous simulation = new Synchronous(seed);
        simulation.setNumberOfIteration(nbOfMaxIterations);

        //creation of the Tasks
        simulation.setTotal_task_number(nbOfTasks);
        simulation.createNTasks(nbOfTasks);

        //creation of the Agents
        simulation.setTotal_agent_number(nbOfAgents);
        simulation.createNAgents(nbOfAgents);


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


        //Prints tasks & relevances
        for(Task t : simulation.getTasks()) {
            System.out.println("---------- Task "+t.getId()+" ----------");
            for(int i= 0; i<nbOfMaxIterations; i++) {
                System.out.print("R at t"+i+" : " + t.getTasksRelevances().getRelevanceArrayList().get(i)+"\t\t");
            }
            System.out.println(" ");

        }
        System.out.println("Relevance sum : " + simulation.getRelevanceSum());

        // History mgmt
        simulation.getHistory().fillThresholds(simulation.getAgents());
        simulation.getHistory().fillRelevance(simulation.getTasks());

        // Export
        try {
            simulation.exportHistoryToCsv();
            simulation.exportRelevanceToCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
