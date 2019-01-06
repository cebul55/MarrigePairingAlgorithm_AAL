package Graph;

import java.util.LinkedList;

public class Node {
    private int number; //numer wierzcholka
    private LinkedList<Edge> edges = new LinkedList<Edge>();

    private Node() {};

    public Node(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    //Usuwa krawedzie biegnace do i-tego wierzcholka, jesli istnieje
    public void removeEdge(int i){
        for (Edge e: edges) {
            if(e.getEnd().getNumber() == i){
                edges.remove(e);
            }
        }
    }

    public void removeEdge(Node v){
        removeEdge(v.getNumber());
    }

    public void addEdge(Edge e){
        this.edges.add(e);
    }

    //zwraca krawedz biegnaca do i-tego wierzcholka
    public Edge getEdge(int i){
        for (Edge e: edges) {
            if(e.getEnd().getNumber() == i){
                return e;
            }
        }
        return null;
    }
}
