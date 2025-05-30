package by.it.group410972.sheshtakauskas.lesson01.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

public class C_GreedyKnapsack {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = C_GreedyKnapsack.class.getResourceAsStream("greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }

    double calc(InputStream inputStream) throws FileNotFoundException {
        Scanner input = new Scanner(inputStream);
        // первым числом идет количество предметов, вторым – вместимость рюкзака
        int n = input.nextInt();      // число предметов
        int W = input.nextInt();      // вместимость рюкзака (W)
        Item[] items = new Item[n];   // массив предметов
        for (int i = 0; i < n; i++) { // создаём каждый предмет конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Выводим полученные предметы для контроля
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Вместимость рюкзака: %d кг.\n", n, W);

        Arrays.sort(items);

        double result = 0;
        int remainingWeight = W;
        for (int i = 0; i < n && remainingWeight > 0; i++) {
            if (items[i].weight <= remainingWeight) {
                // Можно взять предмет целиком
                remainingWeight -= items[i].weight;
                result += items[i].cost;
            } else {
                // Берем оставшуюся часть предмета пропорционально оставшейся вместимости
                result += items[i].cost * ((double) remainingWeight / items[i].weight);
                remainingWeight = 0;
            }
        }
        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                   "cost=" + cost +
                   ", weight=" + weight +
                   '}';
        }

        @Override
        public int compareTo(Item o) {
            //тут может быть ваш компаратор

            double thisRatio = (double) this.cost / this.weight;
            double otherRatio = (double) o.cost / o.weight;
            return Double.compare(otherRatio, thisRatio);
        }
    }
}