import java.util.TreeMap;

public class MonthlyReport {
    String pathCatalogBy_Months = ("Reports/by months");
    String formatMonthlyReport = "m.\\d{6}.csv";
    String patternFormatMonthlyReport = "m.YYYYMM.csv";

    public static TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps = new TreeMap<>();
    Report report = new Report();

    void readingMonthlyReport() {
        report.readingReports(pathCatalogBy_Months, formatMonthlyReport, monthlyReportsMaps, patternFormatMonthlyReport);
    }







}