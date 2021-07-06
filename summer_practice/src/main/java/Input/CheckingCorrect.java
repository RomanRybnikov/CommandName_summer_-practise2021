package Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

import Graph.*;

public class CheckingCorrect {

    private static boolean isEmptyMatrix(String matrix){
        return matrix.isEmpty();
    }
    private static boolean checkSymbolsAtStringMatrix(String matrix,String[] stringMatrix){
        for(int i = 0 ;i<matrix.length();i++){
            char c = matrix.charAt(i);
            if(!(c>='0' && c<='9' || c == ' ' || c=='\n' || c=='-') ){
                return false;
            }
        }

        for(int i = 0 ;i<stringMatrix.length;i++){
            String[] line = stringMatrix[i].split(" ");
            for(int j = 0;j<line.length;j++ ) {
                try {
                    if (!line[j].equals("-")){
                        Integer.parseInt(line[j]);
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        return true;
    }
    private static boolean checkCorrectSizeMatrix(String[] stringMatrix){

        int size=stringMatrix.length;
        for(int i = 0 ;i<stringMatrix.length;i++){
            String[] line = stringMatrix[i].split(" ");
            if(line.length!=size){
                return false;
            }
        }
        return true;
    }
    private static boolean checkDiagonal(String[] stringMatrix){
        for(int i = 0 ;i<stringMatrix.length;i++){
            String[] line = stringMatrix[i].split(" ");
            if(!line[i].equals("-")){
                return false;
            }
        }
        return true;
    }

    public static boolean checkCorrectStringMatrix(String matrix){
        matrix=matrix.trim();
        String[] stringMatrix = matrix.split("\n");
        return !isEmptyMatrix(matrix) && checkSymbolsAtStringMatrix(matrix,stringMatrix) && checkCorrectSizeMatrix(stringMatrix) && checkDiagonal(stringMatrix);
    }

    public static boolean checkingCorrectStringEdge(String edge){
        String[] edgeSplit = edge.split(" ");
        if(edgeSplit.length!=3){
            return false;
        }
        for(int i = 0; i<3;i++){
            try{
                Integer.parseInt(edgeSplit[i]);
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean checkCorrectMatrix(ArrayList<ArrayList<Integer>> matrix){
        if(matrix==null){
            return false;
        }

        int size=matrix.size();
        for(ArrayList<Integer> list:matrix){//проверка на квадратную матрицу
            if(list.size()!=size){
                return false;
            }
        }

        for(int i = 0 ;i<matrix.size();i++)//проверка на симметричность матрицы
        {
            for(int j = i+1 ;j<matrix.size();j++){
                if(matrix.get(i).get(j) != matrix.get(j).get(i)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkCorrectStringLimits(String limits){
        String[] splitLimits = limits.split(" ");
        if(splitLimits.length!=4){
            return false;
        }
        for(int i = 0 ;i<4;i++){
            try {
                Integer.parseInt(splitLimits[i]);
            }catch (NumberFormatException ex){
                return false;
            }
        }
        return true;
    }

    public static boolean checkCorrectGraphForBoruvka(Graph graph){//проверка на то что граф связный
        if(graph.getVertexes().size()<2){
            return false;
        }

        boolean[] added = new boolean[graph.getVertexes().size()];
        Arrays.fill(added,false);
        LinkedList<Integer> vertexQueue = new LinkedList<Integer>();

        Integer vertex = graph.getEdges().get(0).getVertex1();
        added[graph.getVertexes().indexOf(vertex)] = true;
        vertexQueue.addLast(vertex);
        do{
            vertex = vertexQueue.pollFirst();
            for (Edge edge : graph.getEdges()) {
                if (edge.getVertex1() == vertex || edge.getVertex2() == vertex) {
                    int v = (edge.getVertex1() == vertex) ? edge.getVertex2() : edge.getVertex1();
                    int index = graph.getVertexes().indexOf(v);
                    if (!added[index]) {
                        added[index] = true;
                        vertexQueue.addLast(v);
                    }
                }
            }
        }while(!vertexQueue.isEmpty());

        for(int i = 0 ; i<added.length;i++){
            if(!added[i]){
                return false;
            }
        }
        return true;
    }
}
