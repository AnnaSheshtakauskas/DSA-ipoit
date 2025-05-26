package by.it.group410972.sheshtakauskas.lesson01.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        int[][] memo = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return rec(one, two, 0, 0, memo);
    }
    private int rec(String one, String two, int i, int j, int[][] memo) {
        if (i == one.length())
            return two.length() - j;
        if (j == two.length())
            return one.length() - i;
        if (memo[i][j] != -1)
            return memo[i][j];

        int cost;
        if (one.charAt(i) == two.charAt(j)) {
            cost = rec(one, two, i + 1, j + 1, memo);
        } else {
            int insertion = rec(one, two, i, j + 1, memo);
            int deletion = rec(one, two, i + 1, j, memo);
            int replacement = rec(one, two, i + 1, j + 1, memo);
            cost = 1 + Math.min(insertion, Math.min(deletion, replacement));
        }
        memo[i][j] = cost;
        return cost;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));

    }
}
