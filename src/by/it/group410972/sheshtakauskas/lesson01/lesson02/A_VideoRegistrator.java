package by.it.group410972.sheshtakauskas.lesson01.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/*
Даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1); // рассчитываем моменты запуска с длительностью работы 1
        System.out.println(starts); // выводим моменты запуска
    }

    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration) {
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        Arrays.sort(events);
        List<Double> result = new ArrayList<>();
        int i = 0;
        int n = events.length;

        while (i < n) {
            // Берем текущее событие, как начало включения регистратора
            double start = events[i];
            result.add(start);
            // Определяем конец активности регистратора
            double finish = start + workDuration;

            // Пропускаем все события, которые попадают в интервал работы регистратора
            while (i < n && events[i] <= finish) {
                i++;
            }
        }
        return result;

        //i - это индекс события events[i]
        //Комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
        //Подготовка к жадному поглощению массива событий
        //hint: сортировка Arrays.sort обеспечит скорость алгоритма
        //C*(n log n) + C1*n = O(n log n)

        //пока есть незарегистрированные события
        //получим одно событие по левому краю
        //и запомним время старта видеокамеры
        //вычислим момент окончания работы видеокамеры
        //и теперь пропустим все покрываемые события
        //за время до конца работы, увеличивая индекс

                  //вернем итог
    }
}
