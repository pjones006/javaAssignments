/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Paul Jones
 * @version 12/13/2019
 * printNames - void method - print fields from selected CSV file
 * 
 * getRank() - 3 params(int year, String name, String gender) - returns rank(int) of name popularity for that year by gender
 * testGetRank() - void method for testing getRank
 * 
 * getName() - 3 params((int year, int rank, String gender) - returns name for the given rank for the selected year by gender
 * testGetName() -void method for testing getName
 * 
 * whatIsNameInYear() - 4 params(String name, int year, int newYear, String gender) - returns the current rank of the name given
 *                      for the given year and the equivalent name for the 2nd year given based upon that ranking. for example 'emma'
 *                      ranks 2 in 2012 and in 2013 'olivia' is the 2nd in ranking. Uses getName() and getRank() methods.
 * testWhatIsNameInYear() - void method for testing whatIsNameInYear().
 * 
 * yearOfHighestRank() - 2 parameters(String name, String gender) - returns the year(int) with the highest rank for the name
 *                       and gender for a rank of files selected.
 * testYearOfHighestRank() - void method for testing yearOfHighestRank()
 * 
 * getAverageRank() - 2 parameters(String name, String gender - returns double with the average rank for selected files.
 * testGetAverageRank() - void method for testing getAverageRank()
 * 
 * getTotalBirthsRankedHigher() - 3 parameters(int year, String name, String gender) - returns  returns an integer, the 
 *                                total number of births of those names with the same gender and same year who are 
 *                                ranked higher than name.
 * testGetTotalBirthsRankedHigher() - void method for testing getTotalBirthsRankedHigher()
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public static final void print(Object x) { System.out.println(x); }
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        String sYear = Integer.toString(year);
        String filename = ("data/yob" + sYear + ".csv");
        FileResource fr = new FileResource(filename);
        CSVParser parser =  fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                   return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        int counter = 0;
        String sYear = Integer.toString(year);
        String filename = ("data/yob" + sYear + ".csv");
        FileResource fr = new FileResource(filename);
        CSVParser parser =  fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                counter++;
                if (rank == counter) {
                     counter = 0;
                     return rec.get(0);                 
                }
            } 
            
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int birthRank = getRank(year, name, gender);
        if (birthRank != -1) {
            String newName = getName(newYear, birthRank, gender);
            print(name + " Born in " + year + " would be " + newName + " in " + newYear);
        }
        else { print("No record in files for " + name + " born in " + year); }
    }
    public int yearOfHighestRank(String name, String gender) {
        int currentRank = 0;
        int highestRank = 999999999;
        int highestYear = 0;
        boolean exists = false;        
        int counter = 0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // counter++;
            // print("Loop: " + counter);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            currentRank = getRank(year, name, gender);
            if (currentRank != -1) {
                exists = true;
            }
            if (currentRank < highestRank && currentRank != -1) {
                highestRank = currentRank;
                highestYear = year;
            }
        }
        if (exists) {
            return highestYear;
        }
        else {
            return -1;
        }
    }
    public double getAverageRank(String name, String gender) {
        int counter = 0;
        double averageRank = 0.00;
        double totalRank = 0.00;
        double currentRank = 0.00;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            currentRank = getRank(year, name, gender);
            if (currentRank != -1) {
                counter++;
                totalRank = totalRank + currentRank;                
            }
        }
        if (totalRank == 0) {
            return -1.0;
        }
        else {
            averageRank = (totalRank/counter);
            return averageRank;
        }
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
         String sYear = Integer.toString(year);
         String filename = ("data/yob" + sYear + ".csv");
         FileResource fr = new FileResource(filename);
         int totalBirths = 0;
         for (CSVRecord rec : fr.getCSVParser(false)) {
             if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                 return totalBirths;                 
                }
             else {
                if (rec.get(1).equals(gender)) {
                totalBirths += Integer.parseInt(rec.get(2));
                }
              }
         }
         return totalBirths;
    }
        
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public void testGetRank() {
        String name = "Paul";
        String gender = "M";
        int year = 1964;
        int rank = getRank(year, name, gender);
        print(name + " " + gender + " is ranked " + rank + " for " + year);
        
        name = "Emily";
        gender = "F";
        year = 1960;
        rank = getRank(year, name, gender);
        print(name + " " + gender + " is ranked " + rank + " for " + year);
        
        name = "Frank";
        gender = "M";
        year = 1971;
        rank = getRank(year, name, gender);
        print(name + " " + gender + " is ranked " + rank + " for " + year);
    }
    public void testGetName() {
        String name = getName(1980, 350, "F");
        print ("1980: Number 350 female is :" + name);
        
        name = getName(1982, 450, "M");
        print ("1982: Number 450 male is :" + name);
        
        name = getName(2012, 123, "F");
        print ("2012: Number 123 female is :" + name);
    }
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
        whatIsNameInYear("Emma", 2012, 2014, "F");
        whatIsNameInYear("Mason", 2012, 2014, "M");
        whatIsNameInYear("William", 2012, 2014, "M");
        whatIsNameInYear("Paul", 1964, 2014, "M");
               
    }
    public void testYearOfHighestRank() {
        String name = "Genevieve";
        String gender = "F";
        int highestRankYear = yearOfHighestRank(name, gender);
        print(name + ": Year of highest ranking is " + highestRankYear); print("");
        
        name = "Mich";
        gender = "M";
        highestRankYear = yearOfHighestRank(name, gender);
        print(name + ": Year of highest ranking is " + highestRankYear);print("");
        
        // name = "Paul";
        // gender = "M";
        // highestRankYear = yearOfHighestRank(name, gender);
        // print(name + ": Year of highest ranking is " + highestRankYear); print("");
                
    }
    public void testGetAverageRank() {
        String name = "Susan";
        String gender = "F";
        double averageRank = getAverageRank(name, gender);
        print(name + ": Average rabking is " + averageRank); print("");
        
        name = "Robert";
        gender = "M";
        averageRank = getAverageRank(name, gender);
        print(name + ": Average ranking is " + averageRank);print("");
        
        // name = "Jacob";
        // gender = "M";
        // averageRank = getAverageRank(name, gender);
        // print(name + ": Average ranking is " + averageRank); print("");
        
    }
    public void testGetTotalBirthsRankedHigher() {
        String name = "Emily";
        String gender = "F";
        int year = 1990;
        int totalBirths = getTotalBirthsRankedHigher(year, name, gender);
        print("Total " + gender + " births ranked higher for " + name + " in " + year + " is " + totalBirths);
                
        name = "Drew";
        gender = "M";
        year = 1990;
        totalBirths = getTotalBirthsRankedHigher(year, name, gender);
        print("Total " + gender + " births ranked higher for " + name + " in " + year + " is " + totalBirths);
        
        name = "William";
        gender = "M";
        year = 2012;
        totalBirths = getTotalBirthsRankedHigher(year, name, gender);
        print("Total " + gender + " births ranked higher for " + name + " in " + year + " is " + totalBirths);
    }
}
