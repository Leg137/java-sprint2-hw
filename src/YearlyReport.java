import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
/**
 * Класс для работы с Годовым отчетом
 */
public class YearlyReport {
    CustomFileReader fileReader = new CustomFileReader();
    HashMap<String, ArrayList<ArrayList<String>>> reports;
    String catalogPath = ("reports");
    String reportNameFormat = "y.\\d{4}.csv";

    void readingYearlyReport() {
        reports = fileReader.readingReports(catalogPath, reportNameFormat);
    }

    void outputInfoYearlyReport() {
        if (!reports.isEmpty()) {
            reports.forEach((reportFileName, report) -> {
                int year = Integer.parseInt(reportFileName.replaceFirst("y.", "")
                        .replaceFirst(".csv", ""));
                System.out.println("Рассматриваемый год: \"" + year + "\"");
                double averageSumExpenses = 0.0;
                double averageSumProfit = 0.0;
                
                for (int i = 1; i < report.size(); i++) {

                    if (i % 2 == 0 && report.get(i - 1).get(0).equals(report.get(i).get(0))) {
                        int monthNumber = Integer.parseInt(report.get(i).get(0));
                        String monthName = Month.of(monthNumber).getDisplayName(TextStyle.FULL_STANDALONE,
                                new Locale("ru", "RUS"));
                        double expenses = 0.0;
                        double profit = 0.0;

                        if (report.get(i - 1).get(2).equals("false")) {
                            expenses = Double.parseDouble(report.get(i - 1).get(1));
                            profit = Double.parseDouble(report.get(i).get(1));

                            averageSumExpenses = averageSumExpenses + expenses;
                            averageSumProfit = averageSumProfit + profit;
                        } else if (report.get(i).get(2).equals("false")) {
                            expenses = Double.parseDouble(report.get(i).get(1));
                            profit = Double.parseDouble(report.get(i-1).get(1));

                            averageSumExpenses = averageSumExpenses + expenses;
                            averageSumProfit = averageSumProfit + profit;
                        }
                        System.out.println(" Прибыль за: \"" + monthName + "\" = " +
                                (profit - expenses));
                    }
                }
                System.out.println("  Ср. расход за год: \"" +
                        (-1 * averageSumExpenses / (report.size()- 1) + "\";"));
                System.out.println("  Ср. доход за год: \"" +
                        (averageSumProfit / (report.size()- 1) + "\"."));
            });
        } else {
            System.out.println("Перед Выводом информации о годовом отчёте необходимо:\n" +
                    "2 - Считать годовой отчёт.");
        }
    }
}