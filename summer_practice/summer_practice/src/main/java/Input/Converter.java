package Input;

import java.util.ArrayList;
import Graph.Edge;
public class Converter {
    public static ArrayList<ArrayList<Integer>> convertStringMatrix(String stringMatrix){//преобразует введенную строковую матрицу в ArrayList<ArrayList<>>
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        String[] splitMatrix = stringMatrix.split("\n");

        for(int i = 0 ;i<splitMatrix.length;i++){
            matrix.add(new ArrayList<>());
            String[] row = splitMatrix[i].split(" ");
            for(int j = 0 ;j<row.length;j++){
                if(row[j].equals("-")){
                    matrix.get(i).add(null);
                }else {
                    matrix.get(i).add(Integer.parseInt(row[j]));
                }
            }
        }
        return matrix;
    }

    public static Edge convertStringEdge(String stringEdge){
        String[] split = stringEdge.split(" ");
        return new Edge(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2]));
    }

    public static int[] convertStringLimits(String stringLimits){
        int[] limits = new int[4];
        String[] splitLimits = stringLimits.split(" ");
        for(int i = 0 ;i<4;i++){
            limits[i] = Integer.parseInt(splitLimits[i]);
        }
        return limits;
    }

}
