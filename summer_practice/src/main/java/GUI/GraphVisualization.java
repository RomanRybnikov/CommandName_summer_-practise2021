package GUI;
import Controller.EdgeHandler;
import Controller.ElementHandler;
import Controller.VertexHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GraphVisualization extends JPanel implements MouseListener, MouseMotionListener {
    ArrayList<VertexVisualization> vertexes;
    ArrayList<EdgeVisualization> edges;
    private  float panel_radius;
    private final int HEIGHT = 700;
    private final int WIDTH = 800;
    VertexVisualization movableVertex;
    VertexHandler vHandler;
    EdgeHandler eHandler;
    private int button;


    public static final int RADIUS = 20;

    public GraphVisualization(ArrayList<VertexVisualization> vertexes,ArrayList<EdgeVisualization> edges){
        this.vertexes=vertexes;
        this.edges=edges;
        vertexes.forEach((v)->v.setRadius(RADIUS));
        setCoordinates();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setVertexes(ArrayList<VertexVisualization> v){
        this.vertexes = v;
        setCoordinates();
        vertexes.forEach((vertex)->vertex.setRadius(RADIUS));
    }

    public void setEdges(ArrayList<EdgeVisualization> e){
        this.edges=e;
    }

    public ArrayList<VertexVisualization> getVertexes(){
        return vertexes;
    }

    public ArrayList<EdgeVisualization> getEdges(){
        return edges;
    }

    private void setCoordinates(){
        int count = vertexes.size();
        float pi = (float) Math.PI;
        float delta = 2*pi/count;
        int i = 0 ;
        for(VertexVisualization v:vertexes){
            double angle = pi - i*delta;
            Dimension d = getSize();
            panel_radius = Float.min(WIDTH,HEIGHT)/2;
            float x= panel_radius*(float)Math.cos(angle)+panel_radius;
            float y= panel_radius*(float)Math.sin(angle)+panel_radius;
            x = x>=RADIUS ? x : RADIUS;
            y = y>=RADIUS ? y : RADIUS;
            x = Math.min(x, panel_radius * 2 - RADIUS);
            y = Math.min(y, panel_radius * 2 - RADIUS);
            v.setCoords( x, y);
            i++;
        }
    }

    public void setVertexHandler(ElementHandler vertexHandler){
        this.vHandler = (VertexHandler) vertexHandler;
    }

    public void setEdgeHandler(ElementHandler edgeHandler){
        this.eHandler = (EdgeHandler) edgeHandler;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        try {
            for(EdgeVisualization e:edges){
                e.draw(graphics);
            }

            for(VertexVisualization v:vertexes){
                v.setColor(Color.GREEN);
                v.draw(graphics);
            }
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        setSize(WIDTH,HEIGHT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3 || e.getButton()==MouseEvent.BUTTON1){
            float x = e.getX();
            float y = e.getY();
            for(VertexVisualization vertex:vertexes){
                float xVertex = vertex.getCoordX();
                float yVertex = vertex.getCoordY();
                float radius  = vertex.getRadius();

                if(xVertex <= x+radius && xVertex>=x-radius && yVertex<=y+radius && yVertex>=y-radius){
                    vHandler.setVertex(vertex);
                    vHandler.setNumButton(e.getButton());
                    vHandler.processing();
                    return;
                }
            }
            for (EdgeVisualization edge : edges) {
                VertexVisualization v1 = edge.getVertex1();
                VertexVisualization v2 = edge.getVertex2();
                float x0 = v1.getCoordX();
                float x1 = v2.getCoordX();
                float y0 = v1.getCoordY();
                float y1 = v2.getCoordY();

                if (lineContainsPoint(x,y,x0,y0,x1,y1)) {
                    eHandler.setEdge(edge);
                    eHandler.setNumButton(e.getButton());
                    eHandler.setCoords((int) x, (int) y);
                    eHandler.processing();
                    return;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button=e.getButton();
        if(button==MouseEvent.BUTTON1) {
            for (VertexVisualization v : vertexes) {
                float x = v.getCoordX();
                float y = v.getCoordY();
                float radius = v.getRadius();

                if (e.getX()<=x+radius && e.getX()>=x-radius && e.getY()<=y+radius && e.getY()>=y-radius){
                    movableVertex = v;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button=-1;
        movableVertex=null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private boolean lineContainsPoint(float x,float y,float x0,float y0,float x1,float y1){
        float diff = 0.5f;
        if(Math.abs(x0-x1)<diff){//для вертикальной прямой
            diff=1.0f;
            return  Math.abs(x-x0)<diff && y<Math.max(y0,y1) && y>Math.min(y0,y1);
        }
        diff=0.5f;
        if(Math.abs(y0-y1)<diff){//для горизонтальной
            diff=1.0f;
            return  Math.abs(y-y0)<diff && x<Math.max(x0,x1) && x>Math.min(x0,x1);
        }
        diff = 0.03f;


        return Math.abs((x - x0) / (x1 - x0) - (y - y0) / (y1 - y0)) < diff && x < Math.max(x0, x1) && x > Math.min(x0, x1) && y < Math.max(y0, y1) && y > Math.min(y0, y1);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(button!=MouseEvent.BUTTON1 || movableVertex==null){
            return;
        }
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if(x<0 || y<0 || x>getWidth() || y>getHeight()){
            return;
        }
        movableVertex.setCoords(x,y);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) { }
}
