import java.util.Scanner;

//«Автоматизация бухгалтерии»
public class Praktikum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ReviseReports reviseReports = new ReviseReports();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.readingMonthlyReports(); //Метод для Считывания всех месячных отчётов
            } else if (command == 2) {
                yearlyReport.readingYearlyReport(); //Метод для Считывания годового отчёта
            } else if (command == 3) {
                reviseReports.reviseSumReports(monthlyReport.monthlyReportsMaps, yearlyReport.yearlyReportMap); //Метод Сверки отчётов
            } else if (command == 4) {
                monthlyReport.outputInfoMonthlyReports(); //Метод Вывода информации о всех месячных отчётах
            } else if (command == 5) {
                yearlyReport.outputInfoYearlyReport(); //Метод Вывода информации о годовом отчёте
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nЧто вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}