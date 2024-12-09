import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс для работы с Месячными отчетами
 */
public class MonthlyReport {
    CustomFileReader fileReader = new CustomFileReader();
    HashMap<String, ArrayList<ArrayList<String>>> reports;
    String catalogPath = ("reports");
    String reportNameFormat = "m.\\d{6}.csv";

    void readingMonthlyReports() {
        reports = fileReader.readingReports(catalogPath, reportNameFormat);
    }

    void outputInfoMonthlyReports() {
        //Метод Вывода информации о всех месячных отчётах
        if (!reports.isEmpty()) {
            reports.forEach((reportFileName, report) -> {
                int monthNumber = Integer.parseInt(reportFileName.replaceFirst("m.\\d{4}", "")
                        .replaceFirst(".csv", ""));
                String monthName = Month.of(monthNumber).getDisplayName(TextStyle.FULL_STANDALONE,
                        new Locale("ru", "RUS"));

                String maxProfitableItem = "не известен";
                String maxUnProfitableItem = "не известен";
                double maxProfit = 0.0;
                double maxUnProfit = 0.0;

                for (int i = 1; i < report.size(); i++) {
                    String itemName = report.get(i).get(0);
                    String isExpense = report.get(i).get(1).toLowerCase();
                    int quantity = Integer.parseInt(report.get(i).get(2));
                    double unitPrice = Double.parseDouble(report.get(i).get(3));
                    double multiplication = quantity * unitPrice;

                    if (isExpense.equals("true") && (multiplication > maxProfit)) {
                        maxProfit = multiplication;
                        maxProfitableItem = itemName;
                    } else if (isExpense.equals("false") && (multiplication > maxUnProfit)) {
                        maxUnProfit = multiplication;
                        maxUnProfitableItem = itemName;
                    }
                }
                System.out.println("Месяц: " + monthName);
                System.out.println(" Самый прибыльный товар: \"" + maxProfitableItem + "\" на сумму: \"" +
                        maxProfit + "\"");
                System.out.println(" Самый не прибыльный товар: \"" + maxUnProfitableItem + "\" на сумму: \"" +
                        -maxUnProfit + "\"");
            });
        } else {
            System.out.println("Перед Выводом информации о всех месячных отчётах необходимо:\n" +
                    "1 - Считать все месячные отчёты.");
        }
    }
}