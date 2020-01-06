import java.util.*;
import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        String [] myWords = new String[100];
        for(String word : resource.words()){
                    myWords.add(word.toLowerCase());
        
        }
    }
}