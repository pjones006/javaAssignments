import java.io.*;
import edu.duke.*;
/**
 * Write a description of JunkClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JunkClass {
    public static final void print(Object x) { System.out.println(x); }
    public void counterCharactersInString(String s) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";    // 'a' = 0 and 'z' = 25 for the index variable
        int[] counters = new int[26];       // Initialize for 26 letters of the alphabet
        for (int k=0; k<s.length(); k++) {  // Iterate over each character in the 's' string
            char ch  = s.charAt(k);         // assign each character of the string to 'ch'
            int index = alpha.indexOf(Character.toLowerCase(ch)); // find the index of that character 'ch' in the alphabet
            if (index != -1) {              // Only save counts of letters in the alphabet, ignore non alpha characters
                counters[index] += 1;       // Add 1 to the value of that alphabet index. IE: 'f' = 5
            }
        }
        for (int k = 0; k< counters.length; k++) {  // loop through the 26 letters of the alphabet, by their index #
            print(alpha.charAt(k) + "\t| "+ counters[k]);
        }
    }
    public void testCounterCharactersInString() {
        String junkString = "The rains is spain stay mainly on the plains";
        counterCharactersInString(junkString);
    }
}


