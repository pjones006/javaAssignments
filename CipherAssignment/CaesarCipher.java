import java.io.*;
import edu.duke.*;
/**
 * CaesarCipher - Class used to encrypt a string using one or two keys
 * 
 * @author  Paul Jones
 * @version 12/20/2019
 * encrypt  -           Takes a Single string and encrypts it with using a key
 * @param   input       String to be encrytped
 * @param   key         Key used for shifting alphabet
 * @return              Encrypted string
 * 
 * encryptTwoKeys -     Takes a Single string and encrypts it with using two keys
 * @param   input       String to be encrypted
 * @param   key1        First key used for shifting alphabet
 * @param   key2        Second key used for shifting alphabet
 * @return              Encrypted string
 * 
 * testEncryptTwoKeys   Void method for testing encryptTwiKeys
 */


public class CaesarCipher {
    public static final void print(Object x) { System.out.println(x); }
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean lower = false;
            if (Character.isLowerCase(currChar)) {
                    lower = true;
            } 
            currChar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if (lower) {
                    newChar = Character.toLowerCase(newChar);
                } else {
                    newChar = Character.toUpperCase(newChar);
                }
               encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
                      
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            boolean lower = false;
            if (Character.isLowerCase(currChar)) {
                    lower = true;
            } 
            currChar = Character.toUpperCase(currChar);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar1 = shiftedAlphabet1.charAt(idx);
                char newChar2 = shiftedAlphabet2.charAt(idx);
                if (lower) {
                    newChar1 = Character.toLowerCase(newChar1);
                    newChar2 = Character.toLowerCase(newChar2);
                } else {
                    newChar1 = Character.toUpperCase(newChar1);
                    newChar2 = Character.toUpperCase(newChar2);
                }
                if(i % 2 == 0) {
                    encrypted.setCharAt(i, newChar1);
                } else {
                    encrypted.setCharAt(i,newChar2);
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

   
   
    public void testEncryptTwoKeys() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key1 = 14;
        int key2 = 24;
        // String encrypted = encryptTwoKeys(message, key1, key2);
        // print("--------------------Encrypted----------Keys: " + key1 + " " + key2);
        // print(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        print("--------------------Decrypted-------------------------------------");
        print(decrypted);
    }
    public void testDecrypt() {
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 21;
        String encrypted = encrypt(message, key);
        print("E: " + encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        print("D: " + decrypted);
    }
    public void testEncrypt() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;
        String encrypted = encrypt(message, key);
        print("Message: ");
        print(message);
        print("Encrypted");
        print(encrypted);
    }
    
}

