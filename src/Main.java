import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int nbOfTasks = 10;
        int nbOfAgents = 7;
        int nbOfMaxIterations = 25;

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
                        " threshold at t0 : " + a.getThresholds().get(t.getId()));
            }
            System.out.println("Tresholds sum : " + a.sumThresholds());
        }

        // Synchronous Simulation launch
        simulation.runSimulation();

        System.out.println("debug");
        //Prints tasks & relevances
        for(Task t : simulation.getTasks()) {
            System.out.println("---------- Task "+t.getId()+" ----------");
            for(int i= 0; i<nbOfMaxIterations; i++) {
                System.out.print("R at t"+i+" : " + t.getTasksRelevances().getRelevanceArrayList().get(i)+"\t\t");
            }
            System.out.println(" ");

        }
        System.out.println("Relevance sum : " + simulation.getRelevanceSum());

        for(Agent a : simulation.getAgents()) {
            System.out.println("---------- Agent "+a.getId()+" ---------");
            for(int i=0; i<nbOfMaxIterations; i++) {
                System.out.print(a.getPickedTasksID().get(i)+"\t");
            }
            System.out.println("");
        }

        // History mgmt
        simulation.getHistory().fillThresholds(simulation.getAgents());
        simulation.getHistory().fillRelevance(simulation.getTasks());

        // Export
        try {
            simulation.exportRatioToCsv();
            simulation.exportRelevanceToCsv();
            simulation.exportTasksToCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
