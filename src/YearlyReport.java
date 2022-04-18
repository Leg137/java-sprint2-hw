import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
/**
 * Класс для работы с Годовым отчетом
 */
public class YearlyReport {
/**
 * yearlyReportMap - Форма итоговой таблицы годового отчета;
 * pathCatalogReports - Каталог с годовым отчетом;
 * formatYearlyReport - Формат годового отчета.
 */
    TreeMap<String, Double[]> yearlyReportMap = new TreeMap<>();
    static String pathCatalogReports = ("Reports");
    static String formatYearlyReport = "y.\\d{4}.csv";
    static String patternFormatYearlyReport = "y.YYYY.csv";
    static String nameYearlyReport;
    File catalogReports;

    void readingYearlyReport() {
    /**
     * Метод для Считывания годового отчёта
     */
        catalogReports = new File(pathCatalogReports);
        try {
            File[] pathFileReport = catalogReports.listFiles((dir, name) -> name.matches(formatYearlyReport)); //Отсеиваем файлы не формата

            if (pathFileReport != null && pathFileReport.length == 1) {

                Double[] sumsYearlyReport = new Double[2];
                int count = 1;
                String lineYearlyReport;

                nameYearlyReport = pathFileReport[0].getName();
                BufferedReader readerYearlyReport = new BufferedReader(new FileReader(pathFileReport[0]));

                while ((lineYearlyReport = readerYearlyReport.readLine()) != null) {

                    String[] massivLinesYearlyReport = lineYearlyReport.split(",");

                    if (massivLinesYearlyReport[0].matches("\\d\\d")) {

                        String keyMonthNumber = massivLinesYearlyReport[0];

                        if (massivLinesYearlyReport[2].equalsIgnoreCase("False")) {

                            sumsYearlyReport[0] = Double.parseDouble(massivLinesYearlyReport[1]);

                        } else if (massivLinesYearlyReport[2].equalsIgnoreCase("True")) {

                            sumsYearlyReport[1] = -Double.parseDouble(massivLinesYearlyReport[1]);
                        }

                        if (count % 2 == 0) {

                            yearlyReportMap.put(keyMonthNumber, sumsYearlyReport);
                            sumsYearlyReport = new Double[2];
                        }
                        count++;
                    }
                }
                readerYearlyReport.close();
            } else {
            /**
             * Метод вывода ошибок при считывании годового отчета
             */
                windowsErrorYearlyReport();
            }
        } catch (IOException e) {
            windowsErrorYearlyReport(); //Метод вывода ошибок при считывании месячных отчетов
        }
        System.out.println("Считывание годового отчёта успешно завершено.");
    }

    void windowsErrorYearlyReport() { //Знаменитый красный круг с крестикомм windows error=)
    /**
     * Знаменитый красный круг с крестикомм windows error =)
     */
        System.out.println(
                "Ошибка: файл не найден.\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatYearlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogReports.getAbsolutePath() + "\".\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿В папке может находиться только один годовой отчет.");
    }

    void outputInfoYearlyReport() {
    /**
     * Метод Вывода информации о годовом отчёте
     */
        Double[] sumsYearlyReport;

        if (yearlyReportMap.size() != 0) {

            System.out.println("Рассматриваемый год: \"" + nameYearlyReport.replaceFirst("y.", "")
                    .replaceFirst(".csv", "") + "\"");

            double sumProfit = 0.0;
            double sumExpense = 0.0;

            for (String keyMonthNumber : yearlyReportMap.keySet()) {

                sumsYearlyReport = yearlyReportMap.get(keyMonthNumber);
                double profitMonthlyReport = sumsYearlyReport[0] + sumsYearlyReport[1];
                sumProfit += sumsYearlyReport[0];
                sumExpense += sumsYearlyReport[1];

                System.out.println(" Прибыль по месяцу: " + ReviseReports.calendar(keyMonthNumber) + " равна \"" + profitMonthlyReport + "\";");
            }
            double averageSumProfit = sumProfit / yearlyReportMap.size();
            double averageSumExpense = sumExpense / yearlyReportMap.size();

            System.out.println(" Средний расход за все месяцы в году: \"" + averageSumExpense + "\";");
            System.out.println(" Средний доход за все месяцы в году: \"" + averageSumProfit + "\".");
        } else {
            System.out.println("Перед Выводом информации о годовом отчёте необходимо:\n" +
                    "2 - Считать годовой отчёт.");
        }
    }
}