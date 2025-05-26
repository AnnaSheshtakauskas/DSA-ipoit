package by.it.group410972.sheshtakauskas.lesson01.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        int[] points = new int[m];
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSort(segments, 0, n - 1);


        int[] startArr = new int[n];
        int[] stopArr = new int[n];
        for (int i = 0; i < n; i++) {
            startArr[i] = segments[i].start;
            stopArr[i] = segments[i].stop;
        }
        Arrays.sort(stopArr);
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int x = points[i];
            int countStarts = countLessOrEqual(startArr, x);
            int countStops   = countLess(stopArr, x);
            result[i] = countStarts - countStops;
        }

        return result;
    }
    int countLessOrEqual(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
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
            int mid = left + (right - left) / 2;
            if (arr[mid] < x)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
    void quickSort(Segment[] arr, int l, int r) {
        while (l < r) {
            int lt = l, gt = r;
            Segment pivot = arr[l];
            int i = l;
            while (i <= gt) {
                int cmp = arr[i].compareTo(pivot);
                if (cmp < 0) {
                    swap(arr, lt, i);
                    lt++;
                    i++;
                } else if (cmp > 0) {
                    swap(arr, i, gt);
                    gt--;
                } else {
                    i++;
                }
            }
            // Рекурсивно сортируем меньшую часть
            quickSort(arr, l, lt - 1);
            // Переключаемся на правую часть, используя устранение хвостовой рекурсии
            l = gt + 1;
        }
    }
    void swap(Segment[] arr, int i, int j) {
        Segment tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start != o.start)
                return this.start - o.start;
            return this.stop - o.stop;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + stop + "]";
        }
    }

}
