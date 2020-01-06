import java.io.*;
import edu.duke.*;
/**
 * CaessarBreaker - Class used to break a caesarcipher encrypted string using one or two keys
 * 
 * @author Paul Jones
 * @version 12/23/2019
 * 
 * countLetters     Takes a string and counts the number of occurancees of each letter of the alphabet
 * @param       message     String to have characters alpha only characters counters
 * @return      counters    Integer array of counted lettters. IE: a=index 0 , z = index 25
 * 
 * maxIndex         Returns the index position of the array item that is largest , Assumes E(index 4) will be this letter.
 * @param       values      Array returned from countLetters
 * @return      indexPos    Index position of the largest value, this should be 'E'
 * 
 * getKey           Get the key used to enctrypt a message
 * @param   encrypted       The encrypted string
 * @return  dkey            The key used to encrypt the string. Calls methods countLetters and maxIndex to figure out the key
 * 
 * decrypt          Decrypt a message method
 * @param   encrypted       The ecrypted message
 * @param   dkey            The key used for the encryption
 * @return  decrypted       The decrypted message. Calls cc.encrypted method
 * 
 * decryptTwoKeys   Decrypt a message that was encrypted using two keys
 * @param   encrypted       The encrypted message
 * @return  decrypt         The decrypted message. Calls methods halfOfMessage, getKeys and cc.encryptTwoKeys
 * 
 * halfOfString             Takes a single string and splits it into two using every other character.
 * @param   message         The messsage to be split
 * @param   start           Index position of where to start splitting the message (should be 0 or 1)
 * @return  halfOfString    New string which is every other character starting from 'start'.
 * 
 * testCountLetters         Void method for testing countLetters
 * testHalfOfString         Void method to test halfOfString
 * testDecrypt              Void method to test decrypt. Calls cc.encrypt to encrypt message first
 * testDecryptTwoKeys       Void method to test decryptTwoKeys. Calls cc.encryptTwoKeys to encrypt message first
 */
public class CaeserBreaker {
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
    public String decrypt(String encrypted, int dkey) {
        CaesarCipher cc = new CaesarCipher();
        // freqs is array of all letters in the array and their counts
        String decrypted = cc.encrypt(encrypted, 26-dkey);
        return decrypted;
    }
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String string1 = halfOfString(encrypted, 0);
        String string2 = halfOfString(encrypted, 1);
        int key1 = getKey(string1);
        int key2 = getKey(string2);
        print("Key 1: " + key1);
        print("Key 2: " + key2);
        String decrypted = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        return decrypted;        
    }
    public String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i+=2) {
                halfString = halfString + message.charAt(i);
        }
        return halfString;
    }
     public void testCountLetters() {
        // String encrypted = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!tttttttttttttttttt";
        String message = "Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth";
        int[] freq = countLetters(message);
        int maxDex = maxIndex(freq);
        int dkey = maxDex -4;
        print("The following answer is if the string passed was enctrypted!");
        print("Max index is(this should be the encryption key)" + dkey);
    }
    public void testHalfOfString() {
        String message = "abcdefghijklmnopqrstuvwxyz";
        String halfString = halfOfString(message, 0);
        print("Half string of 0 is: " + halfString);
        halfString = halfOfString(message, 1);
        print("Half string of 1 is: " + halfString);
        message = "Qbkm Zgis";
        halfString = halfOfString(message, 0);
        print("Half string of 0 is: " + halfString);
        halfString = halfOfString(message, 1);
        print("Half string of 1 is: " + halfString);
    }
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String message = "The plains in spain blah blah blaheeeeeeeeeeeeeeeeeeeeeeeee";;
        int key = 21;
        String encrypted = cc.encrypt(message, key);
        print("--------------------Encrypted----key: " + key);
        print(encrypted);
        int dKey = getKey(encrypted);
        String decrypted = decrypt(encrypted, dKey);
        print("--------------------Decrypted message-------------------------------------");
        print(decrypted);
    }
    public void testdDecryptTwoKeys() {
         FileResource fr = new FileResource();
         String contents = fr.asString();
         CaesarCipher cc = new CaesarCipher();
         int key1 = 8;
         int key2 = 21;
         String message = "Just a test string with lots of eeeeeeeeeeeeeeeees ";
         String encrypted = cc.encryptTwoKeys(message, key1, key2);
         print("--------------------Encrypted----------Keys: " + key1 + " " + key2);
         print(encrypted);
         encrypted = "Xifqvximt tsdtlxzrx iijirvtl ek Uybi";
         // Changed encrypted to contents for testing decrypting  a file instead of a single message.
         String decrypted = decryptTwoKeys(contents);
         print("--------------------Decrypted-------------------------------------");
         print(decrypted);
        }
}
