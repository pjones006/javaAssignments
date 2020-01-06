import java.io.*;
import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author  Paul Jones
 * @version 12/27/2019
 * 
 * TestCaesarCipherTwo - Test class for testing CaesarCipherTwo
 * 
 * countLetters - Counts all the letters in a string an returns an array of lengths for each letter
 * halfOfString - returns a string of every other character in given string, starting at 'pos'.
 * maxIndex     - returns index position of array item containing the most occuring letter.
 * 
 */
public class TestCaesarCipherTwo {
        public static final void print(Object x) { System.out.println(x); }
        private int[] countLetters(String message) {
            String alpha = "abcdefghijklmnopqrstuvwxyz";    
            int[] counters = new int[26];       
            for (int k=0; k<message.length(); k++) {  
                    char ch  = Character.toLowerCase(message.charAt(k));      
                    int dex = alpha.indexOf(ch);
                    if (dex != -1) {   
                        counters[dex] += 1;       
                    }
            }   
            return counters;
    }
    private String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i+=2) {
                halfString = halfString + message.charAt(i);
        }
        return halfString;
    }
    private int maxIndex(int[] values) {
        int max = 0;
        int indexPos = 0;
        for (int i=0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                indexPos = i;
            }
        }
        return indexPos;
    }
    private int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex -4;
        if (maxDex < 4) {
            // since 'e' is 4 we must wraparound the shifting
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    public String breakCaesarCipher(String input) {
        String string1 = halfOfString(input, 0);
        String string2 = halfOfString(input, 1);
        int key1 = getKey(string1);
        int key2 = getKey(string2);
        print("Keys: " + key1 + ":" + key2);
        CaesarCipherTwo bcc = new CaesarCipherTwo(key1,key2);
        String decoded = bcc.decrypt(input);
        return decoded;        
    }
    
    public void simpleTests() {
         FileResource fr = new FileResource();
         String contents = fr.asString();
         CaesarCipherTwo cc2 = new CaesarCipherTwo(17,3);
         
         String encrypted = cc2.encrypt(contents);
         print("Encrypted");
         print(encrypted);
         
         // String decrypted = cc2.decrypt(encrypted);
         // print("Decrypted using CaesarCipherTwo");
         // print(decrypted);

         String decrypted2 = breakCaesarCipher(encrypted);
         print("Decrypted using breakCaesarCipher");
         print(decrypted2);
    }
    
    
    
    
}
