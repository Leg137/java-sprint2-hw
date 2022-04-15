import java.util.TreeMap;

public class Report {

    void reviseReport(TreeMap<String, TreeMap<String, String[]>> monthlyReportsMaps) {
        double monthlySumsProfit;
        double monthlySumsExpenses;
        Double[] massiveMonthlySums;
        TreeMap<String, String[]> monthlyReportMap;
        TreeMap<String, Double[]> reviseSumMonthlyReport = new TreeMap<>();

        for (String keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {
            monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
            monthlySumsProfit = 0;
            monthlySumsExpenses = 0;

            for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {
                String[] itemName = monthlyReportMap.get(keyMonthlyReportMap);
                if (itemName[1].equals("FALSE")) {
                    monthlySumsProfit += Double.parseDouble(itemName[2]) * Double.parseDouble(itemName[3]);
                } else if (itemName[1].equals("TRUE")) {
                    monthlySumsExpenses += Double.parseDouble(itemName[2]) * Double.parseDouble(itemName[3]);
                }
            }
            massiveMonthlySums = new Double[2];
            massiveMonthlySums[0] = monthlySumsProfit;
            massiveMonthlySums[1] = monthlySumsExpenses;

            reviseSumMonthlyReport.put(keyMonthlyReportsMaps, massiveMonthlySums);
        }

        for (String keyMonthlyReportsMaps : reviseSumMonthlyReport.keySet()) {
            System.out.println("\n" + keyMonthlyReportsMaps);
            Double[] outputSumsMonthlyReport = reviseSumMonthlyReport.get(keyMonthlyReportsMaps);
            System.out.println(" Сумма доходов в месяце: " + outputSumsMonthlyReport[0]);
            System.out.println(" Сумма расходов в месяце: " + outputSumsMonthlyReport[1]);
        }
    }
}