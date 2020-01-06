
/**
 * Count common words in Shakespeare's works
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;

public class CommonWords
{   // get 20 most common words into an array and return it.
    // each word is assigned to an array index position from 0 to 20.
    public String[] getCommon(){
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20]; // common is the name of the array holding 20 ommon words
        int index = 0;
        for(String s : resource.words()){
            common[index] = s;
            index += 1;
        }
        return common;          // key = int(index pos) , value = word
    }
    // return index positon of a word , list is the 20 common words list
    public int indexOf(String[] list, String word) {
        for (int k=0; k<list.length; k++) {
            if (list[k].equals(word)) {
                   return k;
               }
           }
        return -1;
    }
    // resource = file(a play) , common is string array of 20 common words, counts is an int array of 20 for the 20 commons words
    public void countWords(FileResource resource, String[] common, int[] counts){
        // take the file and for each word in file, see it its a common word, if its is
        for(String word : resource.words()){
            word = word.toLowerCase();
            int index = indexOf(common,word); // find index pos of the common word
            if (index != -1) {                  // increment the counter is common word is found
                counts[index] += 1;
            }
        }
    }
    // NOTE: common is the list of common words, counts is those words counted (key(index) is the count of the word while
    //                                                                          value is the word itself
    void countShakespeare(){
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
                     "likeit.txt", "macbeth.txt", "romeo.txt"};
            // String[] plays = {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length]; // counts is the array name corresponding to 20 common words
        for(int k=0; k < plays.length; k++){
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource,common,counts);
        }
        
        for(int k=0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
}
