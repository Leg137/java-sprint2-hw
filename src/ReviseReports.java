import java.util.TreeMap;

public class ReviseReports {

    void reviseSumReports(TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps, TreeMap<String, Double[]> yearlyReportMap) {

        TreeMap<String, Double[]> reviseSumMonthlyReport = new TreeMap<>(); //
        double monthlySumsProfit;
        double monthlySumsExpenses;
        Double[] massiveMonthlySums;
        TreeMap<String, String[]> monthlyReportMap; //Форма таблицы месячных отчетов для сверки

        if ((monthlyReportsMaps.size() != 0) && (yearlyReportMap.size() != 0)) {

            for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
                monthlySumsProfit = 0;
                monthlySumsExpenses = 0;

                for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {

                    String[] itemName = monthlyReportMap.get(keyMonthlyReportMap);

                    if (itemName[1].equalsIgnoreCase("False")) {

                        monthlySumsProfit += Double.parseDouble(itemName[2]) * Double.parseDouble(itemName[3]);

                    } else if (itemName[1].equalsIgnoreCase("True")) {

                        monthlySumsExpenses += Double.parseDouble(itemName[2]) * Double.parseDouble(itemName[3]);
                    }
                }
                massiveMonthlySums = new Double[2];

                massiveMonthlySums[0] = monthlySumsProfit;
                massiveMonthlySums[1] = -monthlySumsExpenses;

                reviseSumMonthlyReport.put(keyMonthlyReportsMaps, massiveMonthlySums); ////Сохраняем таблицу с месячными данными по ключу - номер месяца
            }

            for (String keyMonthlyReportsMaps : reviseSumMonthlyReport.keySet()) { //Сверка данных месячных отчетов и годового отчета

                Double[] reviseSumsMonthlyReport = reviseSumMonthlyReport.get(keyMonthlyReportsMaps);

                for (String keyMonthNumber : yearlyReportMap.keySet()) {

                    Double[] reviseYearlyReport = yearlyReportMap.get(keyMonthNumber);

                    if ((keyMonthlyReportsMaps.equals(keyMonthNumber)) && (reviseSumsMonthlyReport[0].equals(reviseYearlyReport[0])) &&
                            (reviseSumsMonthlyReport[1].equals(reviseYearlyReport[1]))) {

                        System.out.println("Сверка отчётов успешно завершена, ошибок не обнаружено.");

                    } else if (keyMonthlyReportsMaps.equals(keyMonthNumber)) {

                        System.out.println("Отчет за " + keyMonthlyReportsMaps + " месяц не соответствует годовому отчету.");
                    }
                }
            }
        } else {
            System.out.println("Перед Сверкой отчётов необходимо:\n" +
                    "1 - Считать все месячные отчёты;\n" +
                    "2 - Считать годовой отчёт.");
        }
    }

    public static String calendar(String month) { //Метод для замены номера месяца на его название

        String nameMonth = null;
        switch (month) {
            case "01": nameMonth = "\"Январь\"";
                       break;
            case "02": nameMonth = "\"Февраль\"";
                       break;
            case "03": nameMonth = "\"Март\"";
                       break;
            case "04": nameMonth = "\"Апрель\"";
                       break;
            case "05": nameMonth = "\"Май\"";
                       break;
            case "06": nameMonth = "\"Июнь\"";
                       break;
            case "07": nameMonth = "\"Июль\"";
                       break;
            case "08": nameMonth = "\"Август\"";
                       break;
            case "09": nameMonth = "\"Сентябрь\"";
                       break;
            case "10": nameMonth = "\"Октябрь\"";
                       break;
            case "11": nameMonth = "\"Ноябрь\"";
                       break;
            case "12": nameMonth = "\"Декабрь\"";
                       break;
        }
        return nameMonth;
    }
}