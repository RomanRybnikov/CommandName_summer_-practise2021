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
    private JButton buttonEnd;
    private JButton buttonAddVertex;
    private JButton buttonAddEdge;
    private JButton buttonInputGraph;
    private JButton buttonGenerateGraph;

    private JLabel emptyLabelAddVertex;
    private JTextArea textInputAddGraph;
    private JTextField textInputLimitsForGenerateGraph;
    private JScrollPane inputGraphScrollPane;
    private JTextField textInputAddEdge;

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
        buttonGenerateGraph = new JButton("Generate Graph");
        textInputLimitsForGenerateGraph = new JTextField();
        buttonInputGraph = new JButton("Input graph");

        textInputAddGraph = new JTextArea();
        textInputAddEdge = new JTextField();
        emptyLabelAddVertex=new JLabel();
        inputGraphScrollPane = new JScrollPane(textInputAddGraph);
        buttonEnd = new JButton("END");

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
        westPanel.add(buttonGenerateGraph,westConstraints);
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

        southConstraints.gridx=3;
        southPanel.add(buttonEnd,southConstraints);
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
