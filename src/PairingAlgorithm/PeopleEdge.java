package PairingAlgorithm;


import Graph.Edge;

public class PeopleEdge extends Edge {


    PeopleEdge(PeopleNode begin, PeopleNode end, int weight) {
        super(begin, end, weight);
    }

//    public PeopleEdge(Person male, Person female){
//        super();
//        //todo implement add edge between male and female
//    }

    @Override
    public String toString() {
        return "People Edge";
    }

    @Override
    public PeopleNode getBegin(){
        return (PeopleNode) super.getBegin();
    }

    @Override
    public PeopleNode getEnd(){
        return (PeopleNode) super.getEnd();
    }

    @Override
    public int getWeight(){
        return super.getWeight();
    }
}
