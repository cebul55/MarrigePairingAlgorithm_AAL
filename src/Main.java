import DataGenerator.PeopleGenerator;
import PairingAlgorithm.PeopleGraph;
import PairingAlgorithm.PeopleGraphCONSTRUCT;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Random;



public class Main {

    public static void main(String[] args) {
        // write your code here
        if (true) {
           try{
                PeopleGraph peopleGraph1 = new PeopleGraph("resourceTest/Test1.txt");
                peopleGraph1.writePeopleGraph();
                peopleGraph1.checkIfEverybodyHasEdge();
                System.out.println(peopleGraph1.getDifference());


                int[] pairs = peopleGraph1.pairingAlgorithm();
                for(int i = 0; i< pairs.length; i++){
                    System.out.println("Pair: " + i + " " + pairs[i]);
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.print("File not found");
            }
        }
        else {
            final int peopleToGenerate = 5;
            System.out.println("HELLO AAL");

            // create random object
            Random randomno = new Random();
            double rand = randomno.nextGaussian();
            // check next Gaussian value
            System.out.println("Next Gaussian value: " + rand);
            System.out.println("Next Gaussian HEIGHT value: " + (rand * 20 + 180));

            PeopleGenerator peopleGenerator = new PeopleGenerator(peopleToGenerate);
            peopleGenerator.sortByHeight();
            for (int i = 0; i < peopleToGenerate; i++) {
                System.out.print(i + " ");
                peopleGenerator.getMalePeople().getPeopleVector().get(i).writePerson();

            }
            for (int i = 0; i < peopleToGenerate; i++) {
                System.out.print(i + " ");
                peopleGenerator.getFemalePeople().getPeopleVector().get(i).writePerson();

            }

            //System.out.println("PEOPLE GRAPH: \n");
            PeopleGraphCONSTRUCT graph = new PeopleGraphCONSTRUCT(peopleGenerator);
            //graph.writePeopleGraph();

            System.out.println("Check if all man has at least 1 possible woman to pair with: " + graph.checkIfMenHasVector());
            System.out.println("Difference " + graph.getDifferenceOfSums());

            System.out.println("********\"********\"********\"********");

            PeopleGraph peopleGraph = new PeopleGraph(peopleGenerator.getMalePeople(), peopleGenerator.getFemalePeople());
            peopleGraph.writePeopleGraph();
            System.out.println("Check if all man has at least 1 possible woman to pair with: " + peopleGraph.checkIfEverybodyHasEdge());

        }

    }
}
