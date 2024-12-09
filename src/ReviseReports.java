import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Класс для Сверки отчётов
 */
public class ReviseReports {

    void reviseSumReports(HashMap<String, ArrayList<ArrayList<String>>> monthlyReports,
                                 HashMap<String, ArrayList<ArrayList<String>>> yearlyReports) {
        //Метод для Сверки отчётов
        if ((!monthlyReports.isEmpty()) && (!yearlyReports.isEmpty())) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entryYearlyReports : yearlyReports.entrySet()) {
                ArrayList<ArrayList<String>> yearlyReport = entryYearlyReports.getValue();
                String yearlyReportFileName = entryYearlyReports.getKey();
                int year = Integer.parseInt(yearlyReportFileName.replaceFirst("y.", "")
                        .replaceFirst(".csv", ""));
                System.out.println("Рассматриваемый год: \"" + year + "\"");

                for (int i = 1; i < yearlyReport.size(); i++) {

                    if (i % 2 == 0 && yearlyReport.get(i - 1).get(0).equals(yearlyReport.get(i).get(0))) {
                        int monthNumber = Integer.parseInt(yearlyReport.get(i).get(0));
                        Month month = Month.of(monthNumber);
                        String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE,
                                new Locale("ru", "RUS"));
                        System.out.println(" Месяц: \"" + monthName + "\"");

                        double expensesFromYearlyReport = 0.0;
                        double profitFromYearlyReport = 0.0;

                        if (yearlyReport.get(i - 1).get(2).equals("false")) {
                            expensesFromYearlyReport = Double.parseDouble(yearlyReport.get(i - 1).get(1));
                            profitFromYearlyReport = Double.parseDouble(yearlyReport.get(i).get(1));
                        } else if (yearlyReport.get(i).get(2).equals("false")) {
                            expensesFromYearlyReport = Double.parseDouble(yearlyReport.get(i).get(1));
                            profitFromYearlyReport = Double.parseDouble(yearlyReport.get(i - 1).get(1));
                        }
                        System.out.println("  Расходы: \"" + expensesFromYearlyReport + "\"");
                        System.out.println("  Доходы: \"" + profitFromYearlyReport + "\"");

                        compareWithMonthlyReports(monthlyReports, year, monthNumber, expensesFromYearlyReport,
                                monthName, profitFromYearlyReport);
                    }
                }
            }
        } else {
            System.out.println("Перед Сверкой отчётов необходимо:\n" +
                    "1 - Считать все месячные отчёты;\n" +
                    "2 - Считать все годовые отчёты.");
        }
    }

    void compareWithMonthlyReports(HashMap<String, ArrayList<ArrayList<String>>> monthlyReports, int year,
                                   int monthNumber, double expensesFromYearlyReport, String monthName,
                                   double profitFromYearlyReport) {
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entryMonthlyReports : monthlyReports.entrySet()) {
            String monthlyReportFileName = entryMonthlyReports.getKey();
            String numberMonth = String.format("m.%d0%d.csv", year, monthNumber);
            String doubleNumberMonth = String.format("m.%d%d.csv", year, monthNumber);

            if (monthlyReportFileName.matches(numberMonth) || monthlyReportFileName.matches(doubleNumberMonth)) {
                ArrayList<ArrayList<String>> monthlyReport = entryMonthlyReports.getValue();
                double expensesFromMonthlyReport = 0.0;
                double profitFromMonthlyReport = 0.0;

                for (int j = 1; j < monthlyReport.size(); j++) {
                    String isExpense = monthlyReport.get(j).get(1).toLowerCase();
                    int quantity = Integer.parseInt(monthlyReport.get(j).get(2));
                    double unitPrice = Double.parseDouble(monthlyReport.get(j).get(3));
                    double amount = quantity * unitPrice;

                    if (isExpense.equals("true")) {
                        profitFromMonthlyReport = profitFromMonthlyReport + amount;
                    } else if (isExpense.equals("false")) {
                        expensesFromMonthlyReport = expensesFromMonthlyReport + amount;
                    }
                }

                if (expensesFromYearlyReport != expensesFromMonthlyReport) {
                    System.out.println("Обнаружено несоответствие в отчете за \"" + monthName + "\"");
                } else if (profitFromYearlyReport != profitFromMonthlyReport) {
                    System.out.println("Обнаружено несоответствие в отчете за \"" + monthName + "\"");
                }

                System.out.println("  Расходы: \"" + expensesFromMonthlyReport + "\"");
                System.out.println("  Доходы: \"" + profitFromMonthlyReport + "\"");
            }
        }
    }
}