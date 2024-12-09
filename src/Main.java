import java.util.Scanner;

/**
 * «Автоматизация бухгалтерии»
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ReviseReports reviseReports = new ReviseReports();

        while (true) {
            //Консольный интерфейс для управления программой
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.readingMonthlyReports();
            } else if (command == 2) {
                yearlyReport.readingYearlyReport();
            } else if (command == 3) {
                reviseReports.reviseSumReports(monthlyReport.reports, yearlyReport.reports);
            } else if (command == 4) {
                monthlyReport.outputInfoMonthlyReports();
            } else if (command == 5) {
                yearlyReport.outputInfoYearlyReport();
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        //Вывод меню
        System.out.println("\nЧто вы хотите сделать? ");
        System.out.println("1 - Считать месячные отчёты");
        System.out.println("2 - Считать годовые отчёты");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о месячных отчётах");
        System.out.println("5 - Вывести информацию о годовых отчётах");
        System.out.println("0 - Выход");
    }
}