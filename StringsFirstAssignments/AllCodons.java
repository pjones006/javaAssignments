
/**
 * Write a description of AllCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodons {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);            
            }
        
        }
    return dnaStr.length();
    }
    
    public String fIndGene(String dna) {
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex + 3);
        // Keep searching through the dna string until the gene is divisible by 3 and then return that gene
        while (currIndex != -1) {
            if((currIndex - startIndex) % 3 ==0) {
                return dna.substring(startIndex, currIndex + 3);
        }
        else {
            currIndex = dna.indexOf("TAA" , currIndex + 1);
        }
    }        return "";
}
}
