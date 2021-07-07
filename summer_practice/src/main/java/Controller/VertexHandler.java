package Controller;
import Graph.*;
import GUI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class VertexHandler implements ElementHandler{
    private int vertexNum = -1;
    private VertexVisualization vertexVisualization;
    private int button = -1;
    private  Controller controller;

    VertexHandler(Controller controller){
       this.controller=controller;
    }

    public void setVertex(VertexVisualization vertex){
        this.vertexNum=vertex.getVertexNum();
        vertexVisualization=vertex;
    }
    public void setNumButton(int button){
        this.button=button;
    }
    @Override
    public void processing() {
        if(vertexNum!=-1 && !Controller.algoStart){
            if(button == MouseEvent.BUTTON3){
                JPopupMenu menu = new JPopupMenu();
                JMenuItem itemDelete = new JMenuItem("Удалить вершину");
                JMenuItem itemAddLink = new JMenuItem("Соедининть с другой вершиной");

                itemDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.graph.deleteVertex(vertexNum);
                        setChanges();
                    }
                });

                itemAddLink.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String result = JOptionPane.showInputDialog(controller.graphVisualization,"<html><h3>Введите номер вершины и вес через пробел");
                        if(result==null){
                            return;
                        }
                        String[] splitResult = result.split(" ");
                        if(splitResult.length!=2){
                            JOptionPane.showOptionDialog(controller.graphVisualization,"Неверный формат!","information",
                                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                            return;
                        }
                        try{
                            int vertex = Integer.parseInt(splitResult[0]);
                            int weight = Integer.parseInt(splitResult[1]);
                            if(weight<0){
                                JOptionPane.showOptionDialog(controller.graphVisualization,"Введен отрицательный вес!","information",
                                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                                return;
                            }
                            if(!controller.graph.getVertexes().contains(vertex)){
                                JOptionPane.showOptionDialog(controller.graphVisualization,"Введенной вершины нет в графе!","information",
                                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                                return;
                            }
                            controller.graph.addEdge(new Edge(vertex,vertexNum,weight));
                            setChanges();
                        }catch (NumberFormatException | IOException ex){
                            JOptionPane.showOptionDialog(controller.graphVisualization,"Неверный формат!","information",
                                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                        }
                    }
                });

                menu.add(itemDelete);
                menu.add(itemAddLink);
                menu.show(controller.graphVisualization, (int) vertexVisualization.getCoordX(),(int) vertexVisualization.getCoordY());
            }
        }
    }

    private void setChanges(){//изменяет визуализацию графа
        ArrayList<EdgeVisualization> edges = new ArrayList<>();
        ArrayList<VertexVisualization> vertexes = controller.graphVisualization.getVertexes();

        vertexes.removeIf(vertex -> !controller.graph.getVertexes().contains(vertex.getVertexNum()));


        for(Edge edge:controller.graph.getEdges()){
            int vertex1  = edge.getVertex1();
            int vertex2 = edge.getVertex2();
            VertexVisualization v1=null;
            VertexVisualization v2 =null;
            for(VertexVisualization v:vertexes){
                if(v.getVertexNum()==vertex1){
                    v1 = v;
                }
                if(v.getVertexNum()==vertex2){
                    v2=v;
                }
            }
            edges.add(new EdgeVisualization(v1,v2,edge.getWeight()));
        }

        controller.graphVisualization.setVertexes(vertexes);
        controller.graphVisualization.setEdges(edges);
        controller.graphVisualization.setVertexHandler(new VertexHandler(controller));
        controller.graphVisualization.setEdgeHandler(new EdgeHandler(controller));
        controller.gui.setGraphVisualization(controller.graphVisualization);
    }
}
