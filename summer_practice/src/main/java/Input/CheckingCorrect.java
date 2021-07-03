package Input;

import java.util.ArrayList;

public class CheckingCorrect {
    private static boolean isEmptyMatrix(String matrix){
        if (matrix.isEmpty()) return false;
        return true;
    }

    private static boolean checkSymbolsAtStringMatrix(String matrix){
        for (int i = 0; i < matrix.length(); i++){
            char c = matrix.charAt(i);
            if(!(c>='0' && c<='9' || c == ' ' || c=='\n' || c=='-') ){
                return false;
            }
        }
        return true;
    }

//    public static void fillMatrix...(){}  тут я бы хотел заменить всё с первого for-a ниже

    private static boolean checkSizeMatrix(ArrayList<ArrayList<Integer>> intMatrix, int size){
        for (ArrayList<Integer>list : intMatrix) {
            if (list.size() != size) return false;
        }
        return true;
    }

    private static boolean checkCorrectStringMatrix(String matrix){
        isEmptyMatrix(matrix);
        matrix = matrix.trim();
        checkSymbolsAtStringMatrix(matrix);

        String[] stringMatrix = matrix.split("\n");
        ArrayList<ArrayList<Integer>> intMatrix = new ArrayList<>();

        for(int i = 0; i<stringMatrix.length; i++){
            intMatrix.add(new ArrayList<>());
            String[] row = stringMatrix[i].split(" ");
            for(int j = 0; j < row.length; j++){
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

        int size = intMatrix.size();
        checkSizeMatrix(intMatrix, size);

        return true;
    }


    private static boolean checkEmptyMatrix(ArrayList<ArrayList<Integer>> matrix){
        if(matrix == null) return  false;
        return  true;
    }

    private static boolean checkSquareMatrix(ArrayList<ArrayList<Integer>> matrix, int sizeMatrix){
        for(ArrayList<Integer> list:matrix){//проверка на квадратную матрицу
            if(list.size() != sizeMatrix){
                return false;
            }
        }
        return true;
    }

    private static boolean checkSymmetryMatrix(ArrayList<ArrayList<Integer>> matrix, int sizeMatrix){
        for(int i = 0 ;i<matrix.size();i++) {//проверка на симметричность матрицы
            for(int j = i+1 ;j<matrix.size();j++){
                if(matrix.get(i).get(j) != matrix.get(j).get(i)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkCorrectMatrix(ArrayList<ArrayList<Integer>> matrix){
        checkEmptyMatrix(matrix);
        int size = matrix.size();
        checkSquareMatrix(matrix, size);
        checkSymmetryMatrix(matrix, size);
        return true;
    }

}
