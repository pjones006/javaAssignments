/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    
    private String pip;
    private String phrase;
    
    public PhraseFilter(String pInPhrase, String phraseToSearchIn) {
        pip = pInPhrase;
        phrase = phraseToSearchIn;
    }

    public boolean satisfies(QuakeEntry qe) {
        String name = qe.getInfo();
        if(pip.equals("any"))
        {
            if(name.contains(phrase)) { return true;}
        }
        if(pip.equals("end"))
        {
            if(name.endsWith(phrase)) { return true;}
        }
        if(pip.equals("start"))
        {
            if(name.startsWith(phrase)) { return true;}
        }
        return false;
    }
     public String getName(){
        return "PhraseFilter";
    }
}