import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int i, time_ind = 0, pop_ind = 0;
        int nberror = 0;
        // Tableau dynamique.
        BinomialHeap<Integer> a = new BinomialHeap<Integer>();


        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        boolean memory_allocation = true;


        Random rd = new Random(11500697);

        for (i = 0; i < 1000000; i++) {
            before = System.nanoTime();
            try {
                a.insert(i);
            } catch (Exception e) {
                e.printStackTrace();
                nberror += 1;
            }
            after = System.nanoTime();

            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            //memory_analysis.append(a.capacity() - a.size());
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("dynamic_array_time_binomial_increase.plot");
        copy_analysis.save_values("dynamic_array_copy_binomial_increase.plot");
        memory_analysis.save_values("dynamic_array_memory_binomial_increase.plot");


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        a = new BinomialHeap<Integer>();

        time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        memory_analysis = new Analyzer();
        // Booléen permettant de savoir si une allocation a été effectuée.

        for (i = 0; i < 1000000; i++) {
            before = System.nanoTime();
            try {
                a.insert(1000000 - i);
            } catch (Exception e) {
                e.printStackTrace();
                nberror += 1;
            }
            after = System.nanoTime();
            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            //memory_analysis.append(a.capacity() - a.size());
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("dynamic_array_time_binomial_decrease.plot");
        copy_analysis.save_values("dynamic_array_copy_binomial_decrease.plot");
        memory_analysis.save_values("dynamic_array_memory_binomial_decrease.plot");


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        a = new BinomialHeap<Integer>();

        time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        memory_analysis = new Analyzer();
        // Booléen permettant de savoir si une allocation a été effectuée.
        Analyzer value_analysis = new Analyzer();
        // Analyse des valeurs choisis


        for (i = 0; i < 1000000; i++) {
            int value = Math.abs(rd.nextInt(1000000));
            before = System.nanoTime();
            try {
                a.insert(value);
            } catch (Exception e) {
                e.printStackTrace();
                nberror += 1;
            }
            after = System.nanoTime();
            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            //memory_analysis.append(a.capacity() - a.size());
            // Enregistrement de la valeur
            value_analysis.append(value);
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("dynamic_array_time_binomial_random.plot");
        copy_analysis.save_values("dynamic_array_copy_binomial_random.plot");
        memory_analysis.save_values("dynamic_array_memory_binomial_random.plot");
        value_analysis.save_values("dynamic_array_value_binomial_random.plot");


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        a = new BinomialHeap<Integer>();

        time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        copy_analysis = new Analyzer();
        // Analyse de l'espace mémoire inutilisé.
        memory_analysis = new Analyzer();
        // Booléen permettant de savoir si une allocation a été effectuée.
        value_analysis = new Analyzer();

        Analyzer choice_analysis = new Analyzer();

        for (i = 0; i < 1000000; i++) {
            int value = Math.abs(rd.nextInt(1000000));
            Object o;
            boolean randomValue = rd.nextBoolean();
            int choice = 1;
            if (randomValue) {
                before = System.nanoTime();
                try {
                    a.insert(value);// Modifier la fonction insert pour avoir retourné un boolean et extractMin
                } catch (Exception e) {
                    e.printStackTrace();
                    nberror += 1;
                }
                after = System.nanoTime();
                // Ajout d'un élément et mesure du temps pris par l'opération.
            } else {
                choice = 0;
                before = System.nanoTime();
                o = a.extractMin();
                after = System.nanoTime();
                if (o == null) {
                    value = 0;
                } else {
                    value = (Integer) o;
                }
                // Suppression d'un élément et mesure du temps pris par l'opération.
            }
            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append((memory_allocation == true) ? i : 1);
            // Enregistrement de l'espace mémoire non-utilisé.
            //memory_analysis.append(a.capacity() - a.size());
            value_analysis.append(value);
            choice_analysis.append(choice);
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : " + time_analysis.get_total_cost());
        System.err.println("Average cost : " + time_analysis.get_average_cost());
        System.err.println("Variance :" + time_analysis.get_variance());
        System.err.println("Standard deviation :" + time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("dynamic_array_time_binomial_full.plot");
        copy_analysis.save_values("dynamic_array_copy_binomial_full.plot");
        memory_analysis.save_values("dynamic_array_memory_binomial_full.plot");
        value_analysis.save_values("dynamic_array_value_binomial_full.plot");
        choice_analysis.save_values("dynamic_array_choice_binomial_full.plot");

        System.out.println("nberro ="+nberror);
    }

}

