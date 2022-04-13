import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MonthlyReport {
    String pathCatalogBy_Months = ("Reports/by months");
    File catalogBy_Months = new File(pathCatalogBy_Months);
    HashMap<String, HashMap<String, String[]>> monthlyReportsMaps = new HashMap<>();
    BufferedReader reader;
    String line;
    String keyFileName;
    HashMap<String, String[]> monthlyReportMap;

    void readingMonthlyReports() {
        try {
            File[] abstractPathsFiles = catalogBy_Months.listFiles((dir, name) -> name.matches("m.\\d{6}.csv"));

            if (abstractPathsFiles != null && abstractPathsFiles.length != 0) {

                for (File abstractPathsFile : abstractPathsFiles) {
                    //System.out.println("\n" + abstractPathsFile.getName() + "\n");

                    keyFileName = abstractPathsFile.getName();
                    monthlyReportMap = new HashMap<>();
                    String[] massivLines;
                    String keyItemName;
                    reader = new BufferedReader(new FileReader(abstractPathsFile));

                    while ((line = reader.readLine()) != null) {
                        //System.out.println(line);

                        massivLines = line.split(",");
                        keyItemName = massivLines[0];
                        monthlyReportMap.put(keyItemName, massivLines);
                        monthlyReportsMaps.put(keyFileName, monthlyReportMap);
                    }
                    reader.close();
                }


            } else {
                System.out.println("Равно null или 0");
                windowsError();
            }
        } catch (IOException e) {
            windowsError();
        }

        for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {
            monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
            System.out.println("\n" + keyMonthlyReportsMaps + "\n");
            for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {
                //System.out.println("\n" + keyMonthlyReportMap + "\n");
                String[] itemName = monthlyReportMap.get(keyMonthlyReportMap);
                for (String item : itemName) {
                    System.out.println(item);
                }

            }
        }

    }

    void windowsError() {
        System.out.println(
                "Ошибка: файл не найден\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате \"m.YYYYMM.csv\" не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogBy_Months.getAbsolutePath() + "\"\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц и отчёты за год;\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами.");
    }
}