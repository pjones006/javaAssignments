/**
 * Write a description of GladlibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class GladLibMap {
    private HashMap<String,ArrayList<String>> map;
    private ArrayList<String> usedWordList;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong//";
    
    public GladLibMap(){
            map= new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
            map= new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
            String [] Categories= {"adjective","noun","color"
                ,"country","name","animal","timeframe","verb","fruit"};
                
                for(String s : Categories) {
                    if(!map.containsKey(s)){
                    ArrayList<String> category= new ArrayList<String>();
                    category=readIt(source+s+".txt");
                    map.put(s,category);
                   }
                   
                }
        
            usedWordList=new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom(map.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int index=usedWordList.indexOf(sub);
        while(true)
        { if(index==-1)
            {
             usedWordList.add(sub);
             break;
            }
            else
            {
             sub = getSubstitute(w.substring(first+1,last));
             index=usedWordList.indexOf(sub);
            }
            
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap()
    {
       int size=0;
        for (String s : map.keySet()) {
               ArrayList<String> list = new ArrayList<String>();
               list=map.get(s);
               int length=list.size();
               size=size+length;
            } 
            return size;
    }
    public int totalWordsConsidered ()
    {
        System.out.println("List of words used: ");
        for(int k=0;k<usedWordList.size();k++)
        {
            System.out.println(usedWordList.get(k));
        }
        return usedWordList.size();
    }
    public void makeStory(){
        usedWordList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        int TotalWordsConsidered= totalWordsConsidered ();
        System.out.println("Number of words used "+TotalWordsConsidered);
        System.out.println(" ");
        System.out.println("Total number of words: "+totalWordsInMap());
        System.out.println(" ");
        System.out.println("Total words considered: "+totalWordsConsidered());
        }
}