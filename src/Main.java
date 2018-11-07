import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        int nbOfTasks = 2;
        int nbOfAgents = 2;


        Simulation simulation = new Simulation();

        //creation of the Tasks
        simulation.setTotal_task_number(nbOfTasks);
        simulation.createNTasks(nbOfTasks);

        //creation of the Agents
        simulation.setTotal_agent_number(nbOfAgents);
        simulation.createNAgents(nbOfAgents);

        //Prints tasks & relevances
        for(int i =0; i<simulation.getTasks().size();i++){
            System.out.println("Task id : " + simulation.getTasks().get(i).getId() + " Task Relevance at t0 : " + simulation.getTasks().get(i).getRelevance().getRelevance().get(0));
        }

        //Prints Agents & thresholds per task
        for(int i = 0; i<simulation.getAgents().size();i++){
            for (int j=0; j<simulation.getTasks().size(); j++){
                System.out.println("Agent id : " + simulation.getAgents().get(i).getId() + " Task " + (j+1) + " threshold at t0 : " + simulation.getAgents().get(i).getThresholds().get(j));
            }
        }

        //create and fill history class
        History history = new History();
        history.fillRelevance(simulation.getTasks());
        history.fillThresholds(simulation.getAgents());


        try {
            simulation.exportHistoryToCsv(history);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
