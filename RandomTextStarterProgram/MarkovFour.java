import java.util.Random;
import edu.duke.*;
import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author Paul Jones
 * @version 01/23/2020
 * @Private variables           String myText, Random myRandom
 * MarkovOne()                  Constructor
 * setRandom(int seed)          Pre-seed RANDOM with an integer for testing purposes.
 * setTraining(String s)        Remove trailing spaces in myText
 * getFollows(String key)       Returns a String array of all characters that follow the key in myText
 * 
 * getRandomText(int numChars)
 *  Predict the next character, by finding all the characters that follow the current character in the training 
 *  text, and then randomly picking one of them as the next character.
 *              SB  sb          String Builder array to hold key and all characters that follow it in myText
 *              int index       random number within the length of myText - 1.
 *              int key         the character in myText at that index position
 */
public class MarkovFour {
    public static final void print(Object x) { System.out.println(x); }
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key) {
         ArrayList<String> follows = new ArrayList<String>();
         int pos = 0;
         int loops = 0;
         while (pos < myText.length()) {
             int startIdx = myText.indexOf(key, pos);
             if(startIdx == -1) {
                 break;
             }
             // DONT TOUCH THE FOLLOWING LINE IT MUST NOT BE >=
             if(startIdx + key.length() > myText.length() -1) {
                 print("Reached the end of myText!");
                 break;
             }
             String next = myText.substring(startIdx + key.length(), startIdx + key.length() + 1);
             follows.add(next);
             pos = startIdx + key.length();
            }
         return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            print("myText is empty!");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        // Get a random number, find character in myText at that position, add it to a new SB array
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            
            // Add that character to the end of the SB
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
 
}
