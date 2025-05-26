package by.it.group410972.sheshtakauskas.lesson01.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = B_Huffman.class.getResourceAsStream("dataB.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(inputStream);
        System.out.println(result);
    }

    String decode(InputStream inputStream) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputStream);

        // Считываем количество различных букв и длину закодированной строки
        int count = scanner.nextInt();
        int length = scanner.nextInt();
        // После считывания чисел переходим к началу следующей строки
        scanner.nextLine();

        // Считываем таблицу кодов: каждая строка имеет вид "буква: код"
        // Строим отображение "код" -> "буква", которое удобно использовать при декодировании
        Map<String, Character> codeToLetter = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine().trim();
            // Предполагается, что строка имеет формат "a: 0"
            // Здесь letter — это первый символ, а код — всё, что идёт после ": "
            char letter = line.charAt(0);
            String code = line.substring(3); // пропускаем букву, двоеточие и пробел
            codeToLetter.put(code, letter);
        }

        // Считываем закодированную строку
        String encoded = scanner.nextLine().trim();

        // Декодирование: проходим по закодированной строке, накапливая биты в currentCode,
        // пока накопленный префикс не встретится в отображении codeToLetter.
        StringBuilder result = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            currentCode.append(encoded.charAt(i));
            if (codeToLetter.containsKey(currentCode.toString())) {
                // Если найден соответствующий код, добавляем букву и сбрасываем накопленный префикс
                result.append(codeToLetter.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }

        return result.toString();
    }


}
