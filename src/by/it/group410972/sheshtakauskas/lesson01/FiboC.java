package by.it.group410972.sheshtakauskas.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d) = %d \n\t time = %d ms\n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    long fasterC(long n, int m) {
        //Интуитивно найти решение не всегда просто и
        //возможно потребуется дополнительный поиск информации
        return fibPair(n, m)[0];
    }
    private long[] fibPair(long n, int m) {
        if (n == 0) {
            return new long[]{0, 1};
        }
        long[] half = fibPair(n / 2, m);
        long a = half[0];
        long b = half[1];
        long calc = ((2 * b) % m - a + m) % m;
        long c = (a * calc) % m;
        long d = ((a * a) % m + (b * b) % m) % m;
        if (n % 2 == 0) {
            return new long[]{c, d};
        } else {
            return new long[]{d, (c + d) % m};
        }
    }
}

