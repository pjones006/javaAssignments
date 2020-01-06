import java.io.*;
import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author Paul Jones 
 * @version - 12/20/2019
 * 
 * countWordLengths -   Updates counts[] array with word lengths of all words in the file passed to it.
 * @param   resource        The file of words to be counted.
 * @param   counts[]        An empty array that will be populated by this method.The index is also the word length in 
 *                          this array. IE: index 3's value is the number of words 3 characters long. NOTE: The first and last 
 *                          characters must be alpha characters to count in the word length.
 *                          
 * indexOfMax           Find the most common occuring word length from an array
 * @param   counts[]    The array of word lengths
 * @return  max         The index position of the most common occuring word length
 * 
 * testCountWordLengths void method for testing countWordLengths() and indexOfMax().
 * 
 * 
 */
public class WordLengths {
    public static final void print(Object x) { System.out.println(x); }
    public void countWordLengths(FileResource resource, int[] counts) {
        boolean firstOrLast = false;
        for(String word : resource.words()){
            int wLength = 0;
            for(int i = 0; i < word.length(); i++) {
                firstOrLast = false;
                Character ch = word.charAt(i);
                

                if (i == 0 || (i == word.length() - 1)) {
                    firstOrLast = true;
                }
                    
                if (Character.isLetter(ch)){
                    wLength += 1;
                }
                if(!Character.isLetter(ch) && !firstOrLast) {
                    wLength += 1;
                }
            }
            counts[wLength] += 1;
        }
    }
    public int indexOfMax(int[] values) {
        int max = 0;
        int maxIdx = 0;
        for (int i=0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                maxIdx = i;
                print ("if values[i]: " + values[i] + "> " + max);
            }
        }
        return maxIdx;
    }
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int [] counts = new int[31];
        countWordLengths(resource, counts);
        int most = indexOfMax(counts);
        for(int k=1; k < counts.length; k++){
            if (counts[k] != 0) {
                print("Number of words at length of " + k + " is "  + counts[k]);
            }
        }
        print("The most common word length is: " + most);
        
    }
}


