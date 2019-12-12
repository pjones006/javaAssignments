import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of FirstCSVExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstCSVExample {
    public static final void print(Object x) { System.out.println(x); }
    
    public void listExporters(CSVParser parser, String exportOfInterest) {
        // for each row in csv file
        for (CSVRecord record : parser) {
            // Look at the exports column
            String export = record.get("Exports");
            // Check if contains exportOfInterest
            
            // ONE WAY TO CHECK FOR EXPORT
            //if (export.indexOf(exportOfInterest) != -1){
            // BETTER WAY TO CHECK FOR EXPORT
            if (export.contains(exportOfInterest)) {
                // If so writedown 'country' for that row
                String country = record.get("Country");
                print(country);
            }

        }
    }
        
    public String countryInfo(CSVParser parser, String countryOfInterest) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(countryOfInterest)) {
                String country = record.get("Country"); 
                String exportsInfo = record.get("Exports");
                String valueInfo = record.get("Value (dollars)");
                String countryData = (country + ":" + exportsInfo + " " + valueInfo);   
                return (countryData);
                //return countryData;
            } 
        }
        return "NOT FOUND";
    } 
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        print("Here are the countries that are exporting both: " + exportItem1 + " and " + exportItem2);
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                print(record.get("Country"));
 
            }
        }
        
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem)) {
                count = count + 1;
            }
        }
        return count;
        
    }
    
    public void bigExporters(CSVParser parser, String amount) {
         for (CSVRecord record : parser) {
             String country = record.get("Country");
             String exportAmount = record.get("Value (dollars)");
             int amountLength = amount.length();
             int exportLength = exportAmount.length();
             if (amountLength < exportLength) {
                 print(country + " " + exportAmount);
                }
            }
        
        
    }
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public void tester() {
        FileResource fr = new FileResource("/home/paul/Desktop/java-programming/JAVA/csvFiles/exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        CSVParser parser2 = fr.getCSVParser();
        CSVParser parser3 = fr.getCSVParser();   
        
        // print country data for selected country
        print("Nauru");
        String result = countryInfo(parser, "Nauru");
        print(result); print(""); 
        
        print("Germany");   
        result = countryInfo(parser2, "Germany");
        print(result); print(""); 
                
        print("lalaland");       
        result = countryInfo(parser3, "lalaland");
        print(result); print(""); 
        
        // Countries with these 2 exports
        fr = new FileResource("/home/paul/Desktop/java-programming/JAVA/csvFiles/exportdata.csv");
        CSVParser parser4 = fr.getCSVParser();
        listExportersTwoProducts(parser4, "cotton", "flowers"); print("");
        
        // Number of countries with these exports
        fr = new FileResource("/home/paul/Desktop/java-programming/JAVA/csvFiles/exportdata.csv");
        CSVParser parser5 = fr.getCSVParser();
        int exportCount = numberOfExporters(parser5, "gold");
        print("Number of countries exporting gold is : " + exportCount);
        CSVParser parser6 = fr.getCSVParser();
        exportCount = numberOfExporters(parser6, "coffee");
        print("Number of countries exporting coffee is : " + exportCount); print("");
        CSVParser parser7 = fr.getCSVParser();
        exportCount = numberOfExporters(parser7, "cocoa");
        print("Number of countries exporting cocoa is : " + exportCount); print("");
        
        // Print countries and their export values if they exceed the digit length of the amount passed into the method
        fr = new FileResource("/home/paul/Desktop/java-programming/JAVA/csvFiles/exportdata.csv");
        CSVParser parser8 = fr.getCSVParser();
        bigExporters(parser8, "$999,999,999,999"); print("");
        
    }
    
}
