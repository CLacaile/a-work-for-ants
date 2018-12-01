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
        simulation.assignTasks();

        //Prints tasks & relevances
        for(int i =0; i<simulation.getTasks().size();i++){
            System.out.println("Task id : " + simulation.getTasks().get(i).getId() + " Task Relevance at t0 : " + simulation.getTasks().get(i).getTasksRelevances().getRelevanceArrayList().get(0));
        }

        //Prints Agents & thresholds & threshold_decrements per task
        for(int i = 0; i<simulation.getAgents().size();i++){

            for (int j=0; j<simulation.getTasks().size(); j++){
                System.out.println("Agent id : " + simulation.getAgents().get(i).getId() + " Task " + (j+1) +
                                    " threshold at t0 : " + simulation.getAgents().get(i).getThresholds().get(j) +
                                    " threshold_decrement at t0: " + simulation.getAgents().get(i).getThreshold_decrements().get(j));
            }
            System.out.println("Picked task is #"+simulation.getAgents().get(i).getPickedTask().getId());
            System.out.println("Tresholds sum : " + simulation.getAgents().get(i).sumThresholds());
        }

        // Synchronous Simulation launch
        simulation.runSimulation();

        // Export
        try {
            simulation.exportHistoryToCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
