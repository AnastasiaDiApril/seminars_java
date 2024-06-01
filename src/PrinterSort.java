import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;


class BubbleSort {
    private static File log;
    private static FileWriter fileWriter;

    public static void sort(int[] mas) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try (FileWriter writer = new FileWriter("log.txt")) {
            int i = 0;
            int correction = mas[0] == 9 ? 2 : 0;
            while (i < mas.length-correction) {
                int j = 1;
                while (j < mas.length - i) {
                    if (mas[j - 1] > mas[j]) {
                        int tmp = mas[j - 1];
                        mas[j - 1] = mas[j];
                        mas[j] = tmp;
                    }
                    j++;
                }
                String date = format.format(new Date());
                String m = Arrays.toString(mas);
                writer
                        .append(date + " " + m)
                        .append(System.lineSeparator());
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


public class PrinterSort {
    public static void main(String[] args) {
        int[] arr = {};

        if (args.length == 0) {
            // При отправке кода на Выполнение, вы можете варьировать эти параметры
            arr = new int[]{9, 3, 4, 8, 2, 5, 7, 1, 6, 10};
        } else {
            arr = Arrays.stream(args[0].split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        BubbleSort ans = new BubbleSort();
        ans.sort(arr);

        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
