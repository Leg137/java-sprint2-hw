import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

//Класс для работы с Месячными отчетами
public class MonthlyReport {

    TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps = new TreeMap<>(); //Форма итоговой таблицы месячных отчетов
    String pathCatalogBy_Months = ("Reports/By Months"); //Каталог с месячными отчетами
    String formatMonthlyReport = "m.\\d{6}.csv"; //Формат месячных отчетов
    String patternFormatMonthlyReport = "m.YYYYMM.csv";
    File catalogBy_Months;

    void readingMonthlyReports() { //Метод для Считывания всех месячных отчётов

        catalogBy_Months = new File(pathCatalogBy_Months); //Каталог с месячными отчетами

        try {
            File[] pathsMonthlyReports = catalogBy_Months.listFiles((dir, name) -> name.matches(formatMonthlyReport)); //Отсеиваем файлы не формата

            if (pathsMonthlyReports != null && pathsMonthlyReports.length != 0 && pathsMonthlyReports.length < 13) { //Проверяем пустую папку месячных отчетов

                String keyReportName;
                String keyItemName;
                String lineMonthlyReport;
                String[] massivLinesMonthlyReport;
                BufferedReader readerMonthlyReport;
                TreeMap<String, String[]> reportMap;

                for (File pathFileReport : pathsMonthlyReports) {

                    keyReportName = pathFileReport.getName().replaceFirst("m.\\d{4}", "")
                            .replaceFirst(".csv", ""); //Оставляем от названия файла номер месяца
                    reportMap = new TreeMap<>();
                    readerMonthlyReport = new BufferedReader(new FileReader(pathFileReport));

                    while ((lineMonthlyReport = readerMonthlyReport.readLine()) != null) {

                        massivLinesMonthlyReport = lineMonthlyReport.split(",");

                        if (massivLinesMonthlyReport[1].equalsIgnoreCase("False") ||
                                massivLinesMonthlyReport[1].equalsIgnoreCase("True")) { //Исключаем строку с шапкой csv файла

                            keyItemName = massivLinesMonthlyReport[0];
                            reportMap.put(keyItemName, massivLinesMonthlyReport);
                            monthlyReportsMaps.put(keyReportName, reportMap); //Сохраняем таблицу с месячными данными по ключу - номер месяца
                        }
                    }
                    readerMonthlyReport.close();
                }
            } else {
                windowsErrorMonthlyReports(); //Метод вывода ошибок при считывании месячных отчетов
            }
        } catch (IOException e) {
            windowsErrorMonthlyReports(); //Метод вывода ошибок при считывании месячных отчетов
        }
        System.out.println("Считывание всех месячных отчётов успешно завершено.");
    }

    void windowsErrorMonthlyReports() { //Знаменитый красный круг с крестикомм windows error=)

        System.out.println(
                "Ошибка: файл не найден.\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatMonthlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogBy_Months.getAbsolutePath() + "\".\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами." +
                        "Количество файлов в папке не должно превышать 12 месяцев в году.");
    }

    void outputInfoMonthlyReports() { //Метод Вывода информации о всех месячных отчётах

        String maxProfitItemName = null;
        String maxExpanseItemName = null;
        TreeMap<String, String[]> reportMap;
        String[] massivLinesMonthlyReport;
        double sumMonthlyReport;

        if (monthlyReportsMaps.size() != 0) { //Проверяем считывались ли месячные отчеты

            for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                double maxProfitMonthlyReport = 0.0;
                double maxExpanseMonthlyReport = 0.0;

                reportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);

                System.out.println("Месяц: " + ReviseReports.calendar(keyMonthlyReportsMaps));

                for (String keyReportMap : reportMap.keySet()) {

                    massivLinesMonthlyReport = reportMap.get(keyReportMap);
                    sumMonthlyReport = Double.parseDouble(massivLinesMonthlyReport[2]) * Double.parseDouble(massivLinesMonthlyReport[3]);

                    if ((massivLinesMonthlyReport[1].equalsIgnoreCase("False")) && (sumMonthlyReport > maxProfitMonthlyReport)) {

                        maxProfitMonthlyReport = sumMonthlyReport;
                        maxProfitItemName = keyReportMap;

                    } else if ((massivLinesMonthlyReport[1].equalsIgnoreCase("True")) && (sumMonthlyReport > maxExpanseMonthlyReport)) {

                        maxExpanseMonthlyReport = sumMonthlyReport;
                        maxExpanseItemName = keyReportMap;
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