package Input;

import java.util.ArrayList;

public class CheckingCorrect {

    private static boolean isEmptyMatrix(String matrix){
        return matrix.isEmpty();
    }
    private static boolean checkSymbolsAtStringMatrix(String matrix,ArrayList<ArrayList<Integer>> intMatrix,String[] stringMatrix){
        for(int i = 0 ;i<matrix.length();i++){
            char c = matrix.charAt(i);
            if(!(c>='0' && c<='9' || c == ' ' || c=='\n' || c=='-') ){
                return false;
            }
        }

        for(int i = 0 ;i<stringMatrix.length;i++){
            intMatrix.add(new ArrayList<>());
            String[] row = stringMatrix[i].split(" ");
            for(int j = 0 ;j<row.length;j++){
                try{
                    if(row[j].equals("-")){
                        intMatrix.get(i).add(null);
                    }
                    else {
                        intMatrix.get(i).add(Integer.parseInt(row[j]));
                    }
                }catch (NumberFormatException e){
                    return false;
                }

            }
        }

        return true;
    }
    private static boolean checkCorrectSizeMatrix(ArrayList<ArrayList<Integer>> intMatrix){
        int size=intMatrix.size();
        for(ArrayList<Integer> list:intMatrix){
            if(list.size()!=size){
                return false;
            }
        }
        return true;
    }

    public static boolean checkCorrectStringMatrix(String matrix){
        if(isEmptyMatrix(matrix)){
            return false;
        }

        String[] stringMatrix = matrix.split("\n");
        ArrayList<ArrayList<Integer>> intMatrix = new ArrayList<>();
        matrix=matrix.trim();

        if(!checkSymbolsAtStringMatrix(matrix,intMatrix,stringMatrix)){
            return false;
        }

        if(!checkCorrectSizeMatrix(intMatrix)){
            return false;
        }

        return true;
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



}
