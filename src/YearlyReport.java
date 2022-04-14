import java.util.TreeMap;

public class YearlyReport {
    String pathCatalogReports = ("Reports");
    String formatYearlyReport = "y.\\d{4}.csv";
    String patternFormatYearlyReport = "y.YYYY.csv";


    TreeMap<String, TreeMap<String, String[]>> yearlyReportsMaps = new TreeMap<>();
    Report report = new Report();

    void readingYearlyReport() {
        report.readingReports(pathCatalogReports, formatYearlyReport, yearlyReportsMaps, patternFormatYearlyReport);
    }

    void informationOutputYearlyReport() {
        TreeMap<String, String[]> yearlyReportMap;

        for (String keyMonthlyReportsMaps : yearlyReportsMaps.keySet()) {
            yearlyReportMap = yearlyReportsMaps.get(keyMonthlyReportsMaps);
            for (String keyMonthlyReportMap : yearlyReportMap.keySet()) {
                String[] monthlySums = yearlyReportMap.get(keyMonthlyReportMap);
                for (String item : monthlySums) {
                    System.out.println(item);
                }
            }
        }
    }
}