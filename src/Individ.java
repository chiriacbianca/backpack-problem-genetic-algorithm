

import java.util.Arrays;

public class Individ {
    public int[] chromosomeArray;
    public static int lenght;
    public int fitness;
    public int weightOfSolution;
    public static int maxCapacity;
    public static ItemsInBackPack[] items;

    public Individ(int lenght, int maxCapacity, ItemsInBackPack[] items) {
        //lungimea unui cromozom este numarul de obiecte citit din fisier
        //ietms este sirul de obiecte citit din fisier, fiecare avand o greutate si o valoare
        //maxCapacity este capacitatea maxima a ghiozdanului
        this.maxCapacity = maxCapacity;
        this.lenght = lenght;
        this.items = items;

    }

    public Individ(int[] individ) {
        this.chromosomeArray = individ;
        int currentFitness=0;
        int currentWeight=0;
        for (int i = 0; i < individ.length; i++) {
            if(chromosomeArray[i]==1) {
                currentFitness += items[i].value;
                currentWeight += items[i].weight;
            }
        }
        if (currentWeight > maxCapacity) {
            fitness = 0;
            weightOfSolution=currentWeight;
        }
        else{
            fitness=currentFitness;
            weightOfSolution=currentWeight;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individ individ = (Individ) o;
        return Arrays.equals(chromosomeArray, individ.chromosomeArray);
    }

    @Override
    public String toString() {
        return "Individ{" +
                "chromosomeArray=" + Arrays.toString(chromosomeArray) +
                ", fitness=" + fitness +
                ", weightOfSolution=" + weightOfSolution +
                '}';
    }
}