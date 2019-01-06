package Graph;

import DataGenerator.Person;

public class Edge {
    private Node begin, end;
    private int weight;

    private Edge(){}

//    private Egde(Person male, Person female){
//
//    }

    public Edge(Node begin, Node end, int weight){
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public Node getBegin() {
        return begin;
    }

    public Node getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
