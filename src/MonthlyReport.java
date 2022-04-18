import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps = new TreeMap<>();
    static String pathCatalogBy_Months = ("Reports/By Months");
    static String formatMonthlyReport = "m.\\d{6}.csv";
    static String patternFormatMonthlyReport = "m.YYYYMM.csv";
    File catalogByMonths;

    void readingMonthlyReports() {
    /**
     * Метод для Считывания всех месячных отчётов
     */
        catalogByMonths = new File(pathCatalogBy_Months);

        try {
            File[] pathsMonthlyReports = catalogByMonths.listFiles((dir, name) -> name.matches(formatMonthlyReport));

            if (pathsMonthlyReports != null && pathsMonthlyReports.length != 0 && pathsMonthlyReports.length < 13) {

                for (File pathFileReport : pathsMonthlyReports) {
                    String lineMonthlyReport;

                    String keyReportName = pathFileReport.getName().replaceFirst("m.\\d{4}", "")
                            .replaceFirst(".csv", "");
                    TreeMap<String, String[]> reportMap = new TreeMap<>();
                    BufferedReader readerMonthlyReport = new BufferedReader(new FileReader(pathFileReport));

                    while ((lineMonthlyReport = readerMonthlyReport.readLine()) != null) {

                        String[] massivLinesMonthlyReport = lineMonthlyReport.split(",");

                        if (massivLinesMonthlyReport[1].equalsIgnoreCase("False") ||
                                massivLinesMonthlyReport[1].equalsIgnoreCase("True")) {

                            String keyItemName = massivLinesMonthlyReport[0];
                            reportMap.put(keyItemName, massivLinesMonthlyReport);
                            monthlyReportsMaps.put(keyReportName, reportMap);
                        }
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

            for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                String maxProfitItemName = null; //Если перенести объявление с этих строки, то при выводе (стр 114-115) будет не видно эти переменные
                String maxExpanseItemName = null;
                double maxProfitMonthlyReport = 0.0;
                double maxExpanseMonthlyReport = 0.0;

                TreeMap<String, String[]> reportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);

                System.out.println("Месяц: " + ReviseReports.calendar(keyMonthlyReportsMaps));

                for (String keyReportMap : reportMap.keySet()) {

                    String[] massivLinesMonthlyReport = reportMap.get(keyReportMap);
                    double sumMonthlyReport = Double.parseDouble(massivLinesMonthlyReport[2]) * Double.parseDouble(massivLinesMonthlyReport[3]);

                    if ((massivLinesMonthlyReport[1].equalsIgnoreCase("False")) && (sumMonthlyReport > maxProfitMonthlyReport)) {

                        maxProfitMonthlyReport = sumMonthlyReport;
                        maxProfitItemName = keyReportMap;

                    } else if ((massivLinesMonthlyReport[1].equalsIgnoreCase("True")) && (sumMonthlyReport > maxExpanseMonthlyReport)) {

                        maxExpanseMonthlyReport = sumMonthlyReport;
                        maxExpanseItemName = keyReportMap;
                    }
                }

                System.out.println(" Самый прибыльный товар: \"" + maxProfitItemName + "\" на сумму: \"" + maxProfitMonthlyReport + "\""); //Если перенести объявление с 76 строки, здесь ее будет не видно
                System.out.println(" Самая большая трата: \"" + maxExpanseItemName + "\" на сумму: \"" + -maxExpanseMonthlyReport + "\"");
            }
        } else {
            System.out.println("Перед Выводом информации о всех месячных отчётах необходимо:\n" +
                    "1 - Считать все месячные отчёты.");
        }
    }
}