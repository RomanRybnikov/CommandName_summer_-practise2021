package GUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GraphVisualization extends JPanel {
    ArrayList<VertexVisualization> vertexes;
    ArrayList<EdgeVisualization> edges;
    private  float panel_radius;
    private final int HEIGHT = 700;
    private final int WIDTH = 800;

    public static final int RADIUS = 20;

    public GraphVisualization(ArrayList<VertexVisualization> vertexes,ArrayList<EdgeVisualization> edges){
        this.vertexes=vertexes;
        this.edges=edges;
        vertexes.forEach((v)->v.setRadius(RADIUS));
        setCoordinates();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        try {
            for(EdgeVisualization e:edges){
                e.draw(graphics);
            }

            for(VertexVisualization v:vertexes){
                v.setColor(Color.BLUE);
                v.draw(graphics);
            }
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        setSize(WIDTH,HEIGHT);
    }
}
