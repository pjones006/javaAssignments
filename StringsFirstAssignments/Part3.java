
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringA, String stringB) {
        // StringA is the substring we are searching for
        // stringB is the main string we are searching within for stringA
        String initialString = stringB;
        int index = stringB.indexOf(stringA);
        int count = 0;
        while (index != -1) {
            count++;
            stringB = stringB.substring(index + 1);
            index = stringB.indexOf(stringA);
        }

    System.out.println(stringA + " occurs " + count + " times in string " + initialString);
    if (count >= 2) {
        return true;
    } else {
        return false;
    }
}

    public String lastPart(String stringA , String stringB) {
        int sal = stringA.length();
        int sbl = stringB.length();
        int sPos = 0;
        String result = "";
        sPos = stringB.indexOf(stringA);
        if (sPos == -1) {
            return stringB;
        }
        result = stringB.substring(sPos + sal, sbl);
        return result;
    }
    public void testing() {
        String stringA = "in";
        String stringB = "builtinisnotbuiltoutbutinOccursTwice";
        Boolean result = twoOccurrences(stringA, stringB);
        System.out.println("Result is " + result);
        
        stringA = "in";
        stringB = "binltinisnotbuiltoutbutinOccursThree";
        result = twoOccurrences(stringA, stringB);
        System.out.println("Result is " + result);
        
        stringA = "atg";
        stringB = "ctgtatgta";
        result = twoOccurrences(stringA, stringB);
        System.out.println("Result is " + result);
        
        stringA = "an";
        stringB = "banana";
        String resultString = lastPart(stringA , stringB);
        System.out.println(stringA + " in " + stringB + " and the result is: " + resultString);
        
        stringA = "zoo";
        stringB = "forest";
        resultString = lastPart(stringA , stringB);
        System.out.println(stringA + " in " + stringB + " and the result is: " + resultString);
    }
}

