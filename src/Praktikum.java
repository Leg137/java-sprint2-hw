import java.io.File;
import java.util.Scanner;

public class Praktikum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String path = ("Reports/2021/by months");
        File file = new File(path);

        File catalogByMonths = new File(file.getAbsolutePath());

        System.out.println("Абстрактный путь " + path);
        System.out.println("Абссолютный путь " + file.getAbsolutePath());
        System.out.println("Является каталогом? " + catalogByMonths.isDirectory());
        System.out.println("Является файлом? " + catalogByMonths.isFile());

        System.out.println("Существует файл или каталог? " + catalogByMonths.exists());
        System.out.println("Может ли приложение прочитать файл? " + catalogByMonths.canRead());

        File[] files = catalogByMonths.listFiles();
        for (File f : files) {
            System.out.println(f);
        }

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {

            } else if (command == 2) {

            } else if (command == 3) {

            } else if (command == 4) {

            } else if (command == 5) {

            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}