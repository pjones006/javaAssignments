import java.io.*;
import edu.duke.*;

/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Paul Jones
 * @version 12/27/2019
 * 
 * CaesarCipherTwo - Create the CaesarCipher twoKeys methods using OOP class
 * CaesarCipherTwo - Constructor for this class, called with key1 and key2
 * @private field   - alphabet - entire alphabet
 * @private field   - shiftedAlphabet1 - shifted based upon key1
 * @private field   - shiftedAlphabet2 - shifted based upon key2
 * @private field   - mainKey1 - Used for decrypting
 * @private field   - mainKey2 - Used for decrypting
 * 
 * encrypt - Encrypt a string using two keys and return it
 * @param   - input     String to encrypt
 * return   -           String encrypted
 * 
 * decrypt  - Decrypt a string using the two keys subtracted from 26. Creates new CaesarCipherTwo object with (26 - key1,2)
 * @param   - encrypt   Encrypted string
 * return   -           String decrypted
 */
public class CaesarCipherTwo {
    public static final void print(Object x) { System.out.println(x); }
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = 26 - key1;
        mainKey2 = 26 - key2;
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
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
        // print(encrypted.toString());
        return encrypted.toString();
    }
    public String decrypt(String encrypted) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(mainKey1, mainKey2);
        String decrypted = cc2.encrypt(encrypted);
        return decrypted;        
    }
      
        
}

