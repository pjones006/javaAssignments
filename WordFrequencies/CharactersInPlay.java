import edu.duke.*;
import java.util.ArrayList;
/**
 * Write a description of MainCharacters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    public static final void print(Object x) { System.out.println(x); }
    private ArrayList<String> characters;
    private ArrayList<Integer> chrFreqs;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        chrFreqs = new ArrayList<Integer>();
    }
    public void update(String person) {
            person = person.toLowerCase();
            int index = characters.indexOf(person);
            if (index == -1){
                characters.add(person);
                chrFreqs.add(1);
                // System.out.println("Added: " + person);
            }
            else {
                int freq = chrFreqs.get(index);
                chrFreqs.set(index,freq+1);
            }
        
        
    }
    public void findAllCharacters(){
        FileResource resource = new FileResource();
        characters.clear();
        chrFreqs.clear();
        
        for(String s : resource.lines()){
            int pos = s.indexOf(".");  
            if(pos != -1) {
                    String chr = s.substring(0,pos);
                    update(chr);
                    // print(chr);
            }
             
        }
    }
    public void tester(){
        findAllCharacters();
        System.out.println("# unique characters: "+ characters.size());
        int sz = characters.size();
        double q = (sz/80);
        
        for (int k = 0; k<characters.size() ;  k++) {
            String s = characters.get(k);
            int a = chrFreqs.get(k);
            if(a >= q && a > 1) {
                System.out.println("Character is: " + s + " occurences: " + a);
            }
        }
        print("");
        charactersWithNumParts(10,15);
    }
    public void charactersWithNumParts(int num1, int num2) {
        print("Here are characters with speaking parts GE: " + num1 + " and LE: " + num2);
        for (int k = 0; k<characters.size() ;  k++) {
            String s = characters.get(k);
            int a = chrFreqs.get(k);
            if(a >= num1 && a <= num2) {
                System.out.println("Character is: " + s + " occurences: " + a);
            }
            
        }
        
        
    }
}
