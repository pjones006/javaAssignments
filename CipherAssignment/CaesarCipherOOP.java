import java.io.*;
import edu.duke.*;
/**
 * Write a description of CaesarCipherOOP here.
 * 
 * @author Paul Jones
 * @version 12/26/2019
 * 
 * CaesarCipherOOP - CaesarCipher rewritten using the OOP format
 * CaesarCipherOOP - Contructor for this class, created with key for encryption
 * @private - alphanet
 * @private - shiftedAlphabet
 * @private - mainKey(used during decryption)
 * 
 * encrypt      Encrypt a string using key used when creaating object
 * @param   input   String to encrypt
 * return   encrypted string
 * 
 * decrypt      Decrypt a string using (26 - key) used to create object
 * @param   encrypted   Encrypted string
 * return   decrypted string
 * 
 */

public class CaesarCipherOOP {
    public static final void print(Object x) { System.out.println(x); }
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipherOOP(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
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
    public String decrypt(String encrypted) {
        CaesarCipherOOP ccD = new CaesarCipherOOP(26 - mainKey);
        String decrypted = ccD.encrypt(encrypted);
        return decrypted;
    }
   
        
}

