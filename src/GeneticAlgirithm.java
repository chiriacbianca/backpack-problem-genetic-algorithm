import java.util.Random;

public class GeneticAlgirithm {
    public Individ[] initialPopulation;
    public Individ[] firstGeneration;
    public static final int populationLength=10;
    public static final double mutationRate = 0.05;


    public GeneticAlgirithm (){

        initialPopulation=new Individ[populationLength];

        Random rand= new Random(System.currentTimeMillis());
        for(int i=0;i<populationLength;i++){
            int[] chromosomeArray=new int[Individ.lenght];
            int currentWeight=0;

            do{
                int currentItem = rand.nextInt(Individ.lenght);
                System.out.println("Nr random: " + currentItem);
                if (currentWeight + Individ.items[currentItem].weight > Individ.maxCapacity)
                    break;
                else {
                    chromosomeArray[currentItem] = 1;
                    currentWeight += Individ.items[currentItem].weight;
                }

            } while (currentWeight<Individ.maxCapacity);
            initialPopulation[i]=new Individ(chromosomeArray);
        }
        for (int i=0;i<populationLength;i++) {
            System.out.println((i+1)+" "+initialPopulation[i]);
        }
        firstGeneration=new Individ[populationLength];
        firstGeneration=initialPopulation;
        System.out.println();
        System.out.println("Approximate solution: "+firstGeneration[getIndexOfBestFit(createNewPopulation())]);
    }

    public double totalFittness(Individ[] population){
        int total=0;
        for (Individ i:population)
            total +=i.fitness;
        return (double) total;
    }

    public double[] cumulativeProbability(Individ[] population){
        double[] cumulativeProbability= new double[population.length];
        double total=totalFittness(population);
        double cumulatedProbability=0;
        for (int i=0;i<population.length;i++){
            cumulatedProbability+=(double)population[i].fitness;
            cumulativeProbability[i]=cumulatedProbability/total;
        }
        return cumulativeProbability;
    }

    public int getIndexOfLeastFit(Individ[] population){
        int minimum=population[0].fitness;
        int index=0;
        for (int i=0;i<population.length;i++){
            if (minimum>population[i].fitness){
                minimum=population[i].fitness;
                index=i;
            }
        }
        return index;
    }

    public int getIndexOfBestFit(Individ[] population){
        int max=population[0].fitness;
        int index=0;
        for (int i=0;i<population.length;i++){
            if (max<population[i].fitness){
                max=population[i].fitness;
                index=i;
            }
        }
        return index;
    }


    public Individ selectParent(){
        double[] probableParents= cumulativeProbability(firstGeneration);
        //number1 este un numar extras random intre 0 si 1
        double number1 = Math.random();
        if (number1 < probableParents[0])
            return firstGeneration[0];

        else {
            for (int i = 0; i < firstGeneration.length-1; i++) {
                if (number1>probableParents[i]&&number1<probableParents[i+1]){
                    return firstGeneration[i+1];
                }
            }
        }
        return firstGeneration[firstGeneration.length-1];
    }

    public Individ[] selection(){
        Individ[] parents=new Individ[2];
        parents[0]=selectParent();
        do {
            parents[1]=selectParent();
        } while (parents[0]==parents[1]);
        return  parents;
    }

    public Individ[] crossover(Individ[] parents){
        Individ[] children=new Individ[2];
        int[] child1=new int[Individ.lenght];
        int[] child2=new int[Individ.lenght];
        int pivot=Individ.lenght/2;
        for (int i=0;i<Individ.lenght;i++){
            if (i<=pivot){
                child1[i]=parents[0].chromosomeArray[i];
                child2[i]=parents[1].chromosomeArray[i];
            }
            else{
                child1[i]=parents[1].chromosomeArray[i];
                child2[i]=parents[0].chromosomeArray[i];
            }
        }
        child1=mutation(child1);
        child2=mutation(child2);
        children[0]=new Individ(child1);
        children[1]=new Individ(child2);
        return children;
    }

    public int[] mutation(int[] individArray){
        double randomNumber=Math.random();
        if (randomNumber<=mutationRate){
            System.out.println("Mutation:Yes");
            Random rand= new Random(System.currentTimeMillis());
            int chromosomeToBeReplaced=rand.nextInt(individArray.length);
            if (individArray[chromosomeToBeReplaced]==0)
                individArray[chromosomeToBeReplaced]=1;
            else
                individArray[chromosomeToBeReplaced]=0;
        }
        return individArray;
    }

    public Individ[] createNewPopulation() {
       for (int j=0;j<populationLength;j++) {
           for (int i = 0; i < populationLength / 2; i++) {
               //la fiecare 5 incrucisari rezulta 10 copii (5x2 din fiecare incrucisare, adica inca o data populatia)
               System.out.println("aici");
               Individ[] parents = selection();
               for (int k = 0; k < parents.length; k++) {
                   System.out.println("Parent: " + parents[k]);
               }
               Individ[] children = crossover(parents);
               for (int k = 0; k < children.length; k++) {
                   System.out.println("Child: " + children[k]);
               }
               if (children[0].fitness > firstGeneration[getIndexOfLeastFit(firstGeneration)].fitness)
                   firstGeneration[getIndexOfLeastFit(firstGeneration)] = children[0];
               if (children[1].fitness > firstGeneration[getIndexOfLeastFit(firstGeneration)].fitness)
                   firstGeneration[getIndexOfLeastFit(firstGeneration)] = children[1];
               System.out.println("sau aici");
           }
           for (int i=0;i<populationLength;i++){
               System.out.println((i+1)+" "+firstGeneration[i]);
           }
       }
        return firstGeneration;
    }
}

