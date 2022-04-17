import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

//Класс для работы с Годовым отчетом
public class YearlyReport {

    public TreeMap<String, Double[]> yearlyReportMap = new TreeMap<>(); ////Форма итоговой таблицы годового отчета
    String pathCatalogReports = ("Reports"); //Каталог с годовым отчетом
    String formatYearlyReport = "y.\\d{4}.csv"; //Формат годового отчета
    String patternFormatYearlyReport = "y.YYYY.csv";
    File catalogReports;
    String nameYearlyReport;

    void readingYearlyReport() { //Метод для Считывания годового отчёта

        catalogReports = new File(pathCatalogReports); //Каталог с годовым отчетом
        try {
            File[] pathFileReport = catalogReports.listFiles((dir, name) -> name.matches(formatYearlyReport)); //Отсеиваем файлы не формата

            if (pathFileReport != null && pathFileReport.length == 1) { //Проверяем пустую папку годового отчета

                Double[] sumsYearlyReport = new Double[2];
                int count = 1; //Счетчик для сохранения годового отчета
                String keyMonthNumber;
                String lineYearlyReport;
                String[] massivLinesYearlyReport;
                BufferedReader readerYearlyReport;

                nameYearlyReport = pathFileReport[0].getName();
                readerYearlyReport = new BufferedReader(new FileReader(pathFileReport[0]));

                while ((lineYearlyReport = readerYearlyReport.readLine()) != null) {

                    massivLinesYearlyReport = lineYearlyReport.split(",");

                    if (massivLinesYearlyReport[0].matches("\\d\\d")) { //Исключаем строку с шапкой csv файла

                        keyMonthNumber = massivLinesYearlyReport[0];

                        if (massivLinesYearlyReport[2].equalsIgnoreCase("False")) {

                            sumsYearlyReport[0] = Double.parseDouble(massivLinesYearlyReport[1]);

                        } else if (massivLinesYearlyReport[2].equalsIgnoreCase("True")) {

                            sumsYearlyReport[1] = -Double.parseDouble(massivLinesYearlyReport[1]);
                        }

                        if (count % 2 == 0) { //Сохраняем таблицу, когда доходы и расходы сохранены в массив

                            yearlyReportMap.put(keyMonthNumber, sumsYearlyReport); //Сохраняем суммы месячных данных по ключу - номер месяца
                            sumsYearlyReport = new Double[2];
                        }
                        count++;
                    }
                }
                readerYearlyReport.close();
            } else {
                windowsErrorYearlyReport(); //Метод вывода ошибок при считывании месячных отчетов
            }
        } catch (IOException e) {
            windowsErrorYearlyReport(); //Метод вывода ошибок при считывании месячных отчетов
        }
        System.out.println("Считывание годового отчёта успешно завершено.");
    }

    void windowsErrorYearlyReport() { //Знаменитый красный круг с крестикомм windows error=)

        System.out.println(
                "Ошибка: файл не найден.\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatYearlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogReports.getAbsolutePath() + "\".\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿В папке может находиться только один годовой отчет.");
    }

    void outputInfoYearlyReport() { //Метод Вывода информации о годовом отчёте

        double sumProfit = 0.0;
        double sumExpense = 0.0;
        double profitMonthlyReport;
        double averageSumProfit;
        double averageSumExpense;
        Double[] sumsYearlyReport;

        if (yearlyReportMap.size() != 0) { //Проверяем считывался ли годовой отчет

            System.out.println("Рассматриваемый год: \"" + nameYearlyReport.replaceFirst("y.", "")
                    .replaceFirst(".csv", "") + "\""); //Оставляем от названия файла номер года

            for (String keyMonthNumber : yearlyReportMap.keySet()) {

                sumsYearlyReport = yearlyReportMap.get(keyMonthNumber);
                profitMonthlyReport = sumsYearlyReport[0] + sumsYearlyReport[1];
                sumProfit += sumsYearlyReport[0];
                sumExpense += sumsYearlyReport[1];

                System.out.println(" Прибыль по месяцу: " + ReviseReports.calendar(keyMonthNumber) + " равна \"" + profitMonthlyReport + "\";");
            }
            averageSumProfit = sumProfit / yearlyReportMap.size();
            averageSumExpense = sumExpense / yearlyReportMap.size();

            System.out.println(" Средний расход за все месяцы в году: \"" + averageSumExpense + "\";");
            System.out.println(" Средний доход за все месяцы в году: \"" + averageSumProfit + "\".");
        } else {
            System.out.println("Перед Выводом информации о годовом отчёте необходимо:\n" +
                    "2 - Считать годовой отчёт.");
        }
    }
}