import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class MonthlyReport {
    String pathCatalogBy_Months = ("Reports/By Months");
    String formatMonthlyReport = "m.\\d{6}.csv";
    String patternFormatMonthlyReport = "m.YYYYMM.csv";
    File catalogBy_Months;
    public TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps = new TreeMap<>();

    void readingMonthlyReports() {

        catalogBy_Months = new File(pathCatalogBy_Months);
        try {
            File[] pathsMonthlyReports = catalogBy_Months.listFiles((dir, name) -> name.matches(formatMonthlyReport));

            if (pathsMonthlyReports != null && pathsMonthlyReports.length != 0 && pathsMonthlyReports.length < 13) {

                String keyReportName;
                String keyItemName;
                String lineMonthlyReport;
                String[] massivLinesMonthlyReport;
                BufferedReader readerMonthlyReport;
                TreeMap<String, String[]> reportMap;

                for (File pathFileReport : pathsMonthlyReports) {

                    keyReportName = pathFileReport.getName();
                    reportMap = new TreeMap<>();
                    readerMonthlyReport = new BufferedReader(new FileReader(pathFileReport));

                    while ((lineMonthlyReport = readerMonthlyReport.readLine()) != null) {

                        massivLinesMonthlyReport = lineMonthlyReport.split(",");
                        keyItemName = massivLinesMonthlyReport[0];
                        reportMap.put(keyItemName, massivLinesMonthlyReport);
                        monthlyReportsMaps.put(keyReportName, reportMap);
                    }
                    readerMonthlyReport.close();
                }
            } else {
                windowsErrorMonthlyReport();
            }
        } catch (IOException e) {
            windowsErrorMonthlyReport();
        }
    }

    void windowsErrorMonthlyReport() {
        System.out.println(
                "Ошибка: файл не найден\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatMonthlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogBy_Months.getAbsolutePath() + "\"\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами." +
                        "Количество файлов в папке не должно превышать 12 месяцев в году.");
    }


}