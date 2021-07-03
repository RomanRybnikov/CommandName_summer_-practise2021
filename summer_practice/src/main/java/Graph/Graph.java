package Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import Input.*;

public class Graph {
    private ArrayList<Edge> edges;
    private ArrayList<Integer> vertexes;
    private int countVertexes;
    private int countEdges;

    public Graph(ArrayList<ArrayList<Integer>> matrix) throws IOException {
        if(CheckingCorrect.checkCorrectMatrix(matrix)){
            edges = new ArrayList<>();
            vertexes = new ArrayList<>();
            fillEdges(edges,matrix);
            fillVertexes(vertexes,matrix);
            countEdges= edges.size();
            countVertexes=vertexes.size();
        }
        else{
            throw new IOException("incorrect matrix");
        }
    }

    private Graph(ArrayList<Edge> edges, ArrayList<Integer> vertexes){
        this.edges = (ArrayList<Edge>) edges.clone();
        this.countEdges = edges.size();
        this.vertexes = (ArrayList<Integer>) vertexes.clone();
        this.countVertexes = vertexes.size();
    }

    public int getCountVertexes(){
        return countVertexes;
    }

    public int getCountEdges(){
        return countEdges;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Integer> getVertexes(){
        return vertexes;
    }

    public void addVertex(){
        ListIterator<Integer> iterator = vertexes.listIterator();
        int index = 0;
        while (iterator.hasNext()){//вставляем в первое "пустое" место
            Integer v = iterator.next();
            if(v!=index){
                iterator.previous();
                iterator.add(index);
                countVertexes++;
                return;
            }
            index++;
        }
        vertexes.add(countVertexes);
        countVertexes++;
    }

    public void deleteVertex(int vertex) throws IndexOutOfBoundsException{
        if(vertexes.contains(vertex)) {
            ListIterator<Integer> iterator = vertexes.listIterator();
            while(iterator.hasNext()){
                Integer v = iterator.next();
                if(v==vertex){
                    iterator.remove();
                    break;
                }
            }
            deleteIncidentEdges(vertex);
            countVertexes--;
        }
        else{
            throw new IndexOutOfBoundsException("Vertex with number " + vertex + "does not exist");
        }
    }

    public void addEdge(Edge edge){
        ListIterator<Edge> iterator = containsEdge(edge);
        if(iterator!=null){//если ребро инцидентное данным вершинам уже есть, то заменяем его
            iterator.set(edge);
        }
        else{
            edges.add(edge);
            countEdges++;
        }
    }

    public void deleteEdge(int vertex1, int vertex2) throws IndexOutOfBoundsException{
        if(vertex1<countVertexes && vertex1>=0 && vertex2<countVertexes && vertex2>=0) {
            Edge edge = new Edge(vertex1,vertex2);
            ListIterator<Edge> iterator = containsEdge(edge);
            if(iterator != null)//если такое ребро есть
            {
                iterator.remove();
                countEdges--;
            }
            else{
                throw new IndexOutOfBoundsException("Edge with vertex numbers"+vertex1+" and "+vertex2+" does not exist");
            }
        }
    }


    private void fillEdges(ArrayList<Edge> edges, ArrayList<ArrayList<Integer>> matrix){
        for(int i = 0 ;i<matrix.size();i++)
        {
            for(int j = i ;j<matrix.size();j++){
                Integer weight = matrix.get(i).get(j);
                if(weight != -1){
                    edges.add(new Edge(i,j,weight));
                    countEdges++;
                }
            }
        }
    }

    private void fillVertexes(ArrayList<Integer>vertexes,ArrayList<ArrayList<Integer>> matrix){
        for(int i = 0; i<matrix.size(); i++){
            vertexes.add(i);
        }
    }

    private ListIterator<Edge>  containsEdge(Edge edge){
        ListIterator<Edge> iterator = edges.listIterator();
        while(iterator.hasNext()){
            Edge tempEdge = iterator.next();
            if(edge.comparisonIncidentVertexes(tempEdge)){
                return iterator;
            }
        }
        return null;
    }

    public Graph copyGraph(){
        return new Graph(this.edges,this.vertexes);
    }

    private void deleteIncidentEdges(int vertex){
        edges.removeIf(edge -> edge.getVertex1() == vertex || edge.getVertex2() == vertex);
    }

    @Override
    public boolean equals(Object object) {
        if(object == this){
            return true;
        }
        if(object == null || object.getClass() != this.getClass()){
            return false;
        }
        Graph graph = (Graph) object;
        ArrayList<Edge> edges1 = (ArrayList<Edge>) this.edges.clone();
        ArrayList<Edge> edges2 = (ArrayList<Edge>) graph.edges.clone();
        edges1.sort(Comparator.comparingInt(Edge::getWeight));
        edges2.sort(Comparator.comparingInt(Edge::getWeight));

        return edges1.equals(edges2) && this.countEdges==graph.countEdges &&
                this.countVertexes==graph.countVertexes && this.vertexes.equals(graph.vertexes);

    }
}
