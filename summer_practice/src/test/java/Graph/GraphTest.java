package Graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;



class GraphTest {

    ArrayList<ArrayList<Integer>> defaultMatrix ;
    Graph defaultGraph ;

    @BeforeEach
    void setUp() {
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
        int expected = 5;
        assertEquals(expected,actual);
    }

    @Test
    void addVertex(){
        int actualCount = defaultGraph.getCountVertexes();
        int expectedCount = 5;
        assertEquals(expectedCount,actualCount);

        ArrayList<Integer> vertexes = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        boolean actualBool = vertexes.equals(defaultGraph.getVertexes());
        boolean expectedBool=true;
        assertEquals(expectedBool,actualBool);

        defaultGraph.deleteVertex(2);
        defaultGraph.deleteVertex(4);
        defaultGraph.addVertex();

        actualCount = defaultGraph.getCountVertexes();
        expectedCount = 4;
        assertEquals(expectedCount,actualCount);

        vertexes = new ArrayList<>(Arrays.asList(0,1,2,3));
        actualBool = vertexes.equals(defaultGraph.getVertexes());
        expectedBool = true;
        assertEquals(expectedBool,actualBool);

    }

    @Test
    void deleteVertex() {
        try{
            defaultGraph.deleteVertex(3);

            int actualCount =  defaultGraph.getCountVertexes();
            int expectedCount=4;
            assertEquals(expectedCount,actualCount);

            ArrayList<Integer> vertexes = new ArrayList<>(Arrays.asList(0,1,2,4));
            boolean actualBool = vertexes.equals( defaultGraph.getVertexes());
            boolean expectedBool = true;
            assertEquals(expectedBool,actualBool);

            ArrayList<Edge> edges1 = new ArrayList<Edge>(Arrays.asList(
                    new Edge(0,4,1),
                    new Edge(0,2,8),
                    new Edge(4,2,12),
                    new Edge(1,2,6)
            ));
            ArrayList<Edge>edges2  = new ArrayList<Edge>();
            edges2.addAll( defaultGraph.getEdges());
            edges1.sort((o1,o2)->o1.getWeight()-o2.getWeight());
            edges2.sort((o1,o2)->o1.getWeight()-o2.getWeight());

            actualBool = edges1.equals(edges2);
            expectedBool=true;
            assertEquals(expectedBool,actualBool);

        }catch (IndexOutOfBoundsException e ){
            assertEquals(0,1);
        }

        try{
            defaultGraph = defaultGraph.copyGraph();
            defaultGraph.deleteVertex(5);
            assertEquals(0,1);
        }catch (IndexOutOfBoundsException e){
            assertEquals(1,1);
        }
    }

    @Test
    void addEdge() {
        try {
            Edge newEdge = new Edge(1, 3, 12);
            defaultGraph.addEdge(newEdge);

            int actualCount = defaultGraph.getCountEdges();
            int expectedCount = 6;
            assertEquals(actualCount, expectedCount);

            Edge edge = defaultGraph.getEdges().get(defaultGraph.getEdges().size() - 1);
            boolean actualEqual = edge.equals(newEdge);
            boolean expectedEqual = true;
            assertEquals(actualEqual, expectedEqual);

            newEdge = new Edge(1, 2, 10);
            defaultGraph.addEdge(newEdge);
            actualCount = defaultGraph.getCountEdges();
            expectedCount = 6;
            assertEquals(actualCount, expectedCount);

        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void deleteEdge() {
        try {
            defaultGraph.deleteEdge(0, 2);
            int actualCount = defaultGraph.getCountEdges();
            int expectedCount = 4;
            assertEquals(expectedCount,actualCount);

            ArrayList<ArrayList<Integer>> matrix= new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(-1,-1,-1,-1,1)),
                    new ArrayList<>(Arrays.asList(-1,-1,6,-1,-1)),
                    new ArrayList<>(Arrays.asList(-1,6,-1,2,12)),
                    new ArrayList<>(Arrays.asList(-1,-1,2,-1,-1)),
                    new ArrayList<>(Arrays.asList(1,-1,12,-1,-1))
            ));

            Graph newGraph = new Graph(matrix);
            boolean actualBool = newGraph.equals(defaultGraph);
            boolean expectedBool=true;
            assertEquals(actualBool,expectedBool);

        }catch(IndexOutOfBoundsException | IOException e){
            assertEquals(1,0);
        }

        try{
            defaultGraph = defaultGraph.copyGraph();
            defaultGraph.deleteEdge(0,3);
            assertEquals(0,1);
        }catch (IndexOutOfBoundsException e){
            assertEquals(1,1);
        }
    }

    ArrayList<ArrayList<Integer>> getMatrix(){
        ArrayList<ArrayList<Integer>> matrix= new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(-1,-1,8,-1,1)),
                new ArrayList<>(Arrays.asList(-1,-1,6,-1,-1)),
                new ArrayList<>(Arrays.asList(8,6,-1,2,12)),
                new ArrayList<>(Arrays.asList(-1,-1,2,-1,-1)),
                new ArrayList<>(Arrays.asList(1,-1,12,-1,-1))
        ));
        return matrix;
    }

}