
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
public int findStopCodon(String dnaStr,
                              int startIndex,
                              String stopCodon){
         
         int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
         while (currIndex != -1) {
             if((currIndex - startIndex) % 3 ==0) {       
                 return currIndex;
             } else {
                currIndex = dnaStr.indexOf(stopCodon , currIndex + 1);
             }
         }
         return dnaStr.length();
    }
    
public String findGene(String dna, int where) {
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        
       int taaIndex = findStopCodon(dna, startIndex, "TAA");
       int tagIndex = findStopCodon(dna, startIndex, "TAG");
       int tgaIndex = findStopCodon(dna, startIndex, "TGA");    
       // int temp = Math.min(taaIndex, tagIndex);
       // int minIndex =  Math.min(taaIndex,Math.min(tagIndex, tgaIndex));
       int minIndex =0;
       if (taaIndex == -1 || 
           (tgaIndex != -1 && tgaIndex < taaIndex)) {
           minIndex = tgaIndex;
            }
       else {
           minIndex = taaIndex;
       }
       if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
           minIndex = tagIndex;           
       }
       if (minIndex == -1) {
           return "";
       }
       return dna.substring(startIndex, minIndex + 3);
}

public void testFindStopCodon() {
    String dna = "cccatggggTAAaataataatTGAxx";
    int result = findStopCodon(dna, 0, "TGA");
    System.out.println("The dna string is: " + dna);
    System.out.println("The first occurence of TGA is index: " + result);
    dna = "cccatggggTAAaataataatTAAxx";
    result = findStopCodon(dna, 0, "TGA");
    System.out.println("The dna string is: " + dna);
    System.out.println("The first occurence of TGA is index: " + result);
    dna = "cccatggggTAAaataatTAGxx";
    result = findStopCodon(dna, 0, "TAG");
    System.out.println("The dna string is: " + dna);
    System.out.println("The first occurence of TAG is index: " + result);
    }
    
 public void testfindGene() {
        //            012345678901234567890123456789
        String dna = "cccATGgggTAAaataataatTAAxx";
        String result = findGene(dna, 0);
        System.out.println("DNA string is: " + dna);
        System.out.println(result);
        
        dna = "cccATGgggTGAaataataatxx";
        result = findGene(dna, 0);
        System.out.println("DNA string is: " + dna);
        System.out.println(result);
        
        dna = "cccATGgggTAAaataataatTAAxx";
        result = findGene(dna, 0);
        System.out.println("DNA string is: " + dna);
        System.out.println(result);
        
        dna = "cccATGgggTAAaataataatTAAxx";
        result = findGene(dna, 0);
        System.out.println("DNA string is: " + dna);
        System.out.println(result);
        
        dna = "cccNOA$T$G$HEREgggTAAaataataatTAAxx";
        result = findGene(dna, 0);
        System.out.println("DNA string is: " + dna);
        System.out.println(result);
        }
 public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
     }
public void testOn(String dna) {
        System.out.println("Testing printAllGenes on: " + dna);
        printAllGenes(dna);
    }
public void test() {
    //      ATGv   TAAv  ATGv v  v  TGA
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    //      ATGv  v  v  v  TAAv  v  v  ATGTAA
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}