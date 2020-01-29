import edu.duke.*;
import java.util.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
        public static final void print(Object x) { System.out.println(x); }
        public void testGetFollows() {
            MarkovOne markov = new MarkovOne();
            String source  =  "this is a test yes this is a test.";
            markov.setTraining(source);
            ArrayList result = markov.getFollows("zx");
            print(result);
            print("Arraylist size is: " + result.size());
        }
        public void testGetFollowsWithFile() {
            FileResource fr = new FileResource();
            String st = fr.asString();
            MarkovOne markov = new MarkovOne();
            markov.setTraining(st);
            ArrayList result = markov.getFollows("he");
            print("Arraylist size is: " + result.size());
        }
}
