package by.it.group410972.sheshtakauskas.lesson01.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_QSort.class.getResourceAsStream("dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        // Число отрезков
        int n = scanner.nextInt();
        // Число точек
        int m = scanner.nextInt();

        // Массивы для отрезков, а также для хранения отдельно началов и концов
        Segment[] segments = new Segment[n];
        int[] starts = new int[n];
        int[] stops = new int[n];

        // Считываем отрезки
        for (int i = 0; i < n; i++) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            segments[i] = new Segment(s, e);
            starts[i] = s;
            stops[i] = e;
        }

        // Считываем точки (события)
        int[] points = new int[m];
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортировка с применением быстрой сортировки (для примитивных типов используется Arrays.sort)
        Arrays.sort(starts);
        Arrays.sort(stops);

        // Для каждой точки определяем:
        // countStarts = число отрезков, у которых начало <= x
        // countStops  = число отрезков, у которых конец < x
        // Тогда число отрезков, содержащих x = countStarts - countStops.
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int x = points[i];
            int countStarts = countLessOrEqual(starts, x);
            int countStops = countLess(stops, x);
            result[i] = countStarts - countStops;
        }
        return result;
    }
    int countLessOrEqual(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= x)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
    int countLess(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < x)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            // Если отрезки заданы "в обратном порядке", можно их переупорядочить:
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start != o.start)
                return Integer.compare(this.start, o.start);
            return Integer.compare(this.stop, o.stop);

        }
    }

}
