import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of CSVAssignment here.
 * 
 * @author (Paul Jones) 
 * @version (2019-12-12)
 * coldestHourInFile -  one parameter, a CSVParser named parser - return CSVRecord with record of coldest TEMP in ONE file
 * testColdestHourInFile()  - void method for testing coldestHourInFile
 * 
 * fileWithColdestTemperature - no parameters - return string with name of file which has coldest TEMP from MANY files selected.
 * testFileWithColdestTemperature() - void method for testing fileWithColdestTemperature, prints out all TEMP's for the file 
 *                                    with the coldest day.
 *                                    
 * lowestHumidityInFile - one parameter, a CSVParser named parser - return CSVRecord with record of coldest HUMIDITY in ONE file
 * testLowestHumidityInFile() - void method for testing lowestHumidityInFile
 * 
 * lowestHumidityInManyFiles - no parameters - This method returns a CSVRecord that has the lowest humidity from MANY files selected.
 * testLowestHumidityInManyFiles() - void method to print the lowest humidity AND the time the lowest humidity occurred.
 * 
 * averageTemperatureInFile - one parameter, a CSVParser named parser - returns a double that represents the average TEMP in ONE file. 
 * testAverageTemperatureInFile() - void method - Prints the average temperature for the ONE file selected.
 * 
 * averageTemperatureWithHighHumidityInFile - two parameters - a CSVParser named parser and an integer named value. 
 *                                            returns a double that represents the average temperature of only those 
 *                                            temperatures when the humidity was greater than or equal to value.
 * testAverageTemperatureWithHighHumidityInFile() - void method to test averageTemperatureWithHighHumidityInFile 
 * 
 *                      HELPER Function(s)
 * getLargeOrSmallOfTwo -  4 parameters
 *                      CSVRecord currentRow            Current CSV record to test
 *                      CSVRecord largeOrSmallSoFar     largest or smallest record so far - always starts as NULL
 *                      String largeSmall               DO we wants largest or smallest record for selected field
 *                      String field                    The field name(Case sensitive) in CSV file we are looking 
 *                                                      for largest or smallest
 *                      Returns the CSVRecord for largest or smallest record
 */
public class CSVAssignment {
    // Print method so I don't have to keepentering System.out.println
    public static final void print(Object x) { System.out.println(x); }
    // Functional Methods are to be kept here, testing methods after these methods
    //****************************************************************************************
    //****************************************************************************************
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (Double.parseDouble(currentRow.get("TemperatureF")) != -9999.00) {
                smallestSoFar = getLargeOrSmallOfTwo(currentRow, smallestSoFar, "small", "TemperatureF");
            }
        }
        // Notice that largestSoFar is the entire record for the hottest day so all fields are available to report on
        return smallestSoFar;
    }
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        String smallestFile = null;
        // loopCounter only used for debugging
        int loopCounter = 0;
        // Get a list of files to search
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // Open the first 'f' file to get hottest temperature for that day
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser()); 
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                try {
                        smallestFile = f.getCanonicalPath();
                }
                catch(Exception e) {
                        print( e.getMessage());
                }
             } 
             else {
                loopCounter++;
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));    
                if (currentTemp < smallestTemp) {
                    smallestSoFar = currentRow;
                    try {
                        smallestFile = f.getCanonicalPath();
                    }
                    catch(Exception e) {
                        print( e.getMessage());
                    }
                }
        }
        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        }  
        return smallestFile;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (!currentRow.get("Humidity").equals("N/A")) {
                smallestSoFar = getLargeOrSmallOfTwo(currentRow, smallestSoFar, "small", "Humidity");
            }
        }
        return smallestSoFar;
    }
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;
        // Get a list of files to search
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // Open the first 'f' file to get hottest temperature for that day
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser()); 
            smallestSoFar = getLargeOrSmallOfTwo(currentRow, smallestSoFar, "small", "Humidity");
        }
        // Notice that largestSoFar is the entire record for the hottest day so all fields are available to report on
        return smallestSoFar;
    }
    public double averageTemperatureInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        double count = 0;
        double totalTemp = 0;
        for (CSVRecord currentRow : parser) {
            if (Double.parseDouble(currentRow.get("TemperatureF")) != -9999.00) {
                count++;
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                totalTemp = totalTemp + currentTemp;
            }
        }
        return (totalTemp/count);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        CSVRecord smallestSoFar = null;
        double count = 0;
        double totalTemp = 0;
        for (CSVRecord currentRow : parser) {
            if (Double.parseDouble(currentRow.get("TemperatureF")) != -9999.00) {
                if (Integer.parseInt(currentRow.get("Humidity")) >= value) {
                count++;
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                totalTemp = totalTemp + currentTemp;
                }
            }
        }
        if (count == 0) {
            return 0;
        }
        else {
            return (totalTemp/count);
        }
    }
    // Testing methods only, all names MUST start with 'test'
    //****************************************************************************************
    //****************************************************************************************
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile(parser);
        //The above 2 lines can be combined into the single line below
        //CSVRecord largest = hottestHourInFile(fr.getCSVParser());  
        print("Coldest temperature was " + smallest.get("TemperatureF")
                  + " at " + smallest.get("DateUTC"));
    }
    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        print("File with coldest temperature was " + coldestFile);
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile(parser);
        print("The coldest temperature on that day was " + smallest.get("TemperatureF"));
        print("All the temperatures on the coldest day were: ");
        CSVParser parser2 = fr.getCSVParser();
        for (CSVRecord currentRow : parser2) {
            print(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        }
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        print("Lowest humitity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord smallest = lowestHumidityInManyFiles();
        print("Lowest humidity was " + smallest.get("Humidity")
                  + " at " + smallest.get("DateUTC"));
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        print("Average temperature in file is " + average);
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average == 0) {
            print("No temperatures with that humidity");
        }
        else {
            print("Average Temp when high Humidity is " + average);
        }
    }
    // HELPER FUNCTIONS ARE TO BE KEPT IN THIS SECTIONS
    public CSVRecord getLargeOrSmallOfTwo(CSVRecord currentRow, CSVRecord largeOrSmallSoFar, 
                                            String largeSmall, String field) {
        if (largeOrSmallSoFar == null) {
            largeOrSmallSoFar = currentRow;
        } 
        else {
            if (largeSmall == "large") {
                double currentField = Double.parseDouble(currentRow.get(field));
                double largestField = Double.parseDouble(largeOrSmallSoFar.get(field));       
                if (currentField > largestField) {
                    largeOrSmallSoFar = currentRow;
                }
                }
            if (largeSmall == "small") {
                double currentField = Double.parseDouble(currentRow.get(field));
                double smallestField = Double.parseDouble(largeOrSmallSoFar.get(field));       
                if (currentField < smallestField) {
                    largeOrSmallSoFar = currentRow;
                }
            }
        }
        return largeOrSmallSoFar;
    }
}
