
/**
 * Write a description of WordPlay here.
 * 
 * @author (Paul Jones
 * @version (12/17/2019)
 * 
 * isVowel          Method to figure out if the character is a vowel or not.
 * @param           ch          The character to be tested
 * @return          t/f         Returns true of false
 * 
 * replaceVowels    phrase      The phrase to have vowels replaced
 * @param           ch          The character to replace the vowels with
 * return           newPhrase   The new phrase with vowels replaced 
 * 
 * emphasize        Method to replace a character with '*' or '+' depending on its odd/even index position in the phrase.
 * @param           phrase      The phrase used replacing characters.
 * @param           ch          The character to have replaced.
 * @return          newPhrase   The new phrase with substitutions.
 * 
 * testIsVowel          void method for testing isVowel
 * testReplaceVowels    void method for testing replaceVowels
 * testEmphasize        void method for testing emphasize
 * 
 */
public class WordPlay {
    public static final void print(Object x) { System.out.println(x); }
    public boolean isVowel(Character ch) {
        String vowels = "AEIOU";
        int idx = vowels.indexOf(Character.toUpperCase(ch));
        if (idx != -1) {
            return true;
        } else {
            return false;
        }
    }
    public String replaceVowels(String phrase, Character ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            if ( isVowel(currChar)) {
                newPhrase.setCharAt(i, ch);
            } else {
                newPhrase.setCharAt(i, currChar);
            }
        }
        return newPhrase.toString();
    }
    public String emphasize(String phrase, Character ch) {
          StringBuilder newPhrase = new StringBuilder(phrase);
          for (int i = 0; i < phrase.length(); i++) {
              char currChar = phrase.charAt(i);
              if (Character.toUpperCase(currChar) == Character.toUpperCase(ch)) {
                    if (i % 2 == 0) {
                       newPhrase.setCharAt(i, '*');                 
                    } else {
                        newPhrase.setCharAt(i, '+');
                    }
              } else {
                  newPhrase.setCharAt(i, currChar);
              }
          }
          return newPhrase.toString();
    }
    
    public void testIsVowel() {
        Character ch = 'a';
        boolean result = isVowel(ch);
        print(ch + " is a vowel? " + result);
        
        ch = 'g';
        result = isVowel(ch);
        print(ch + " is a vowel? " + result);
        
        ch = 'A';
        result = isVowel(ch);
        print(ch + " is a vowel? " + result);
        
        ch = '@';
        result = isVowel(ch);
        print(ch + " is a vowel? " + result);
        
    }
    public void testReplaceVowels() {
        String phrase = "The rain in spain stays......";
        Character ch = '*';
        String result = replaceVowels(phrase, ch);
        print("old: " + phrase);
        print("new: " + result);print("");
        
        phrase = "All work and now play!";
        ch = '#';
        result = replaceVowels(phrase, ch);
        print("old: " + phrase);
        print("new: " + result);print("");
        // print(0 % 2); // This returns 0
        // print(1 % 2); // this returns 1
        // print(2 % 2);
        // print(3 % 2);
         
    }
    public void testEmphasize() {
        String testLine = "*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+";
        String phrase =   "dna ctgaaactga";
        Character ch = 'a';
        String result = emphasize(phrase, ch);
        print(testLine);
        print("old: " + phrase);
        print("new: " + result);print("");
        
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        print(testLine);
        result = emphasize(phrase, ch);
        print("old: " + phrase);
        print("new: " + result);print("");
        
    }
}
