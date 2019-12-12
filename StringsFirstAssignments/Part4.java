
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    // This first one is better and shorter then the second
    public String findYouTube2(String url) {
        URLResource file = new  URLResource(url);
    for (String item : file.words()) {
           String itemLower = item.toLowerCase();
           int pos = itemLower.indexOf("youtube.com");
           if (pos != -1) {
               int beg = item.lastIndexOf("\"",pos);
               int end = item.indexOf("\"", pos+1);
               System.out.println(item.substring(beg+1,end));
               }
    }
    return "Done";
}
        
        
        
        
    public String findYouTube(String url) {
        // String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html"
        URLResource ur = new URLResource(url);
        //FileResource fr = new FileResource(url);
        int count = 0;
    
    for (String word : ur.words()) {
        String origWord = word;
        word = word.toLowerCase();
        if (word.startsWith("href")) {
            int wordLength = word.length();
            int start = word.indexOf("\"");
            int end = word.lastIndexOf("\"", wordLength);
            if (start == end) {
                end = wordLength;
            }
            String justUrl = word.substring(start + 1, end);
            // Keep original copy of URL before forcing lowercase
            String backupUrl = justUrl;
            
            justUrl = justUrl.toLowerCase();
            //System.out.println("Short url " + justUrl);
            int pos = justUrl.indexOf("youtube.com");
            if (pos != -1) {
                count = count + 1;
                System.out.println("URL is " + backupUrl + " " + count);
            }
        }
    }
    return "done";
}

    public void testing() {
        String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        String result = findYouTube2(url);
        System.out.println(result);
        
    }
}