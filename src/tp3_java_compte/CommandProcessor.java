package tp3_java_compte;

public class CommandProcessor implements Runnable {
    private String productType;
    private int numberOfIterations;

    public CommandProcessor(String productType, int numberOfIterations) {
        this.productType = productType;
        this.numberOfIterations = numberOfIterations;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        System.out.println("Traitement de commandes pour le produit " + productType + " (" + Thread.currentThread().getName() + ")");
        
        for (int i = 1; i <= numberOfIterations; i++) {
            // Simuler le traitement de la commande
            System.out.println("Commande " + i + " pour le produit " + productType + " traitée.");

            // Ici, vous pouvez ajouter la logique de traitement simulé pour le produit A
            // ou le produit B en fonction de la valeur de productType.
            // Par exemple, effectuer des calculs, des mises à jour de stock, etc.
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Temps pris par le thread " + Thread.currentThread().getName() + ": " + elapsedTime + " ms");
    }

    public static void main(String[] args) {
        // Créer deux instances de CommandProcessor, une pour chaque type de produit.
        CommandProcessor productAProcessor = new CommandProcessor("Produit A", 5); // Exemple : 5 itérations
        CommandProcessor productBProcessor = new CommandProcessor("Produit B", 3); // Exemple : 3 itérations

        // Créer des threads pour exécuter le traitement de commandes en parallèle.
        Thread threadProductA = new Thread(productAProcessor);
        Thread threadProductB = new Thread(productBProcessor);

        // Affecter des noms distincts aux threads.
        threadProductA.setName("Thread du Produit A");
        threadProductB.setName("Thread du Produit B");

        // Définir les priorités des threads.
        threadProductA.setPriority(Thread.MAX_PRIORITY); // Priorité maximale (10)
        threadProductB.setPriority(Thread.MIN_PRIORITY); // Priorité minimale (1)

        // Démarrer les threads.
        threadProductA.start();
        threadProductB.start();

        // Attendre la fin des threads.
        try {
            threadProductA.join();
            threadProductB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Tous les traitements de commandes sont terminés.");
    }
}

