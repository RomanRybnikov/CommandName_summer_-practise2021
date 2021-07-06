package Controller;

import Boruvka.Boruvka;
import GUI.*;
import GUI.Gui;
import Graph.*;
import Input.CheckingCorrect;
import Input.Converter;
import Boruvka.*;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


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

    static boolean algoStart = false;

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
        graph = new Graph();
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();

        graphVisualization = new GraphVisualization(vertexes,edges);
        gui = new Gui(graphVisualization);

        graphVisualization.setVertexHandler(new VertexHandler(this));
        graphVisualization.setEdgeHandler(new EdgeHandler(Controller.this));
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
            vertexes.clear();
            for(Integer v:graph.getVertexes()){
                vertexes.add(new VertexVisualization(v));
            }
            for(Edge edge:graph.getEdges()){
                VertexVisualization v1 = null;
                VertexVisualization v2 = null;
                for(VertexVisualization v:vertexes){
                    if(v.getVertexNum()==edge.getVertex1()){
                        v1=v;
                    }
                    if(v.getVertexNum()==edge.getVertex2()){
                        v2=v;
                    }
                }
                EdgeVisualization edgeVisualization = new EdgeVisualization(v1,v2,edge.getWeight());
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
        graphVisualization.setVertexes(vertexes);
        graphVisualization.setEdges(edges);
        gui.setGraphVisualization(graphVisualization);
    }

    class AddGraphListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока работает алгоритм!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                    graphVisualization.setVertexHandler(new VertexHandler(Controller.this));
                    graphVisualization.setEdgeHandler(new EdgeHandler(Controller.this));
                    gui.setGraphVisualization(graphVisualization);
                }
                else {
                    JOptionPane.showOptionDialog(graphVisualization,"Неверная матрица смежности!","information",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока работает алгоритм!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                return;
            }
            edges.clear();
            vertexes.clear();

            graph.addVertex();

            for(Integer v:graph.getVertexes()){
                vertexes.add(new VertexVisualization(v));
            }

            for(Edge edge:graph.getEdges()){
                int vertex1  = edge.getVertex1();
                int vertex2 = edge.getVertex2();
                int indexVertex1 = graph.getVertexes().indexOf(vertex1);
                int indexVertex2 = graph.getVertexes().indexOf(vertex2);
                edges.add(new EdgeVisualization(vertexes.get(indexVertex1),vertexes.get(indexVertex2),edge.getWeight()));
            }

            graphVisualization.setVertexes(vertexes);
            graphVisualization.setEdges(edges);
            graphVisualization.setVertexHandler(new VertexHandler(Controller.this));
            graphVisualization.setEdgeHandler(new EdgeHandler(Controller.this));
            gui.setGraphVisualization(graphVisualization);
        }
    }

    class AddEdgeListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока работает алгоритм","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                    graphVisualization.setVertexHandler(new VertexHandler(Controller.this));
                    graphVisualization.setEdgeHandler(new EdgeHandler(Controller.this));
                    gui.setGraphVisualization(graphVisualization);
                }catch (IOException ex){
                    JOptionPane.showOptionDialog(graphVisualization,"Ребро введено неверно!","information",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                }
            }else{
                JOptionPane.showOptionDialog(graphVisualization,"Ребро введено неверно!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
            }
        }
    }

    class GenerateGraphListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(algoStart){
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока работает алгоритм!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                    graphVisualization.setVertexHandler(new VertexHandler(Controller.this));
                    graphVisualization.setEdgeHandler(new EdgeHandler(Controller.this));
                    gui.setGraphVisualization(graphVisualization);
                }catch (IOException ex){
                    JOptionPane.showOptionDialog(graphVisualization,"Неверно введены параметры графа!","information",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                }
            }
            else {
                JOptionPane.showOptionDialog(graphVisualization,"Неверно введены параметры графа!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
            }
        }
    }

    class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!algoStart) {
                if(CheckingCorrect.checkCorrectGraphForBoruvka(graph)) {
                    boruvka = new Boruvka(graph);
                    steps = boruvka.getBoruvkaSteps();
                    algoStart = true;
                }else{
                    JOptionPane.showOptionDialog(graphVisualization,"Граф не удовлетворяет условиям работы алгоритма!","information",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                }
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
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока алгоритм не работает!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока алгоритм не работает!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока не работает алгоритм!","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
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
                    VertexVisualization v1 = null;
                    VertexVisualization v2 = null;
                    for(VertexVisualization v:vertexes){
                        if(edge.getVertex1()==v.getVertexNum()){
                            v1=v;
                        }
                        if(edge.getVertex2()==v.getVertexNum()){
                            v2=v;
                        }
                    }
                    edges.add(new EdgeVisualization(v1,v2,edge.getWeight()));
                }

                graphVisualization.setVertexes(vertexes);
                graphVisualization.setEdges(edges);
                gui.setGraphVisualization(graphVisualization);
            }else{
                JOptionPane.showOptionDialog(graphVisualization,"Недоступно, пока не работает алгоритм","information",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
            }
        }
    }

}

