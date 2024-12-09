import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomFileReader {
    HashMap<String, ArrayList<ArrayList<String>>> readingReports(String catalogPath, String reportNameFormat) {
        //Метод для Считывания всех отчётов
        HashMap<String, ArrayList<ArrayList<String>>> reports = new HashMap<>();
        File catalogReports = new File(catalogPath);
        File[] listOfReports = catalogReports.listFiles((dir, name) -> name.matches(reportNameFormat));

        if (listOfReports != null && listOfReports.length != 0) {
            for (File reportFile : listOfReports) {
                ArrayList<ArrayList<String>> report = getArrayListsFromFile(reportFile);
                reports.put(reportFile.getName(), report);
            }
            System.out.println("Считывание всех отчётов успешно завершено.");
            return reports;
        } else {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new HashMap<>();
        }
    }

    ArrayList<ArrayList<String>> getArrayListsFromFile(File reportFile) {
        ArrayList<ArrayList<String>> report;

        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(reportFile))) {
            String lineInReport;

            report = new ArrayList<>();

            while ((lineInReport = bufferedReader.readLine()) != null) {
                ArrayList<String> lineContent = new ArrayList<>(List.of(lineInReport.split(",")));
                report.add(lineContent);
            }
            return report;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }
}