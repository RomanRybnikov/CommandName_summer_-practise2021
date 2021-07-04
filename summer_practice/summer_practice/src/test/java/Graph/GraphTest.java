package Graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;



class GraphTest {

    ArrayList<ArrayList<Integer>> defaultMatrix ;
    Graph defaultGraph ;

    GraphTest(){
        defaultMatrix = getMatrix();
        try{
            defaultGraph=new Graph(defaultMatrix);
            assertEquals(1,1);
        }catch (IOException e){
            assertEquals(0,1);
        }
    }

    @Test
    void graphTest(){
        defaultMatrix = getMatrix();
        try{
            defaultGraph=new Graph(defaultMatrix);
            assertEquals(1,1);
        }catch (IOException e){
            assertEquals(0,1);
        }
    }

    @Test
    void getCountVertexes() {
        int actual = defaultGraph.getCountVertexes();
        int expected = 5;
        assertEquals(expected,actual);
    }

    @Test
    void getCountEdges() {
        int actual = defaultGraph.getCountEdges();
        int expected = 8;
        assertEquals(expected,actual);
    }

    @Test
    void addVertex(){
        Graph graph = defaultGraph.copyGraph();
        graph.addVertex();

        int actualCount = graph.getCountVertexes();
        int expectedCount = 6;
        assertEquals(expectedCount,actualCount);

        ArrayList<Integer> vertexes = new ArrayList<>(Arrays.asList(0,1,2,3,4,5));
        boolean actualBool = vertexes.equals(graph.getVertexes());
        boolean expectedBool=true;
        assertEquals(expectedBool,actualBool);

        graph.deleteVertex(3);
        graph.deleteVertex(4);
        graph.addVertex();

        actualCount = graph.getCountVertexes();
        expectedCount = 5;
        assertEquals(expectedCount,actualCount);

        vertexes = new ArrayList<>(Arrays.asList(0,1,2,3,5));
        actualBool = vertexes.equals(graph.getVertexes());
        expectedBool = true;
        assertEquals(expectedBool,actualBool);

    }

    @Test
    void deleteVertex() {
        Graph graph = defaultGraph.copyGraph();
        try{
            graph.deleteVertex(3);

            int actualCount = graph.getCountVertexes();
            int expectedCount=4;
            assertEquals(expectedCount,actualCount);

            ArrayList<Integer> vertexes = new ArrayList<>(Arrays.asList(0,1,2,4));
            boolean actualBool = vertexes.equals(graph.getVertexes());
            boolean expectedBool = true;
            assertEquals(expectedBool,actualBool);

            ArrayList<Edge> edges1 = new ArrayList<Edge>(Arrays.asList(
                    new Edge(0,1,1),
                    new Edge(0,2,0),
                    new Edge(1,1,2),
                    new Edge(2,2,1),
                    new Edge(4,4,5)
            ));
            ArrayList<Edge>edges2 =(ArrayList<Edge>) graph.getEdges().clone();
            edges1.sort((o1,o2)->o1.getWeight()-o2.getWeight());
            edges2.sort((o1,o2)->o1.getWeight()-o2.getWeight());

            actualBool = edges1.equals(edges2);
            expectedBool=true;
            assertEquals(expectedBool,actualBool);

        }catch (IndexOutOfBoundsException e ){
            assertEquals(0,1);
        }

        try{
            graph = defaultGraph.copyGraph();
            graph.deleteVertex(5);
            assertEquals(0,1);
        }catch (IndexOutOfBoundsException e){
            assertEquals(1,1);
        }
    }

    @Test
    void addEdge() {
        try {
            Graph graph = defaultGraph.copyGraph();
            Edge newEdge = new Edge(1, 2, 12);
            graph.addEdge(newEdge);

            int actualCount = graph.getCountEdges();
            int expectedCount = 9;
            assertEquals(actualCount, expectedCount);

            Edge edge = graph.getEdges().get(graph.getEdges().size() - 1);
            boolean actualEqual = edge.equals(newEdge);
            boolean expectedEqual = true;
            assertEquals(actualEqual, expectedEqual);

            newEdge = new Edge(1, 2, 10);
            graph.addEdge(newEdge);
            actualCount = graph.getCountEdges();
            expectedCount = 9;
            assertEquals(actualCount, expectedCount);

            edge = graph.getEdges().get(graph.getEdges().size() - 1);
            actualEqual = edge.equals(newEdge);
            expectedEqual = true;
            assertEquals(actualEqual, expectedEqual);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void deleteEdge() {
        Graph graph = defaultGraph.copyGraph();
        try {
            graph.deleteEdge(4, 3);
            int actualCount = graph.getCountEdges();
            int expectedCount = 7;
            assertEquals(expectedCount,actualCount);

            ArrayList<ArrayList<Integer>> matrix= new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(null,1,0,null,null)),
                    new ArrayList<>(Arrays.asList(1,2,null,1,null)),
                    new ArrayList<>(Arrays.asList(0,null,1,null,null)),
                    new ArrayList<>(Arrays.asList(null,1,null,4,null)),
                    new ArrayList<>(Arrays.asList(null,null,null,null,5))
            ));

            Graph newGraph = new Graph(matrix);
            boolean actualBool = newGraph.equals(graph);
            boolean expectedBool=true;
            assertEquals(actualBool,expectedBool);

        }catch(IndexOutOfBoundsException | IOException e){
            assertEquals(1,0);
        }

        try{
            graph = defaultGraph.copyGraph();
            graph.deleteEdge(0,4);
            assertEquals(0,1);
        }catch (IndexOutOfBoundsException e){
            assertEquals(1,1);
        }
    }

    ArrayList<ArrayList<Integer>> getMatrix(){
        ArrayList<ArrayList<Integer>> matrix= new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(null,1,0,null,null)),
                new ArrayList<>(Arrays.asList(1,2,null,1,null)),
                new ArrayList<>(Arrays.asList(0,null,1,null,null)),
                new ArrayList<>(Arrays.asList(null,1,null,4,5)),
                new ArrayList<>(Arrays.asList(null,null,null,5,5))
        ));
        return matrix;
    }

}