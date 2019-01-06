import DataGenerator.PeopleGenerator;
import PairingAlgorithm.PeopleGraph;
import PairingAlgorithm.PeopleGraphCONSTRUCT;

import java.util.Random;



public class Main {

    public static void main(String[] args) {
	// write your code here
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
        for(int i = 0 ; i < peopleToGenerate; i++){
            System.out.print(i + " ");
            peopleGenerator.getMalePeople().getPeopleVector().get(i).writePerson();

        }
        for(int i = 0 ; i < peopleToGenerate; i++){
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
