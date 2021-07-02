package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import org.jetbrains.annotations.NotNull;


public class Gui extends JFrame {
    private JPanel mainContainer;
    private BorderLayout mainLayout;
    private JPanel graphPanel;

    private JButton buttonNext;
    private JButton buttonPrev;
    private JButton buttonStart;
    private JButton buttonAddVertex;
    private JButton buttonDeleteVertex;
    private JButton buttonAddEdge;
    private JButton buttonDeleteEdge;
    private JButton buttonInputGraph;
    private JButton buttonClearGraph;

    private JTextField textInputAddEdge;
    private JTextField textInputDeleteEdge;
    private JLabel emptyLabelAddVertex;
    private JTextField textInputDeleteVertex;
    private JTextArea textInputAddGraph;
    private JScrollPane inputGraphScrollPane;

    public Gui(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        initWindowSettings();
        setElements();
        setButtonListeners();
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


        graphPanel=new JPanel();
        graphPanel.setBackground(Color.GRAY);

        buttonNext = new JButton(">");
        buttonPrev = new JButton("<");
        buttonStart = new JButton("START");
        buttonAddEdge = new JButton("Add edge");
        buttonAddVertex = new JButton("Add vertex");
        buttonDeleteEdge = new JButton("Delete edge");
        buttonDeleteVertex = new JButton("Delete vertex");
        buttonClearGraph  = new JButton("Generate Graph");
        buttonInputGraph = new JButton("Input graph");

        textInputAddEdge = new JTextField();
        textInputAddGraph = new JTextArea();
        emptyLabelAddVertex=new JLabel();
        textInputDeleteEdge = new JTextField();
        textInputDeleteVertex = new JTextField();
        inputGraphScrollPane = new JScrollPane(textInputAddGraph);
    }

    private void initWindowSettings(){
        setSize(900,700);
        setResizable(false);
        setTitle("Boruvka");
    }


    private void setEastPanel(@NotNull JPanel eastPanel){
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
        eastPanel.add(textInputAddEdge,eastConstraints);

        eastConstraints.gridy=4;
        eastPanel.add(buttonDeleteVertex,eastConstraints);

        eastConstraints.gridy=5;
        eastPanel.add(textInputDeleteVertex,eastConstraints);

        eastConstraints.gridy=6;
        eastPanel.add(buttonDeleteEdge,eastConstraints);

        eastConstraints.gridy=7;
        eastPanel.add(textInputDeleteEdge,eastConstraints);
    }

    private void setWestPanel(@NotNull JPanel westPanel){
        westPanel.setBorder(new EmptyBorder(4,4,4,4));
        GridBagLayout westLayout = new GridBagLayout();
        westPanel.setLayout(westLayout);

        GridBagConstraints westConstraints = new GridBagConstraints();
        westConstraints.weighty=0.5;
        westConstraints.fill = GridBagConstraints.BOTH;
        westConstraints.gridx=0;
        westConstraints.gridy=0;

        westPanel.add(buttonInputGraph,westConstraints);

        westConstraints.gridy=1;
        westPanel.add(inputGraphScrollPane,westConstraints);

        westConstraints.gridy=2;
        westPanel.add(buttonClearGraph,westConstraints);
    }

    private void setSouthPanel(@NotNull JPanel southPanel){
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
    }

    private void setMainContainer(JPanel eastPanel,JPanel westPanel,JPanel southPanel){
        mainContainer.add(graphPanel,BorderLayout.CENTER);
        mainContainer.add(eastPanel,BorderLayout.EAST);
        mainContainer.add(westPanel,BorderLayout.WEST);
        mainContainer.add(southPanel,BorderLayout.SOUTH);
    }

    private void setButtonListeners(){

    }


}
