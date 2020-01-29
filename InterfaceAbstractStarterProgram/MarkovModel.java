import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{
    private int myNum;

    public MarkovModel(int n) {
        myNum = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public String toString() {
        return ("MarkovModel of order " + myNum);
    }
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-myNum);
        String key = myText.substring(index, index + myNum);
        sb.append(key);

        for(int k=0; k < numChars-myNum; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
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