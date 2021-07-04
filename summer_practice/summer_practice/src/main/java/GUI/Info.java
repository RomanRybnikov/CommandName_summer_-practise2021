package GUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Info extends JFrame {
    public Info(){
        setSize(800,700);
        JTextArea label= new JTextArea("  Данное приложение предназначено для визуализации алгоритма Борувки на графе.\n\n" +
                "   Ввести граф можно, используя кнопку \"Input graph\" и соответствующее поле для ввода матрицы смежности.\n" +
                "   Граф неориентированный, поэтому матрица смежности должна быть симметричная.\n" +
                "   Веса матрицы смежности должны быть неотрицательными целыми числами, при отстутствии ребра между вершинами следует\n" +
                "ставить символ \"-\" . Петлей в графе нет, поэтому по диагонали должен стоять этот символ\n" +
                "   Пример: \n- 1 2 1 4\n" +
                "1 - 2 3 5\n" +
                "2 2 - - -\n" +
                "1 3 - - 2\n" +
                "4 5 - 2 -\n\n" +
                "   В граф можно добавить вершину, нажав на кнопку \"Add vertex\".\n\n" +
                "   В граф можно добавить ребро, заполнив соответствующее поле и нажав на кнопку \"Add edge\".\n" +
                "   Формат ввода ввершины: <номер вершины> <номер вершины> <вес ребра>.\n" +
                "   Номера вершин можно выбирать только из имеющихся, веса должны быть неотрицательными целыми числами.\n" +
                "   Пример: 0 1 2\n\n" +
                "   Для удаления вершины или ребра необходимо нажать на соответствующий элемент левой кнопкой мыши и выбрать пункт \"remove\" \n\n" +
                "   С помощью кнопки \"Generate graph\" можно сгенерировать граф, задав в соответствующем поле границы графа, а именно:\n" +
                "       -Число ребер - должно быть >= n-1 и <= n*(n-1)/2, где n - количество вершин.\n" +
                "       -Число вершин - должно быть >2\n" +
                "       -Минимальный вес ребра\n" +
                "       -Максимальный вес ребра\n" +
                "   Формат: <число ребер> <число вершин> <минимальный вес ребра> <максимальный вес ребра>\n" +
                "   Пример: 30 14 2 10\n\n" +
                "   Начать работу алгоритма можно, нажав кнопку \"START\", при этом должен быть готов связный граф.\n" +
                "   После нажатия на кнопку \"START\" можно по шагам просматривать ход алгоритма, нажимая на стрелочки. \n" +
                "   Для перехода к концу работы алгоритма можно нажать \"TO END\" \n" +
                "   Для завершения алгоритма можно нажать кнопку \"STOP\"");
        label.setEditable(false);
        add(label);
    }
}
