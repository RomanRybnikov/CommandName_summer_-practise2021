package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.jetbrains.annotations.NotNull;

public class Gui extends JFrame {
    private JPanel mainContainer;
    private BorderLayout mainLayout;
    private GraphVisualization graphVisualization;

    private JButton buttonNext;
    private JButton buttonPrev;
    private JButton buttonStart;
    private JButton buttonEnd;
    private JButton buttonAddVertex;
    private JButton buttonAddEdge;
    private JButton buttonInputGraph;
    private JButton buttonGenerateGraph;
    private JButton buttonStop;

    private JLabel emptyLabelAddVertex;
    private JTextArea textInputAddGraph;
    private JTextField textInputLimitsForGenerateGraph;
    private JScrollPane inputGraphScrollPane;
    private JTextField textInputAddEdge;

    private JTextField inputEdgeHint;
    private JTextField inputGraphHint;
    private JTextField inputLimitsHint;


    public Gui(GraphVisualization graphVisualization){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.graphVisualization=graphVisualization;

        init();
        initWindowSettings();
        setElements();
        createMenu();
    }

    private void setElements(){

        mainContainer.setSize(900,700);
        mainContainer.setLayout(mainLayout);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();

        setEastPanel(eastPanel);
        setWestPanel(westPanel);
        setSouthPanel(southPanel);
        setMainContainer(eastPanel,westPanel,southPanel);
        setContentPane(mainContainer);
    }

    private void init(){
        mainContainer = new JPanel();
        mainLayout = new BorderLayout();

        buttonNext = new JButton(">");
        buttonPrev = new JButton("<");
        buttonStart = new JButton("START");
        buttonAddEdge = new JButton("Add edge");
        buttonAddVertex = new JButton("Add vertex");
        buttonGenerateGraph = new JButton("Generate Graph");
        textInputLimitsForGenerateGraph = new JTextField();
        buttonInputGraph = new JButton("Input graph");
        textInputAddGraph = new JTextArea();
        textInputAddEdge = new JTextField();
        textInputLimitsForGenerateGraph = new JTextField();
        emptyLabelAddVertex=new JLabel();
        inputGraphScrollPane = new JScrollPane(textInputAddGraph);
        buttonEnd = new JButton("TO END");
        buttonStop = new JButton("STOP");

        inputEdgeHint = new JTextField("Формат:<номер вершины><номер вершины><вес>");
        inputGraphHint = new JTextField("Формат:матрица смежности");
        inputLimitsHint = new JTextField("Формат:\n<количество ребер><количество вершин><мин. вес><макс. вес>");
        inputGraphHint.setEditable(false);
        inputEdgeHint.setEditable(false);
        inputLimitsHint.setEditable(false);
    }

    private void initWindowSettings(){
        setSize(1600,800);
        setResizable(false);
        setTitle("Boruvka");
    }


    private void setEastPanel(JPanel eastPanel){
        eastPanel.setBorder(new EmptyBorder(20,20,20,20));
        GridBagLayout eastLayout = new GridBagLayout();
        eastPanel.setLayout(eastLayout);

        GridBagConstraints eastConstraints = new GridBagConstraints();
        eastConstraints.fill = GridBagConstraints.BOTH;
        eastConstraints.gridx=0;
        eastConstraints.gridy=0;
        eastConstraints.weighty=1;
        eastPanel.add(buttonAddVertex,eastConstraints);

        eastConstraints.weighty=0.5;
        eastConstraints.gridy=1;
        eastPanel.add(emptyLabelAddVertex,eastConstraints);

        eastConstraints.gridy =2;
        eastPanel.add(buttonAddEdge,eastConstraints);

        eastConstraints.gridy=3;
        eastConstraints.weighty=0.1;
        eastPanel.add(inputEdgeHint,eastConstraints);

        eastConstraints.weighty=0.5;
        eastConstraints.gridy=4;
        eastPanel.add(textInputAddEdge,eastConstraints);
    }

