import java.util.HashMap;

public class YearlyReport {
    String pathCatalogReports = ("Reports");
    String formatYearlyReport = "m.\\d{4}.csv";
    String patternFormatYearlyReport = "y.YYYY.csv";
    //HashMap<String, String[]> yearlyReportMap;

    HashMap<String, HashMap<String, String[]>> yearlyReportsMaps = new HashMap<>();
    Report report = new Report();

    void readingYearlyReport() {
        report.readingReports(pathCatalogReports, formatYearlyReport, yearlyReportsMaps, patternFormatYearlyReport);
    }

    /*void informationOutputMonthlyReport() {
        for (String keyMonthlyReportsMaps : yearlyReportsMaps.keySet()) {
            yearlyReportMap = yearlyReportsMaps.get(keyMonthlyReportsMaps);
            System.out.println("\n" + keyMonthlyReportsMaps);
            for (String keyMonthlyReportMap : yearlyReportMap.keySet()) {
                System.out.println("\n");
                String[] itemName = yearlyReportMap.get(keyMonthlyReportMap);
                for (String item : itemName) {
                    System.out.println(item);
                }
            }
        }
    }*/
}