
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.out.println("start: " + start);
        System.out.println("myWords: " + myWords);
        System.out.println("size: " + size);
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return this.myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for(String word : myWords) {
            ret = ret + word + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k < myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;

    }

       public WordGram shiftAdd(String word) {	       
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String[] newWords = new String[this.myWords.length];
        for (int i=0;i<newWords.length-1;i++) {
        	newWords[i] = this.wordAt(i+1);
        }
        newWords[newWords.length-1] = word;
        WordGram out = new WordGram(newWords, 0, newWords.length);
        return out;
    }
}