import java.util.ArrayList;
import java.util.TreeMap;
/**
 * Класс для Сверки отчётов
 */
public class ReviseReports {
    /**
     * monthlyReportsMaps - Итоговая таблица месячных отчетов;
     * yearlyReportMap - Итоговая таблица годового отчета;
     * monthlyReportMap - Форма таблицы месячных отчетов для сверки.
     */
    static void reviseSumReports(TreeMap<Integer, TreeMap<String, ArrayList<Double>>> monthlyReportsMaps, TreeMap<Integer, ArrayList<Double>> yearlyReportMap) {
        /**
         * Метод для Сверки отчётов
         */
        TreeMap<Integer, ArrayList<Double>> reviseSumMonthlyReport = new TreeMap<>();


        int monthError = 0;
        if ((monthlyReportsMaps.size() != 0) && (yearlyReportMap.size() != 0)) {

            for (Integer keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                TreeMap<String, ArrayList<Double>> monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
                double monthlySumsProfit = 0;
                double monthlySumsExpenses = 0;

                for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {

                    ArrayList<Double> arrayProfitAndExpense = monthlyReportMap.get(keyMonthlyReportMap);
                    monthlySumsProfit += arrayProfitAndExpense.get(0);
                    monthlySumsExpenses += arrayProfitAndExpense.get(1);
                }
                ArrayList<Double> arrayMonthlySums = new ArrayList<>();

                arrayMonthlySums.add(0, monthlySumsProfit);
                arrayMonthlySums.add(1, monthlySumsExpenses);

                reviseSumMonthlyReport.put(keyMonthlyReportsMaps, arrayMonthlySums);
            }
            boolean successfulRevise = false;

            for (Integer keyMonthlyReportsMaps : reviseSumMonthlyReport.keySet()) {

                ArrayList<Double> arrayMonthlySums = reviseSumMonthlyReport.get(keyMonthlyReportsMaps);

                for (Integer keyYearlyReportMap : yearlyReportMap.keySet()) {

                    ArrayList<Double> arrayYearlySums = yearlyReportMap.get(keyYearlyReportMap);
                    System.out.println(keyMonthlyReportsMaps + " = " + keyYearlyReportMap);
                    System.out.println(arrayMonthlySums.get(0) + " = " + arrayYearlySums.get(0));
                    System.out.println(arrayMonthlySums.get(1) + " = " + arrayYearlySums.get(1));
                    if ((keyMonthlyReportsMaps.equals(keyYearlyReportMap)) && (arrayMonthlySums.get(0).equals(arrayYearlySums.get(0))) &&
                            (arrayMonthlySums.get(1).equals(arrayYearlySums.get(1)))) {

                        successfulRevise = true;
                    } else {
                        monthError = keyMonthlyReportsMaps;
                    }
                }
                System.out.println("Отчет за месяц: " + calendar(monthError) + " не соответствует годовому отчету.");
            }
            if (successfulRevise) System.out.println("Сверка отчётов успешно завершена, ошибок не обнаружено.");
        } else {
            System.out.println("Перед Сверкой отчётов необходимо:\n" +
                    "1 - Считать все месячные отчёты;\n" +
                    "2 - Считать годовой отчёт.");
        }
    }

    public static String calendar(Integer month) {
        /**
         * Метод для замены номера месяца на его название
         */
        String nameMonth = null;
        switch (month) {
            case 1: nameMonth = "\"Январь\"";
                break;
            case 2: nameMonth = "\"Февраль\"";
                break;
            case 3: nameMonth = "\"Март\"";
                break;
            case 4: nameMonth = "\"Апрель\"";
                break;
            case 5: nameMonth = "\"Май\"";
                break;
            case 6: nameMonth = "\"Июнь\"";
                break;
            case 7: nameMonth = "\"Июль\"";
                break;
            case 8: nameMonth = "\"Август\"";
                break;
            case 9: nameMonth = "\"Сентябрь\"";
                break;
            case 10: nameMonth = "\"Октябрь\"";
                break;
            case 11: nameMonth = "\"Ноябрь\"";
                break;
            case 12: nameMonth = "\"Декабрь\"";
                break;
        }
        return nameMonth;
    }
}