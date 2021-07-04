package Controller;

import Boruvka.Boruvka;
import GUI.*;
import GUI.Gui;
import Graph.*;
import Input.CheckingCorrect;
import Input.Converter;
import Boruvka.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class Controller {
    Graph graph;
    Boruvka boruvka;
    BoruvkaSteps steps;
    Gui gui;
    GraphVisualization graphVisualization;
    ArrayList<EdgeVisualization> edges;
    ArrayList<VertexVisualization> vertexes;
    AddGraphListener addGraphListener;
    AddVertexListener addVertexListener;
    AddEdgeListener addEdgeListener;
    GenerateGraphListener generateGraphListener;
    StartListener startListener;
    NextListener nextListener;
    PrevListener prevListener ;
    StopListener stopListener;
    EndListener endListener;

    boolean algoStart = false;

    public Controller(){
        addGraphListener = new AddGraphListener();
        addVertexListener = new AddVertexListener();
        addEdgeListener = new AddEdgeListener();
        generateGraphListener = new GenerateGraphListener();
        startListener = new StartListener();
        nextListener = new NextListener();
        prevListener = new PrevListener();
        stopListener = new StopListener();
        endListener = new EndListener();
    }

    public void start(){

        GraphGenerator generator = null;
        try {
             generator = new GraphGenerator(10, 11, 2, 10);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        graph = new Graph();
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();

        graphVisualization = new GraphVisualization(vertexes,edges);
        gui = new Gui(graphVisualization);
        setGuiButtonsListeners();
        gui.setVisible(true);
    }

    private void setGuiButtonsListeners(){
        gui.setButtonInputGraphListener(addGraphListener);
        gui.setButtonInputAddVertexListener(addVertexListener);
        gui.setButtonInputAddEdgeListener(addEdgeListener);
        gui.setButtonGenerateGraphListener(generateGraphListener);
        gui.setButtonStartListener(startListener);
        gui.setButtonNextListener(nextListener);
        gui.setButtonPrevListener(prevListener);
        gui.setButtonEndListener(endListener);
        gui.setButtonStopListener(stopListener);
    }

    private void changeStep(Optional<ArrayList<Edge>> edgesOpt){
        if (edgesOpt.isPresent()) {
            ArrayList<Edge> edgesList = edgesOpt.get();
            edges.clear();
            for(Edge edge:graph.getEdges()){
                EdgeVisualization edgeVisualization = new EdgeVisualization(vertexes.get(edge.getVertex1()),vertexes.get(edge.getVertex2()),edge.getWeight());
                for(Edge edge2:edgesList){
                    if(edge2.equals(edge)){
                        if(edge2.getMarkLastAdded()){
                            edgeVisualization.setColor(EdgeVisualization.COLOR_LAST_ADD);
                        }else{
                            edgeVisualization.setColor(EdgeVisualization.COLOR_JUST_ADD);
                        }
                        break;
                    }
                }
                edges.add(edgeVisualization);
            }

        }
        graphVisualization.setEdges(edges);
        gui.setGraphVisualization(graphVisualization);
    }

    class AddGraphListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                System.out.println("not available while the algorithm is running");
                return;
            }
            try {
                vertexes.clear();
                edges.clear();

                String matrix = Controller.this.gui.getTextAtInputAddGraph();
                if (CheckingCorrect.checkCorrectStringMatrix(matrix)) {
                    ArrayList<ArrayList<Integer>> matrixInt = Converter.convertStringMatrix(matrix);
                    graph = new Graph(matrixInt);

                    for(Integer v:graph.getVertexes()){
                        vertexes.add(new VertexVisualization(v));
                    }

                    for(Edge edge:graph.getEdges()){
                        edges.add(new EdgeVisualization(vertexes.get(edge.getVertex1()),vertexes.get(edge.getVertex2()),edge.getWeight()));
                    }

                    graphVisualization.setVertexes(vertexes);
                    graphVisualization.setEdges(edges);
                    gui.setGraphVisualization(graphVisualization);
                }
                else {
                    System.out.println("wrong matrix!");
                }
            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    class AddVertexListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                System.out.println("not available while the algorithm is running");
                return;
            }
            graph.addVertex();
            vertexes.add(new VertexVisualization(graph.getVertexes().size()-1));
            graphVisualization.setVertexes(vertexes);
            gui.setGraphVisualization(graphVisualization);
        }
    }

    class AddEdgeListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                System.out.println("not available while the algorithm is running");
                return;
            }
            String stringEdge=gui.getTextAtInputAddEdge();
            if(CheckingCorrect.checkingCorrectStringEdge(stringEdge)){
                Edge edge = Converter.convertStringEdge(stringEdge);
                try {
                    graph.addEdge(edge);

                    edges.clear();
                    for(Edge edge_:graph.getEdges()){
                        edges.add(new EdgeVisualization(vertexes.get(edge_.getVertex1()),vertexes.get(edge_.getVertex2()),edge_.getWeight()));
                    }

                    graphVisualization.setEdges(edges);
                    gui.setGraphVisualization(graphVisualization);
                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    class GenerateGraphListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                System.out.println("not available while the algorithm is running");
                return;
            }
            String stringGenerate = gui.getTextAtInputLimits();
            if(CheckingCorrect.checkCorrectStringLimits(stringGenerate)){
                int[] limits = Converter.convertStringLimits(stringGenerate);
                try {
                    GraphGenerator generator = new GraphGenerator(limits[0], limits[1], limits[2], limits[3]);
                    graph = generator.generateGraph();

                    vertexes.clear();
                    edges.clear();

                    for(Integer v:graph.getVertexes()){
                        vertexes.add(new VertexVisualization(v));
                    }

                    for(Edge edge:graph.getEdges()){
                        edges.add(new EdgeVisualization(vertexes.get(edge.getVertex1()),vertexes.get(edge.getVertex2()),edge.getWeight()));
                    }

                    graphVisualization.setVertexes(vertexes);
                    graphVisualization.setEdges(edges);
                    gui.setGraphVisualization(graphVisualization);
                }catch (IOException ex){
                    System.out.println(ex.getMessage() + " in GenerateGraphListener");
                }
            }
            else {
                System.out.println("generate limits incorrect");
            }
        }
    }

    class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!algoStart) {
                boruvka = new Boruvka(graph);
                steps = boruvka.getBoruvkaSteps();
                algoStart = true;
            }
        }
    }

    class NextListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart) {
                Optional<ArrayList<Edge>> edgesOpt = steps.nextStep();
                Controller.this.changeStep(edgesOpt);
            }else{
                System.out.println("not available while the algorithm is not running");
            }
        }
    }

    class PrevListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart) {
                Optional<ArrayList<Edge>> edgesOpt = steps.prevStep();
                Controller.this.changeStep(edgesOpt);
            }else{
                System.out.println("not available while the algorithm is not running");
            }
        }
    }

    class EndListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart) {
                Optional<ArrayList<Edge>> edgesOpt = steps.lastStep();
                Controller.this.changeStep(edgesOpt);
            }else{
                System.out.println("not available while the algorithm is not running");
            }
        }
    }

    class StopListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart) {
                algoStart=false;
                vertexes.clear();
                edges.clear();

                for(Integer v:graph.getVertexes()){
                    vertexes.add(new VertexVisualization(v));
                }

                for(Edge edge:graph.getEdges()){
                    edges.add(new EdgeVisualization(vertexes.get(edge.getVertex1()),vertexes.get(edge.getVertex2()),edge.getWeight()));
                }

                graphVisualization.setVertexes(vertexes);
                graphVisualization.setEdges(edges);
                gui.setGraphVisualization(graphVisualization);
            }else{
                System.out.println("not available while the algorithm is not running");
            }
        }
    }
}

