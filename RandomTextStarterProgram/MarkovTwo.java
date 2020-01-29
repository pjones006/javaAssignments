import java.util.Random;
import edu.duke.*;
import java.util.*;
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovTwo {
    public static final void print(Object x) { System.out.println(x); }
    private String myText;
    private Random myRandom;
    
    public MarkovTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();        
        int pos = 0;     
        int kl = key.length();
        while (pos < myText.length()) {            
            int start = myText.indexOf(key, pos);            
            if(start == -1){                
                break;            
            }            
            if(start + key.length() >= myText.length()-key.length()) {
                break;            
            }     
            String next = myText.substring(start + kl, start + kl + 1); 
            follows.add(next);            
            pos = start+key.length();        
        }  
        return follows;   
    }
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-3);
        String key = myText.substring(index, index+3);
        sb.append(key);
       
        for(int k=0; k < numChars-3; k++){
            ArrayList<String> follows = getFollows(key);
            // System.out.println("key " + key + " follows " + follows);
            if( follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
}
