import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class YearlyReport {
    String pathCatalogReports = ("Reports");
    String formatYearlyReport = "y.\\d{4}.csv";
    String patternFormatYearlyReport = "y.YYYY.csv";
    File catalogReports;
    public TreeMap<Integer, Double[]> yearlyReportMap = new TreeMap<Integer, Double[]>();

    void readingYearlyReport() {
        catalogReports = new File(pathCatalogReports);
        try {
            File[] pathFileReport = catalogReports.listFiles((dir, name) -> name.matches(formatYearlyReport));

            if (pathFileReport != null && pathFileReport.length == 1) {
                Integer keyMonthNumber = 0;
                String lineYearlyReport;
                String[] massivLinesYearlyReport;
                Double[] sumsYearlyReport = new Double[2];
                BufferedReader readerYearlyReport;

                readerYearlyReport = new BufferedReader(new FileReader(pathFileReport[0]));

                while ((lineYearlyReport = readerYearlyReport.readLine()) != null) {

                    massivLinesYearlyReport = lineYearlyReport.split(",");
                    if (keyMonthNumber != Integer.parseInt(massivLinesYearlyReport[0])) {
                        keyMonthNumber = Integer.parseInt(massivLinesYearlyReport[0]);
                    }
                    if (massivLinesYearlyReport[2].equals("FALSE")) {
                        sumsYearlyReport[0] = Double.parseDouble(massivLinesYearlyReport[2]);
                    } else if (massivLinesYearlyReport[1].equals("TRUE")) {
                        sumsYearlyReport[1] = -Double.parseDouble(massivLinesYearlyReport[2]);
                    }
                    yearlyReportMap.put(keyMonthNumber, sumsYearlyReport);
                }
                readerYearlyReport.close();
            } else {
                windowsErrorYearlyReport();
            }
        } catch (IOException e) {
            windowsErrorYearlyReport();
        }
    }

    void windowsErrorYearlyReport() {
        System.out.println(
                "Ошибка: файл не найден\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatYearlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogReports.getAbsolutePath() + "\"\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿В папке может находиться только один годовой отчет.");
    }
}