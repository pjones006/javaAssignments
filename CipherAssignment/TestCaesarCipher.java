import java.io.*;
import edu.duke.*;

/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Paul Jones
 * @version 12/27/2019
 * 
 * TestCaesarCipher Test class for CaesarCipherOOP
 * countLetters -   Count all aletters in string and return an array
 * maxIndex         What index has the letter with the most counts
 * getKey           Uses countLetters and maxIndex to return maxIndex - 4, this is where alphabet should start if 'E' is most
 *                  common letter used.
 * breakCaesarCipher - Decode using CaesarCipherOOP class
 * simpleTests       - Encode using  CaesarCipherOOP and decode using breakCaesarCipher
 */
public class TestCaesarCipher {
    public static final void print(Object x) { System.out.println(x); }
    public int[] countLetters(String message) {
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
    public int maxIndex(int[] values) {
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
    public int getKey(String encrypted) {
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
         print("Encoded message");
         print(input);
         int dKey = getKey(input);
         CaesarCipherOOP ccOOP = new CaesarCipherOOP(dKey);
         String decoded = ccOOP.decrypt(input);
         return decoded;
    }
    public void simpleTests() {
         FileResource fr = new FileResource();
         String contents = fr.asString();
         // Encrypt using OOP method
         CaesarCipherOOP cc = new CaesarCipherOOP(18);
         String coded = cc.encrypt(contents);
         print("Encoded message");
         print(coded);
         
         // Here is the OOP method for decrypting, key was passed above when object was created
         String decoded = cc.decrypt(coded);
         print("Decoded message(OOP method");
         print(decoded);
         
         // Here we use breakCaesarCipher to figure out key, decrypt and return decoded message
         String decoded2 = breakCaesarCipher(coded);
         print("Decoded message(breakCaesarCipher method");
         print(decoded2);
    }
    // Use CaesarCipher (old method) to figure out key, Use CaesarCipherOOP to decrypt Input is a coded message
    
}
