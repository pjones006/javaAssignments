import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int myNum;

    public MarkovModel(int n) {
        myRandom = new Random();
        myNum = n;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
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

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1 || (start + key.length() >= myText.length())) {
                break;
            }
            pos = start + key.length();
            if (pos + 1 > myText.length()) {
                break;
            }
            String next = myText.substring(pos, pos + 1);
            follows.add(next);

        }
        return follows;
    }
}