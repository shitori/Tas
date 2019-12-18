import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int i, time_ind = 0, pop_ind = 0;
        // Tableau dynamique.
        BinaryHeap<Integer> a = new BinaryHeap<Integer>(); // Changer
        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        boolean memory_allocation;

        Random rd = new Random(11500697); // Création de la suite pseudo-aléatoire à partir d'une graine.

        for (i = 0; i < 1000000; i++) {
            before = System.nanoTime();
            memory_allocation = a.insert(i);// Modifier la fonction insert pour avoir retourné un boolean et extractMin
            after = System.nanoTime();

            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            memory_analysis.append(a.capacity() - a.size());
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plots/dynamic_array_time_binary_increase.plot");
        copy_analysis.save_values("../plots/dynamic_array_copy_binary_increase.plot");
        memory_analysis.save_values("../plots/dynamic_array_memory_binary_increase.plot");


        ///////////////////////////////////////////////////////////////////////////////////////////////////


        time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        memory_analysis = new Analyzer();
        // Booléen permettant de savoir si une allocation a été effectuée.

        for (i = 0; i < 1000000; i++) {
            before = System.nanoTime();
            memory_allocation = a.insert(1000000 - i);// Modifier la fonction insert pour avoir retourné un boolean et extractMin
            after = System.nanoTime();
            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            memory_analysis.append(a.capacity() - a.size());
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plots/dynamic_array_time_binary_decrease.plot");
        copy_analysis.save_values("../plots/dynamic_array_copy_binary_decrease.plot");
        memory_analysis.save_values("../plots/dynamic_array_memory_binary_decrease.plot");


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        memory_analysis = new Analyzer();
        // Booléen permettant de savoir si une allocation a été effectuée.

        for (i = 0; i < 1000000; i++) {
            int value = rd.nextInt();
            before = System.nanoTime();
            memory_allocation = a.insert(value);// Modifier la fonction insert pour avoir retourné un boolean et extractMin
            after = System.nanoTime();
            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            memory_analysis.append(a.capacity() - a.size());
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plots/dynamic_array_time_binary_random.plot");
        copy_analysis.save_values("../plots/dynamic_array_copy_binary_random.plot");
        memory_analysis.save_values("../plots/dynamic_array_memory_binary_random.plot");


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        memory_analysis = new Analyzer();
        // Booléen permettant de savoir si une allocation a été effectuée.

        for (i = 0; i < 1000000; i++) {
            int value = rd.nextInt();
            boolean randomValue = rd.nextBoolean();
            if (randomValue) {
                before = System.nanoTime();
                memory_allocation = a.insert(value);// Modifier la fonction insert pour avoir retourné un boolean et extractMin
                after = System.nanoTime();
                // Ajout d'un élément et mesure du temps pris par l'opération.
            } else {
                before = System.nanoTime();
                memory_allocation = a.extractMin();
                after = System.nanoTime();
                // Suppression d'un élément et mesure du temps pris par l'opération.
            }
            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            memory_analysis.append(a.capacity() - a.size());
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plots/dynamic_array_time_binary_full.plot");
        copy_analysis.save_values("../plots/dynamic_array_copy_binary_full.plot");
        memory_analysis.save_values("../plots/dynamic_array_memory_binary_full.plot");
    }

}

