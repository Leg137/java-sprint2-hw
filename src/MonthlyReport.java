import java.io.*;

public class MonthlyReport {
    String pathCatalogBy_Months = ("Reports/by months");
    File catalogBy_Months = new File(pathCatalogBy_Months);

    void readingMonthlyReports() {
        try {
            File[] abstractPathsFiles = catalogBy_Months.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches("m.\\d{6}.csv");
                }
            });
            if (abstractPathsFiles != null && abstractPathsFiles.length != 0) {
                for (File abstractPathFile : abstractPathsFiles) {
                    System.out.println(abstractPathFile);
                    BufferedReader reader = new BufferedReader(new FileReader(abstractPathFile));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                }
            } else {
                System.out.println(
                        "Ошибка: файл не найден\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате \"m.YYYYMM.csv\" не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogBy_Months.getAbsolutePath() + "\"\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц и отчёты за год;\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Ошибка: файл не найден\n" +
                            "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате \"m.YYYYMM.csv\" не находится в папке:\n" +
                            "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogBy_Months.getAbsolutePath() + "\"\n" +
                            "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц и отчёты за год;\n" +
                            "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                            "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами.");
        }

    }
}