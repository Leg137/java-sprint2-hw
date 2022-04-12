import java.io.*;

public class MonthlyReport {
    String pathCatalogBy_Months = ("Reports/2021/by months");
    File catalogBy_Months = new File(pathCatalogBy_Months);

    void readingMonthReport() {
        try {
            File[] abstractPathsFiles = catalogBy_Months.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches("m.20210\\d.csv");
                }
            });
            if (abstractPathsFiles.length != 0) {

                for (File abstractPathFile : abstractPathsFiles) {
                    System.out.println(abstractPathFile);
                    BufferedReader reader = new BufferedReader(new FileReader(abstractPathFile));
                }
            } else System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, " +
                    "файл не находится по указанному пути: \"" + pathCatalogBy_Months + "\"");
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, " +
                    "файл не находится по указанному пути: \"" + pathCatalogBy_Months + "\"");
        }

    }
}