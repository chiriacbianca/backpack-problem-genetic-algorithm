import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\bianca info\\IdeaProjects\\com.backpack\\src\\dateintrare.txt"));

        int numberOfObject=input.nextInt();
        ItemsInBackPack[] items=new ItemsInBackPack[numberOfObject];

        for (int i=0;i<numberOfObject;i++){

            items[i]=new ItemsInBackPack(input.nextInt(), input.nextInt());
        }
        for (int i=0;i<numberOfObject;i++){
            System.out.println(items[i]);
        }

        int maxCapacity=20;
        Individ initializeIndivid= new Individ(numberOfObject, maxCapacity, items);

        GeneticAlgirithm getApproximateSolution=new GeneticAlgirithm();
    }
}
