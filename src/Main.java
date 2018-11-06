public class Main {
    
    public static void main(String[] args) {

        System.out.println("Début du test");
        int nbTasks = 3;
        Agent agent1 = new Agent(1, nbTasks);
        System.out.println("Id de l'agent: " + agent1.getId());
        System.out.println("Seuils des taches");
        for(int i=0; i<nbTasks; i++) {
            System.out.println("Tache " + i + " : " + " " + agent1.getThreshold(i));
        }
        agent1.getThreshold(1);
        System.out.println("Fin du test");
    }
}
