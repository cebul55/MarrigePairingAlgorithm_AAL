import DataGenerator.PeopleGenerator;
import PairingAlgorithm.PeopleGraph;
import PairingAlgorithm.PeopleGraphCONSTRUCT;
import PairingAlgorithm.PeopleNode;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.StringBuilder.*;


public class Main {

    public static void main(String[] args) {
        // write your code here
        String workingDirectory =  System.getProperty("user.dir");
        PeopleGraph peopleGraph = null;
        int peopleToGenerate = 0;
        long elapsedTime = 0;
        if(args.length == 2){
            if(args[0].equals("f")){
                //jako 2 argument odczytujemy sciezke do pliku
                String fileDir = args[1];
                try {
                    peopleGraph = new PeopleGraph(workingDirectory + "/" + fileDir);
                    peopleToGenerate = peopleGraph.getNodeCount();
                    graphOperations(peopleGraph);
                } catch (FileNotFoundException e) {
                    //e.printStackTrace();
                    System.out.println("Nie udalo sie otworzyc pliku. Sprawdz podana sciezke i sprobuj ponownie.");
                    return;
                }
            }

            else if(args[0].equals("g")){
                //jako 2 argument podajemy liczbe wierzcholkow danej plci do wygenerowania -> rozmiar problemu to 2* podana liczba
                peopleToGenerate = new Integer(args[1]);
                PeopleGenerator peopleGenerator = new PeopleGenerator(peopleToGenerate);
                peopleGraph = new PeopleGraph(peopleGenerator.getMalePeople(), peopleGenerator.getFemalePeople());
                graphOperations(peopleGraph);
            }

        }
        else if(args.length == 1){
            peopleToGenerate = new Integer(args[0]);
            PeopleGenerator peopleGenerator = new PeopleGenerator(peopleToGenerate);
            peopleGraph = new PeopleGraph(peopleGenerator.getMalePeople(), peopleGenerator.getFemalePeople());
            graphOperations(peopleGraph);
        }
        else if(args.length == 5 && args[0].equals("h")){
            int number = new Integer(args[1]);
            int step = new Integer(args[2]);
            step = step / 2; // dzielimy przez 2 poniewaz generator generuje 2*n wierzcholkow
            int numberOfIterations = new Integer(args[3]);
            int numberOfProblems = new Integer(args[4]);

            String fileContent = "";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(workingDirectory + "/TIMERESULTS.csv"))) {

                for(int i = 0; i < numberOfProblems ; i++){
                    for(int j = 0; j < numberOfIterations; j++){
                        PeopleGenerator peopleGenerator = new PeopleGenerator(number);
                        peopleGraph = new PeopleGraph(peopleGenerator.getMalePeople(), peopleGenerator.getFemalePeople());
                        elapsedTime = graphOperationsNoOut(peopleGraph);
                        //System.out.println(i+"." + j +"Wielkosc: " + peopleGraph.getNodeCount() + " elapsed time: " + elapsedTime + " (nanoseconds).");
                        //fileContent = (i+"." + j +" Wielkosc: " + peopleGraph.getNodeCount() + " elapsed time: " + elapsedTime + " (nanoseconds).\n");
                        fileContent = (peopleGraph.getNodeCount() + ";" + elapsedTime + "\n");
                        writer.write(fileContent);
                    }
                    number += step;
                }

                writer.close();
                System.out.println("Wykonywanie zakonczone, wyniki zapisano do pliku TMIERRESULTS.csv");
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Nie udalo sie utworzyc pliku. Sprobuj jeszcze raz");
            }

        }
        else {
            System.out.println("Nieprawidlowa ilosc parametrow wywolania programu.");
            System.out.println("Dozwolone wywolania programu: ");
            System.out.println("java -jar MarrigePairingAlgorithm_AAL.jar put");
            System.out.println("java -jar MarrigePairingAlgorithm_AAL.jar [numberOfPeopleToGenerate]");
            System.out.println("java -jar MarrigePairingAlgorithm_AAL.jar g [numberOfPeopleToGenerate]");
            System.out.println("java -jar MarrigePairingAlgorithm_AAL.jar f [filename]");
            System.out.println("java -jar MarrigePairingAlgorithm_AAL.jar h [first number] [step] [number iterations] [iterations for instance]");
        }

    }

    static void graphOperations(PeopleGraph peopleGraph){
        String workingDirectory =  System.getProperty("user.dir");

        if(!peopleGraph.checkIfEverybodyHasEdge()){
            System.out.println("Problem nie moze byc rozwiazany, istnieje co najmniej jedna osoba, ktora nie moze zostac polaczona z inna.");
            return;
        }

        int[] result = peopleGraph.pairingAlgorithm();
        if(peopleGraph.checkPairingAlgorithmResult()) {
            System.out.println("------SUKCES.-----\nRoznica sum wysokosci i sum obwodow: " + peopleGraph.getDifference());
            for (int i = 0; i < result.length; i++) {
                System.out.println("Pair: " + i + " - " + result[i]);
            }
            System.out.println("Wielkosc problemu: " + result.length + ", elapsed time: " + peopleGraph.getElapsedTime() + "(nanoseconds).");
            //peopleGraph.writePairsToFile(workingDirectory + "/PAIRS.csv");
        }
        else{
            System.out.println("Wielkosc problemu: " + result.length + ", elapsed time: " + peopleGraph.getElapsedTime() + "(nanoseconds).");
            System.out.println("Nie udalo dopasowac sie wszystkich osob.");
        }
        System.out.println(Duration.ofNanos ( peopleGraph.getElapsedTime() ));
    }

    static long graphOperationsNoOut(PeopleGraph peopleGraph){
        if(!peopleGraph.checkIfEverybodyHasEdge()){
            System.out.println("Problem nie moze byc rozwiazany, istnieje co najmniej jedna osoba, ktora nie moze zostac polaczona z inna.");
            return -1;
        }
        peopleGraph.pairingAlgorithm();
        return peopleGraph.getElapsedTime();
    }
}
