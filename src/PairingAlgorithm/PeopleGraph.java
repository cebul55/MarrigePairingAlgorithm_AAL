package PairingAlgorithm;

import DataGenerator.PeopleVector;
import DataGenerator.Person;
import DataGenerator.Sex;
import Graph.Graph;
import Graph.Edge;
import Graph.Node;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class PeopleGraph extends Graph {

    private int difference = 0;
    private int[] tableOfMatches = null;
    private long elapsedTime = 0;

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

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.tableOfMatches = null;
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

    /**
     * funkcja zwracajace roznice sum wzrostow i obwodow glow
     * @return
     */
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
//todo zmien tworzenie grafu na bardziej losowe !

    //TODO SPRAWDZ POPRAWNOSC FUNKCJI
    /**
     * Funkcja wykonujaca algorytm dopasowania malzenstw
     * @return tablice z utworzonymi parami (indeksy odpowiadaja wierzcholkom grafu, a kazdy element zawiera numer skojarzonego wierzcholka
     */
    public int[] pairingAlgorithm(){
        int n = this.getNodeCount();
        int x;
        PeopleNode vPeopleNode, xPeopleNode;
        // tablica skojarzen
        int[] matching = new int[n];

        /*  pomocnicza tablica do tworzenia naprzemiennej sciezki rozszerzajacej, przechowuje
        tworzona przez BFS strukture drzewa rozpinajacego wszerz. I-ty element zawiera numer wierzcholka
        nadrzednego w drzewie rozpinajacym  */
        int[] alternatingPath = new int[n];

        // pomocnicza tablica logiczna sluzaca do zaznaczania odwiedzonych wierzcholkow
        Boolean[] visited = new Boolean[n];

        // kolejka, w ktorej sa skladowane wierzcholki dla BFS
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i = 0 ; i < n ; i++){
            matching[i] = (-1);
        }
        long startTime = System.nanoTime();
        //wlasciwy algorytm
        for(int v = 0; v < n; v++){
            vPeopleNode = (PeopleNode) this.getNode(v);
            //sprawdzamy czy dany wierzcholek jest kobieta i nie jest skojarzona z mezczyzna
            if(matching[v] == (-1) && vPeopleNode.getPerson().getSex() == Sex.FEMALE){
                Arrays.fill(visited,false);
                queue.clear();

                visited[v] = true;
                alternatingPath[v] = -1;
                queue.push(v);

                while (!queue.isEmpty()){
                    x = queue.getFirst();
                    xPeopleNode = (PeopleNode) this.getNode(x);
                    queue.removeFirst();

                    if(xPeopleNode.getPerson().getSex() == Sex.FEMALE){
                        //jezeli w kolejce trafiamy na kobiete, to w kolejce umieszczamy wszystkie nieodwiedzone sasiednie wierzcholki
                        for (Integer y: xPeopleNode.getNeighboursList()){
                            if(!visited[y]) {
                                visited[y] = true;
                                alternatingPath[y] = x;

                                queue.add(y);
                            }
                        }
                    }

                    else if(matching[x] > (-1)){
                        alternatingPath[matching[x]] = x;
                        visited[matching[x]] = true;
                        queue.add(matching[x]);
                    }
                    else{
                        while(alternatingPath[x] > (-1)){
                            xPeopleNode = (PeopleNode) this.getNode(x);
                            if(xPeopleNode.getPerson().getSex() == Sex.MALE){
                                matching[x] = alternatingPath[x];
                                matching[alternatingPath[x]] = x;
                            }
                            x = alternatingPath[x];
                        }
                        break;
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        this.elapsedTime = endTime - startTime;

        tableOfMatches = matching;
        return matching;
    }

    public boolean checkPairingAlgorithmResult(){
        for(int each: this.tableOfMatches){
            if(each < 0){
                return false;
            }
        }
        return true;
    }

    public long getElapsedTime(){
        //return TimeUnit.NANOSECONDS.toSeconds(elapsedTime);
        return this.elapsedTime;
    }

    public void writePairsToFile(String filePath){
        String fileContent = "nr;SEX;HEIGHT;HEAD;nr;SEX;HEIGHT;HEAD\n";

        BufferedWriter writer = null;
        PeopleNode n1, n2;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContent);
            for(int i = 0; i < this.getNodeCount() / 2; i++){
                n1 = (PeopleNode) this.getNode(i);
                n2 = (PeopleNode) this.getNode(tableOfMatches[i]);
                fileContent = n1.toString() + ";" + n2.toString() + "\n";
                writer.write(fileContent);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


