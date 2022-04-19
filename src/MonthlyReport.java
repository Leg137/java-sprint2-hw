import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
/**
 * Класс для работы с Месячными отчетами
 */
public class MonthlyReport {
    /**
     * monthlyReportsMaps - Форма итоговой таблицы месячных отчетов;
     * pathCatalogBy_Months - Каталог с месячными отчетами;
     * formatMonthlyReport - Формат месячных отчетов.
     */
    TreeMap<Integer, TreeMap<String, ArrayList<Double>>> monthlyReportsMaps = new TreeMap<>();
    static String pathCatalogBy_Months = ("Reports/By Months");
    static String formatMonthlyReport = "m.\\d{6}.csv";
    static String patternFormatMonthlyReport = "m.YYYYMM.csv";
    File catalogByMonths;
    String itemName;
    String isExpense;
    double quantity;
    double sum;

    void readingMonthlyReports() {
        /**
         * Метод для Считывания всех месячных отчётов
         */
        catalogByMonths = new File(pathCatalogBy_Months);

        try {
            File[] pathsMonthlyReports = catalogByMonths.listFiles((dir, name) -> name.matches(formatMonthlyReport));

            if (pathsMonthlyReports != null && pathsMonthlyReports.length != 0 && pathsMonthlyReports.length < 13) {


                for (File pathFileReport : pathsMonthlyReports) {

                    Integer monthNumber = Integer.parseInt(pathFileReport.getName().replaceFirst("m.\\d{4}", "")
                            .replaceFirst(".csv", ""));
                    TreeMap<String, ArrayList<Double>> monthlyReportMap = new TreeMap<>();
                    BufferedReader readerMonthlyReport = new BufferedReader(new FileReader(pathFileReport));
                    String lineMonthlyReport;

                    while ((lineMonthlyReport = readerMonthlyReport.readLine()) != null) {

                        String[] massivLinesMonthlyReport = lineMonthlyReport.split(",");
                        ArrayList<String> arrayLinesMonthlyReport = new ArrayList<>();

                        for (int i = 0; i < massivLinesMonthlyReport.length; i++) {

                            arrayLinesMonthlyReport.add(i, massivLinesMonthlyReport[i]);
                        }
                        itemName = arrayLinesMonthlyReport.get(0);
                        isExpense = arrayLinesMonthlyReport.get(1);
                        ArrayList<Double> arrayProfitAndExpense = new ArrayList<>(2);
                        arrayProfitAndExpense.add(0.0);
                        arrayProfitAndExpense.add(0.0);

                        if (isExpense.equalsIgnoreCase("false"))  {

                            quantity = Double.parseDouble(arrayLinesMonthlyReport.get(2));
                            sum = Double.parseDouble(arrayLinesMonthlyReport.get(3));
                            arrayProfitAndExpense.add(0, quantity * sum);

                        } else if (isExpense.equalsIgnoreCase("true")) {

                            quantity = Double.parseDouble(arrayLinesMonthlyReport.get(2));
                            sum = Double.parseDouble(arrayLinesMonthlyReport.get(3));
                            arrayProfitAndExpense.add(1, quantity * sum);
                        }
                        monthlyReportMap.put(itemName, arrayProfitAndExpense);
                        monthlyReportsMaps.put(monthNumber, monthlyReportMap);
                    }
                    readerMonthlyReport.close();
                }
            } else {
                /**
                 * Метод вывода ошибок при считывании месячных отчетов
                 */
                windowsErrorMonthlyReports();
            }
        } catch (IOException e) {
            windowsErrorMonthlyReports();
        }
        System.out.println("Считывание всех месячных отчётов успешно завершено.");
    }

    void windowsErrorMonthlyReports() {
        /**
         * Знаменитый красный круг с крестикомм windows error =)
         */
        System.out.println(
                "Ошибка: файл не найден.\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatMonthlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogByMonths.getAbsolutePath() + "\".\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами." +
                        "Количество файлов в папке не должно превышать 12 месяцев в году.");
    }

    void outputInfoMonthlyReports() {
        /**
         * Метод Вывода информации о всех месячных отчётах
         */
        if (monthlyReportsMaps.size() != 0) {

            for (Integer keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                String maxProfitItemName = null;
                String maxExpanseItemName = null;
                double maxProfitMonthlyReport = 0.0;
                double maxExpanseMonthlyReport = 0.0;

                TreeMap<String, ArrayList<Double>> monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);

                System.out.println("Месяц: " + ReviseReports.calendar(keyMonthlyReportsMaps));

                for (String itemName : monthlyReportMap.keySet()) {

                    ArrayList<Double> arrayProfitAndExpense = monthlyReportMap.get(itemName);

                    if (arrayProfitAndExpense.get(0) > maxProfitMonthlyReport) {

                        maxProfitMonthlyReport = arrayProfitAndExpense.get(0);
                        maxProfitItemName = itemName;

                    } else if (arrayProfitAndExpense.get(1) > maxExpanseMonthlyReport) {

                        maxExpanseMonthlyReport = arrayProfitAndExpense.get(1);
                        maxExpanseItemName = itemName;
                    }
                }

                System.out.println(" Самый прибыльный товар: \"" + maxProfitItemName + "\" на сумму: \"" + maxProfitMonthlyReport + "\"");
                System.out.println(" Самая большая трата: \"" + maxExpanseItemName + "\" на сумму: \"" + -maxExpanseMonthlyReport + "\"");
            }
        } else {
            System.out.println("Перед Выводом информации о всех месячных отчётах необходимо:\n" +
                    "1 - Считать все месячные отчёты.");
        }
    }
}