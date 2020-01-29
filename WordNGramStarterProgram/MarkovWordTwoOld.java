
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovWordTwoOld implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwoOld() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public int indexOf(String[] words, String target1, String target2, int start) {
        for (int k=start; k<words.length-1;k++) {
            if (words[k].equals(target1) && words[k+1].equals(target2)){
                return k;
            }
        }
        return -1;
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            // System.out.println("folllows size is " + follows.size());
            // System.out.println("Index " + index);
            String next = follows.get(index);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1 || (start + 2 >= myText.length)) {
                break;
            }
            pos = start + 2;
            if (pos > myText.length) {
                break;
            }
            String next = myText[start + 2];
            follows.add(next);

        }
        // System.out.println("The keys are key1: " + key1 + " key2: " + key2 + " |words following are " + follows);
        return follows;  
    }
    public void testIndexOf() {
        String[] words = {"this","is","just","a","test","yes","this","iss","a","simple","test"};
        // for (int k = 0 ; k< words.length; k++) {
        System.out.println("this is 0: " + indexOf(words, "this" , "is", 0));
        System.out.println("this is 3: " + indexOf(words, "this", "is" , 3));
        System.out.println("test,yes 4: " + indexOf(words, "test", "yes" , 4));
      
    }
}
