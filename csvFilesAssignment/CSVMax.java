import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CSVMax {
    public static final void print(Object x) { System.out.println(x); }
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        // Notice that largestSoFar is the entire record for the hottest day so all fields are available to report on
        return largestSoFar;
    }
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } 
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));       
            if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    public void testHottestInDay() {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord largest = hottestHourInFile(parser);
        //The above 2 lines can be combined into the single line below
        //CSVRecord largest = hottestHourInFile(fr.getCSVParser());        
        print("Hottest temperature was " + largest.get("TemperatureF")
                  + " at " + largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        // Get a list of files to search
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // Open the first 'f' file to get hottest temperature for that day
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser()); 
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        // Notice that largestSoFar is the entire record for the hottest day so all fields are available to report on
        return largestSoFar;
       }
    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        print("Hottest temperature was " + largest.get("TemperatureF")
                  + " at " + largest.get("DateUTC"));
    }
}