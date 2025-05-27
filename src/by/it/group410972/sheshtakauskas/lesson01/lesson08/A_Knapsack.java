package by.it.group410972.sheshtakauskas.lesson01.lesson08;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt();   // вместимость рюкзака
        int n = scanner.nextInt();   // количество вариантов слитков
        int[] gold = new int[n];

        // Считываем веса слитков.
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // dp[c] - максимальный вес золота, который можно набрать при вместимости c.
        int[] dp = new int[W + 1];
        dp[0] = 0; // для 0 вместимости ответ 0

        // Вычисляем dp для всех c от 1 до W.
        for (int cap = 1; cap <= W; cap++) {
            int maxWeight = 0;
            for (int j = 0; j < n; j++) {
                if (gold[j] <= cap) {
                    int candidate = dp[cap - gold[j]] + gold[j];
                    if (candidate > maxWeight)
                        maxWeight = candidate;
                }
            }
            dp[cap] = maxWeight;
        }

        int result = dp[W];
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_Knapsack.class.getResourceAsStream("dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int result = instance.getMaxWeight(stream);
        System.out.println(result);
    }
}
