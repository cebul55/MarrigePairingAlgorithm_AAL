package PairingAlgorithm;

import DataGenerator.Person;
import Graph.Node;

import java.util.LinkedList;

public class PeopleNode extends Node {

    private Person person;

    private PeopleNode(int number) {
        super(number);
    }

    public PeopleNode(int number, Person person){
        super(number);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void removeEdge(PeopleNode v){
        super.removeEdge(v.getNumber());
    }

    public void addEdge(PeopleEdge e){
        super.addEdge(e);
    }

    @Override
    public String toString() {
        return (this.getNumber() + ";" + this.getPerson().getSex() + ";" + this.getPerson().getHeight() + ";" + this.getPerson().getHeadGirth());
    }
}