    private void setWestPanel(JPanel westPanel){
        westPanel.setBorder(new EmptyBorder(4,4,4,4));
        GridBagLayout westLayout = new GridBagLayout();
        westPanel.setLayout(westLayout);

        GridBagConstraints westConstraints = new GridBagConstraints();
        westConstraints.weighty=0.5;
        westConstraints.fill = GridBagConstraints.BOTH;
        westConstraints.gridx=0;
        westConstraints.gridy=0;

        westPanel.add(buttonInputGraph,westConstraints);

        westConstraints.weighty=0.1;
        westConstraints.gridy=1;
        westPanel.add(inputGraphHint,westConstraints);

        westConstraints.weighty=0.5;
        westConstraints.gridy=2;
        westPanel.add(inputGraphScrollPane,westConstraints);

        westConstraints.weighty=0.1;
        westConstraints.gridy=4;
        westPanel.add(inputLimitsHint,westConstraints);

        westConstraints.weighty=0.5;
        westConstraints.gridy=3;
        westPanel.add(buttonGenerateGraph,westConstraints);

        westConstraints.gridy=5;
        westPanel.add(textInputLimitsForGenerateGraph,westConstraints);
    }

    private void setSouthPanel(JPanel southPanel){
        southPanel.setBorder(new EmptyBorder(4,4,4,4));
        GridBagLayout southLayout = new GridBagLayout();
        southPanel.setLayout(southLayout);

        GridBagConstraints southConstraints = new GridBagConstraints();
        southConstraints.weightx=0.5;
        southConstraints.fill = GridBagConstraints.BOTH;
        southConstraints.gridx=0;
        southConstraints.gridy=0;

        southPanel.add(buttonStart,southConstraints);

        southConstraints.gridx=1;
        southPanel.add(buttonPrev,southConstraints);

        southConstraints.gridx=2;
        southPanel.add(buttonNext,southConstraints);

        southConstraints.gridx=3;
        southPanel.add(buttonEnd,southConstraints);

        southConstraints.gridx=4;
        southPanel.add(buttonStop,southConstraints);
    }

    private void setMainContainer(JPanel eastPanel,JPanel westPanel,JPanel southPanel){
        mainContainer.add(graphVisualization,BorderLayout.CENTER);
        mainContainer.add(eastPanel,BorderLayout.EAST);
        mainContainer.add(westPanel,BorderLayout.WEST);
        mainContainer.add(southPanel,BorderLayout.SOUTH);
    }

    public String getTextAtInputAddGraph(){
        return textInputAddGraph.getText();
    }


    public String getTextAtInputLimits(){
        return textInputLimitsForGenerateGraph.getText();
    }

    public String getTextAtInputAddEdge(){
        return textInputAddEdge.getText();
    }

    public GraphVisualization getGraphVisualization(){
        return graphVisualization;
    }

    public void setGraphVisualization(GraphVisualization visualization){
        mainContainer.remove(graphVisualization);
        graphVisualization = visualization;
        mainContainer.add(graphVisualization,BorderLayout.CENTER);
        mainContainer.repaint();
        mainContainer.revalidate();
    }

    public void setButtonInputGraphListener(ActionListener listener){
        buttonInputGraph.addActionListener(listener);
    }

    public void setButtonInputAddVertexListener(ActionListener listener){
        buttonAddVertex.addActionListener(listener);
    }

    public void setButtonInputAddEdgeListener(ActionListener listener){
        buttonAddEdge.addActionListener(listener);
    }

    public void setButtonGenerateGraphListener(ActionListener listener){
        buttonGenerateGraph.addActionListener(listener);
    }

    public void setButtonStartListener(ActionListener listener){
        buttonStart.addActionListener(listener);
    }

    public void setButtonStopListener(ActionListener listener){
        buttonStop.addActionListener(listener);
    }

    public void setButtonNextListener(ActionListener listener){
        buttonNext.addActionListener(listener);
    }

    public void setButtonPrevListener(ActionListener listener){
        buttonPrev.addActionListener(listener);
    }

    public void setButtonEndListener(ActionListener listener){
        buttonEnd.addActionListener(listener);
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu info = new JMenu("info");
        JMenuItem information = new JMenuItem("info");
        info.add(information);

        information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Info inf = new Info();
                inf.setVisible(true);
            }
        });
        menuBar.add(info);
        setJMenuBar(menuBar);
    }

}
