package PairingAlgorithm;

import DataGenerator.PeopleVector;
import DataGenerator.Person;
import Graph.Graph;
import Graph.Edge;
import Graph.Node;

import java.util.Vector;

public class PeopleGraph extends Graph {

    public PeopleGraph(PeopleVector malePeople, PeopleVector femalePeople){

        malePeople.sortPeopleVectorByHeight();
        femalePeople.sortPeopleVectorByHeight();

        assert(malePeople.getNumberOfPeople() == femalePeople.getNumberOfPeople());

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

    }
    //dodaje krawedz
    public void addEdge(int begin, int end){
        PeopleNode p1 = (PeopleNode) this.getNode(begin);
        PeopleNode p2 = (PeopleNode) this.getNode(end);
        int difference = p1.getPerson().countDifference(p2.getPerson());
        addEdge(new PeopleEdge(p1, p2, difference));
    }
    //sprawdza czy wszystkie wierzcholki maja co najmniej 1 krawdzedz, jezeli nie to problemu nie da sie rozwiazac
    public boolean checkIfEverybodyHasEdge(){
        for(Node node: nodeList){
            PeopleNode peopleNode = (PeopleNode) node;
            if(peopleNode.getNumberEdges() == 0)
                return false;
        }
        return true;
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


