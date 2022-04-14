import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Report {

    void readingReports(String pathCatalogWithReports, String formatReports, TreeMap<String, TreeMap<String, String[]>> reportsMaps, String patternFormatReport) {

        File catalogWithReports = new File(pathCatalogWithReports);
        try {
            File[] pathsFilesReports = catalogWithReports.listFiles((dir, name) -> name.matches(formatReports));

            if (pathsFilesReports != null && pathsFilesReports.length != 0) {
                String keyReportName;
                BufferedReader readerReport;
                TreeMap<String, String[]> reportMap;

                for (File pathFilesReports : pathsFilesReports) {

                    keyReportName = pathFilesReports.getName();
                    reportMap = new TreeMap<>();
                    String[] massivLines;
                    String keyItemName;
                    readerReport = new BufferedReader(new FileReader(pathFilesReports));
                    String lineReport;

                    while ((lineReport = readerReport.readLine()) != null) {

                        massivLines = lineReport.split(",");
                        keyItemName = massivLines[0];
                        reportMap.put(keyItemName, massivLines);
                        reportsMaps.put(keyReportName, reportMap);
                    }
                    readerReport.close();
                }
            } else {
                windowsErrorReport(catalogWithReports, patternFormatReport);
            }
        } catch (IOException e) {
            windowsErrorReport(catalogWithReports, patternFormatReport);
        }
    }

    void windowsErrorReport(File catalogWithReports, String patternFormatReport) {
        System.out.println(
                "Ошибка: файл не найден\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogWithReports.getAbsolutePath() + "\"\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿m | y — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿Дополнительно: MM — месяц строго 2мя цифрами.");
    }

    void reviseReport(TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps) {
        double monthlySumsProfit;
        double monthlySumsExpenses;
        Double[] massiveMonthlySums;
        TreeMap<String, String[]> monthlyReportMap;
        TreeMap<String, Double[]> reviseSumMonthlyReport = new TreeMap<>();

        for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {
            monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
            monthlySumsProfit = 0;
            monthlySumsExpenses = 0;

            for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {
                String[] itemName = monthlyReportMap.get(keyMonthlyReportMap);
                if (itemName[1].equals("FALSE")) {
                    monthlySumsProfit += Double.parseDouble(itemName[2]) * Double.parseDouble(itemName[3]);
                } else if (itemName[1].equals("TRUE")) {
                    monthlySumsExpenses += Double.parseDouble(itemName[2]) * Double.parseDouble(itemName[3]);
                }
            }
            massiveMonthlySums = new Double[2];
            massiveMonthlySums[0] = monthlySumsProfit;
            massiveMonthlySums[1] = monthlySumsExpenses;

            reviseSumMonthlyReport.put(keyMonthlyReportsMaps, massiveMonthlySums);
        }

        for (String keyMonthlyReportsMaps : reviseSumMonthlyReport.keySet()) {
            System.out.println("\n" + keyMonthlyReportsMaps);
            Double[] outputSumsMonthlyReport = reviseSumMonthlyReport.get(keyMonthlyReportsMaps);
            System.out.println(" Сумма доходов в месяце: " + outputSumsMonthlyReport[0]);
            System.out.println(" Сумма расходов в месяце: " + outputSumsMonthlyReport[1]);
        }
    }
}
