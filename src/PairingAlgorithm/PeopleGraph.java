package PairingAlgorithm;

import DataGenerator.PeopleVector;
import DataGenerator.Person;
import DataGenerator.Sex;
import Graph.Graph;
import Graph.Edge;
import Graph.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

public class PeopleGraph extends Graph {

    private int difference = 0;

    public PeopleGraph(PeopleVector malePeople, PeopleVector femalePeople){
        super();
        if(!this.constructPeopleGraph(malePeople, femalePeople)){
            this.nodeList = new LinkedList<>();
            this.nodeCount = 0;
            this.difference = 0;
            System.out.print("Number of men is different from number of woman.\nThey can not be matched.");
        }
    }

    /**
     * Konstruktor grafu przyjmujacy na wejsciu plik z zapisanymi danymi osob
     * @param filename nazwa pliku do otwarcia
     */
    public PeopleGraph(String filename) throws FileNotFoundException {
        super();

        Scanner fileScanner = new Scanner(new File(filename));

        PeopleVector male = new PeopleVector();
        PeopleVector female = new PeopleVector();
        
        String line;
        String[] lineSplit;
        while(fileScanner.hasNext()){
            line = fileScanner.nextLine();
            lineSplit = line.split(";");
            if(lineSplit.length >= 3) {
                Person tmpPerson = new Person(lineSplit[0], lineSplit[1], lineSplit[2]);
                if (tmpPerson.getSex() == Sex.MALE) {
                    male.addPerson(tmpPerson);
                } else {
                    female.addPerson(tmpPerson);
                }
            }
        }

        if(!this.constructPeopleGraph(male, female)){
            this.nodeList = new LinkedList<>();
            this.nodeCount = 0;
            this.difference = 0;
            System.out.print("Number of men is different from number of woman.\nThey can not be matched.");
        }
    }

    /**
     * funkcja konstruujaca graf dwudzielny, z kobiet i mezczyzn. Krawedze nigdy nie biegna pomiedzy
     *  obiektami tej samej plci
     *
     * @param malePeople wektor zawierajacy obiekty klasy People ( mezczyzni )
     * @param femalePeople wektor zawierajacy obiekty klasy People ( kobiety )
     */
    private boolean constructPeopleGraph(PeopleVector malePeople, PeopleVector femalePeople){
        malePeople.sortPeopleVectorByHeight();
        femalePeople.sortPeopleVectorByHeight();

        if( malePeople.getNumberOfPeople() != femalePeople.getNumberOfPeople() ){
            return false;
        }

        this.difference = malePeople.getSumHeights() + malePeople.getSumHeadGirths()
                        - femalePeople.getSumHeadGirths() - femalePeople.getSumHeights();

        Vector<Person> tmpMale = malePeople.getPeopleVector();
        Vector<Person> tmpFemale = femalePeople.getPeopleVector();

        //utworzenie listy wierzcholkow
        // 0 .. n-1 wierzcholki mezczyzn
        // n .. 2*n -1 wierzcholki kobiet
        for (int i = 0 ; i < malePeople.getNumberOfPeople(); i++){
            nodeList.add(new PeopleNode(i, tmpMale.get(i)));
        }
        for(int i = femalePeople.getNumberOfPeople(); i < 2* femalePeople.getNumberOfPeople(); i++){
            nodeList.add(new PeopleNode( i, tmpFemale.get(i - femalePeople.getNumberOfPeople())));
        }
        nodeCount = malePeople.getNumberOfPeople() * 2 ;

        for(int i = 0 ; i < malePeople.getNumberOfPeople(); i++){
            int tmpMaleHeight = tmpMale.get(i).getHeight();
            int tmpMaleHeadGirth = tmpMale.get(i).getHeadGirth();

            int j = 0;
            int tmpFemaleHeight = tmpFemale.get(j).getHeight();
            int tmpFemaleHeadGirth = tmpFemale.get(j).getHeadGirth();

            while(tmpMaleHeight > tmpFemaleHeight && j < femalePeople.getNumberOfPeople() ){
                tmpFemaleHeight = tmpFemale.get(j).getHeight();
                tmpFemaleHeadGirth = tmpFemale.get(j).getHeadGirth();

                if( tmpMaleHeight > tmpFemaleHeight && tmpMaleHeadGirth > tmpFemaleHeadGirth){
                    //utworz krawedz
                    //addEdge(i, tmpMale.get(i), malePeople.getNumberOfPeople() + j, tmpFemale.get(j));
                    addEdge(i, j + femalePeople.getNumberOfPeople());
                    addEdge(j + femalePeople.getNumberOfPeople(),i );
                }
                j++;
            }
        }
        return true;
    }

    /**
     *     funkcja dodaje dodaje krawedz pomiedzy mezczyzna i kobieta
     */
    public void addEdge(int begin, int end){
        PeopleNode p1 = (PeopleNode) this.getNode(begin);
        PeopleNode p2 = (PeopleNode) this.getNode(end);
        int difference = p1.getPerson().countDifference(p2.getPerson());
        addEdge(new PeopleEdge(p1, p2, difference));
    }
    /**
     *      sprawdza czy wszystkie wierzcholki maja co najmniej 1 krawdzedz, jezeli nie to problemu nie da sie rozwiazac
     */
     public boolean checkIfEverybodyHasEdge(){
        for(Node node: nodeList){
            PeopleNode peopleNode = (PeopleNode) node;
            if(peopleNode.getNumberEdges() == 0)
                return false;
        }
        return true;
    }

    public int getDifference(){
         return this.difference;
    }

    public void writePeopleGraph(){
        for(Node node: nodeList){
            PeopleNode peopleNode = (PeopleNode) node;
            System.out.println("NODE: " + peopleNode.getNumber());
            peopleNode.getPerson().writePerson();
            for(Edge edge: peopleNode.getEdges()){
                System.out.print("    " + ((PeopleEdge) edge).getEnd().getNumber() + ": ");
                ((PeopleEdge) edge).getEnd().getPerson().writePerson();
            }
        }
    }

}


