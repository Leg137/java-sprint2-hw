import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Report {

    void readingReports(String pathCatalogWithReports, String formatReports, HashMap<String, HashMap<String, String[]>> reportMaps, String patternFormatReport) {

        File catalogWithReports = new File(pathCatalogWithReports);
        try {
            File[] pathsFilesReports = catalogWithReports.listFiles((dir, name) -> name.matches(formatReports));

            if (pathsFilesReports != null && pathsFilesReports.length != 0) {
                String keyReportName;
                BufferedReader readerReport;
                HashMap<String, String[]> reportMap;

                for (File pathFilesReports : pathsFilesReports) {

                    keyReportName = pathFilesReports.getName();
                    reportMap = new HashMap<>();
                    String[] massivLines;
                    String keyItemName;
                    readerReport = new BufferedReader(new FileReader(pathFilesReports));
                    String lineReport;

                    while ((lineReport = readerReport.readLine()) != null) {

                        massivLines = lineReport.split(",");
                        keyItemName = massivLines[0];
                        reportMap.put(keyItemName, massivLines);
                        reportMaps.put(keyReportName, reportMap);
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
}
