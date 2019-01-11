package Graph;

import java.util.LinkedList;

public class Graph {
    protected LinkedList<Node> nodeList = new LinkedList<Node>();
    protected int nodeCount = 0;

    public Graph() {}

    public Graph(int nodeCount){
        for(int i = 0; i < nodeCount ; i++){
            nodeList.add(new Node(i));
        }
        this.nodeCount = nodeCount;
    }

    @Override
    protected void finalize() throws Throwable {
        for (Node node: nodeList ) {
            node.getNeighboursList().clear();
        }
        this.nodeList.clear();
    }

    //dodanie nowego wierzcholka
    public void addNode(){
        nodeList.add(new Node(nodeCount));
        nodeCount++;
    }

    //usuwa wirzcholek wraz ze wszyskimi biegnacymi do niego krawedziami
    public void removeNode(int i){
        if(i < nodeCount){
            nodeList.set(i, null);
            for(Node n: nodeList){
                if(n != null){
                    n.removeEdge(i);
                }
            }
        }
    }

    //dodaje do grafu krawedz
    public void addEdge(Edge e){
        nodeList.get(e.getBegin().getNumber()).addEdge(e);
    }

    //dodaje do grafu krawedz o poczatku i koncu
    public void addEdge(int begin, int end, int weight){
        if(begin >= 0 && begin <= nodeCount && end >= 0 && end <= nodeCount && nodeList.get(begin) != null && nodeList.get(end) != null){
            addEdge(new Edge(nodeList.get(begin),nodeList.get(end),weight));
        }
    }

    //usuwa krawedz o poczatku i koncu
    public void removeEdge(int begin, int end){
        if(begin >= 0 && begin <= nodeCount && nodeList.get(begin) != null){
            nodeList.get(begin).removeEdge(end);
        }
    }

    //usuwa krawedz, jezeli taka istnieje
    public void removeEdge(Edge e){
        if(e.getBegin() == null) return;

        int b = e.getBegin().getNumber();
        if( b>=0 && b<=nodeCount && nodeList.get(b) != null)
            nodeList.get(b).removeEdge( e.getEnd().getNumber() );
    }

    public Node getNode(int i){
        return nodeList.get(i);
    }

    //zwraca kopie listy wierzcholkow
    protected LinkedList<Node> getNodeList()
    {
        return new LinkedList<Node>(nodeList);
    }

    public int getNodeCount(){
        return nodeCount;
    }
}
