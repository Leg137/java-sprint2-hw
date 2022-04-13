import java.util.HashMap;

public class MonthlyReport {
    String pathCatalogBy_Months = ("Reports/by months");
    String formatMonthlyReport = "m.\\d{6}.csv";
    String patternFormatMonthlyReport = "m.YYYYMM.csv";
    HashMap<String, String[]> monthlyReportMap;

    HashMap<String, HashMap<String, String[]>> monthlyReportsMaps = new HashMap<>();
    Report report = new Report();

    void readingMonthlyReport() {
        report.readingReports(pathCatalogBy_Months, formatMonthlyReport, monthlyReportsMaps, patternFormatMonthlyReport);
    }

    void informationOutputMonthlyReport() {
        for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {
            monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
            System.out.println("\n" + keyMonthlyReportsMaps);
            for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {
                System.out.println("\n");
                String[] itemName = monthlyReportMap.get(keyMonthlyReportMap);
                for (String item : itemName) {
                    System.out.println(item);
                }
            }
        }
    }
}