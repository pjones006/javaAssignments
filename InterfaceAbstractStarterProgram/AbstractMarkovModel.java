
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {
    public static void print(Object x) { System.out.println(x); }
    protected String myText;
    protected Random myRandom;
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1 || (start + key.length() >= myText.length())) {
                break;
            }
            pos = start + key.length();
            if (pos + 1 > myText.length()) {
                break;
            }
            String next = myText.substring(pos, pos + 1);
            follows.add(next);

        }
        return follows;
    }
    abstract public String getRandomText(int numChars);

}
