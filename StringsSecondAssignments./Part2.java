
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
public int twoOccurrences(String stringA, String stringB) {
        // StringA is the substring we are searching for
        // stringB is the main string we are searching within for stringA
        String initialString = stringB;
        int index = stringB.indexOf(stringA);
        int count = 0;
        int alength = stringA.length();
        System.out.println("idx " + index + " alength " + alength);
        while (index != -1 && alength != 0) {
            count++;
            stringB = stringB.substring(index + alength);
            index = stringB.indexOf(stringA);
        }

    System.out.println(stringA + " occurs " + count + " times in string " + initialString);
    return count;
}
public void testHowMany() {
       String str1 = "aa";
       String str2 = "dsadaafasdsaaaafdggffaafsdasdfdafrewaa";
       twoOccurrences(str1, str2);
       str1 = "xyz";
       str2 = "the big bad sheep is very wary";
       twoOccurrences(str1, str2);
       str1 = "";
       str2 = "ashfaskh";
       twoOccurrences(str1, str2);
       str1 = "abc";
       str2 = "dsadaafasdsaaaafabcffaafsdasdfdafrabca";
       twoOccurrences(str1, str2);
        }
}
