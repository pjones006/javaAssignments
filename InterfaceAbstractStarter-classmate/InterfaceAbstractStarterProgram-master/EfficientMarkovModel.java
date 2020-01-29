import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int myNum;
    private HashMap<String,ArrayList<String>> followsMap;

    public EfficientMarkovModel(int n) {
        myNum = n;
        followsMap = new HashMap<>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo(followsMap);
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

    public String toString() {
        return "This is an EfficientMarkovModel class of order " + myNum;
    }

    public ArrayList<String> getKeyValues(String key) {
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

    public void buildMap() {
        ArrayList<String> follows = new ArrayList<>();
        int index = 0;
        String key = myText.substring(index, index + myNum);
        for(int k=0; k < myText.length(); k++){
            if (! followsMap.containsKey(key)) {
                follows = getKeyValues(key);
                followsMap.put(key, follows);
            }
            if (follows.size() == 0) {
                break;
            }
            String next = myText.substring(index + myNum, index + myNum + 1);
            key = key.substring(1) + next;
            index++;
        }
    }

    public ArrayList<String> getFollows(String key) {
        return followsMap.get(key);
    }

    public void printHashMapInfo(HashMap<String, ArrayList<String>> followsMap) {
        int largestValue = 0;
        String largestKey = null;
        for (String key : followsMap.keySet()) {
            System.out.println(key + "\t" + followsMap.get(key));
            if (followsMap.get(key).size() > largestValue) {
                largestKey = key;
                largestValue = followsMap.get(key).size();
            }
        }
        System.out.println("FollowsMap contains " + followsMap.size() + " keys");
        System.out.println("The key with the most following characters was " + largestKey + " with " + largestValue + " values");
    }

}
