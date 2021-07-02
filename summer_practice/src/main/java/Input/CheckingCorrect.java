package Input;

import java.util.ArrayList;

public class CheckingCorrect {
    public static boolean checkCorrectStringMatrix(String matrix){
        if(matrix.isEmpty()){
            return false;
        }
        matrix=matrix.trim();
        for(int i = 0 ;i<matrix.length();i++){
            char c = matrix.charAt(i);
            if(!(c>='0' && c<='9' || c == ' ' || c=='\n' || c=='-') ){
                return false;
            }
        }
        String[] stringMatrix = matrix.split("\n");
        ArrayList<ArrayList<Integer>> intMatrix = new ArrayList<>();
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

        int size=intMatrix.size();
        for(ArrayList<Integer> list:intMatrix){
            if(list.size()!=size){
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

}
