import java.util.ArrayList;
import java.util.List;

class Answer {
    public static StringBuilder answer(String QUERY, String PARAMS) {
        // Напишите свое решение ниже
        List<String> filter = new ArrayList<>();
        String[] params = PARAMS.replace("{", "")
                .replace("}", "")
                .replace("\"", "")
                .split(",");

        for (String p : params) {
            String[] kv = p.split(":");
            if (kv.length == 2) {
                String k = kv[0].trim();
                String v = kv[1].trim();

                if (!"null".equals(k) && !"null".equals(v)) {
                    filter.add(k + "=" + "'" + v + "'");
                }
            }
        }
        return new StringBuilder(QUERY).append(String.join(" and ", filter));
    }
}

public class PrinterSql {
    public static void main(String[] args) {
        String QUERY = "";
        String PARAMS = "";

        if (args.length == 0) {
            // При отправке кода на Выполнение, вы можете варьировать эти параметры
            QUERY = "select * from students where ";
            PARAMS = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"} ";
        } else {
            QUERY = args[0];
            PARAMS = args[1];
        }

        Answer ans = new Answer();
        System.out.println(ans.answer(QUERY, PARAMS));
    }
}
