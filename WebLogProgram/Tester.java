
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public static final void print(Object x) { System.out.println(x); }
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        print(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        la.printAll();
        
    }
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        print("There are " + uniqueIPs + " IPs");
    }
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String date = "Sep 27";
        ArrayList<String> uniqueIPOnDate = la.uniqueIPVisitsOnDay(date);
        for(String ip : uniqueIPOnDate) {
            print("On " + date + " this is a unique ip " + ip);
            print("The size of array list is " + uniqueIPOnDate.size());
        }
    }
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        int uniqueIPs = la.countUniqueIPsInRange(low, high);
        print("The number of unique ips between: " + low + " and " + high + " is " + uniqueIPs);
    }
    public void testCountVistsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        print(counts);
    }
    public void testCountingWebsiteVisitsAllMethods() {
        LogAnalyzer analyzer6 = new LogAnalyzer();
        analyzer6.readFile("weblog2_log");
        HashMap<String,Integer> counts = analyzer6.countVisitsPerIP();
        print("countVisitsPerIP");
        System.out.println(counts);
        
        // // Prints max number of visits by single IP address
        LogAnalyzer analyzer7 = new LogAnalyzer();
        analyzer7.readFile("weblog2_log");
        int maxVisits = analyzer7. mostNumberVisitsByIP(analyzer7.countVisitsPerIP());
        print("mostNumberVisitsByIP");
        System.out.println(maxVisits);
        
        // // Prints list of addresses that visited website max times
        LogAnalyzer analyzer8 = new LogAnalyzer();
        analyzer8.readFile("weblog2_log");
        ArrayList<String> maxIPs = analyzer8.iPsMostVisits(analyzer8.countVisitsPerIP());
        print("iPsMostVisits");
        System.out.println(maxIPs);
        
        // Prints HashMap with addresses that visited a site each day
        LogAnalyzer analyzer9 = new LogAnalyzer();
        analyzer9.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> iPsEachDay = analyzer9.iPsForDays();
        print("iPsForDays");
        System.out.println(iPsEachDay);
        
        // Prints date with max number of visits (if tie, any such date)
        LogAnalyzer analyzer10 = new LogAnalyzer();
        analyzer10.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> iPsEachDay2 = analyzer10.iPsForDays();
        print("dayWithMostIPVisits");
        System.out.println(analyzer10.dayWithMostIPVisits(iPsEachDay2));
        
        // Prints list of addresses with most visits on given day
        LogAnalyzer analyzer11 = new LogAnalyzer();
        analyzer11.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> iPsEachDay3 = analyzer11.iPsForDays();
        print("iPsWithMostVisitsOnDay for Wed Sep 30");
        System.out.println(analyzer11.iPsWithMostVisitsOnDay(iPsEachDay3, "Wed Sep 30"));
    }
}
