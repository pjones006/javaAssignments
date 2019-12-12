
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
public int findStopCodon(String dnaStr,
                              int startIndex,
                              String stopCodon){
         System.out.println("Function: findStopCodon");
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
        System.out.println("Function: findGene");
        dna = dna.toUpperCase();
        int dnaIndexLength = dna.length();
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
       if (minIndex == dnaIndexLength) {
           return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
}

public void testFindStopCodon() {
    System.out.println("Function: testFindStopCodon");
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
    
public void testFindGene() {
        System.out.println("Function: testFindGene");
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
        System.out.println("Function: printAllGenes");
        int startIndex = 0;
        int countGenes = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            countGenes = countGenes + 1;
        }
        }
     
public int countGenes(String dna) {
        System.out.println("Function: countGenes");
        int startIndex = 0;
        int countGenes = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            countGenes = countGenes + 1;
            //System.out.println("Counting: " + countGenes + " Gene: " + currentGene);
        }
        return countGenes;
        }   
 
public void testCountGenes() {
       String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGAATGCCCCCCTAANNNATGHHHHHHDDDTAG";
       System.out.println("Testing printAllGenes on: " + dna);
       int cnt = countGenes(dna);
       System.out.println("Count is " + cnt);
       
       dna = "ATGJDJHDHGRTTGALJKFDSFA";
       System.out.println("Testing printAllGenes on: " + dna);
       cnt = countGenes(dna);
       System.out.println("Count is " + cnt);
       
       // This should return nothing, no stop codon is in dna string
       dna = "ATGKVAJFHGJFDKJBVDFDJF";
       System.out.println("Testing printAllGenes on: " + dna);
       cnt = countGenes(dna);
       System.out.println("Count is " + cnt);
       
       dna = "JSADSDAHGDATGHFHKFKTAAHFHATGJIODEWNSRTGAATGTAA";
       System.out.println("Testing printAllGenes on: " + dna);
       cnt = countGenes(dna);
       System.out.println("Count is " + cnt);
       }    
    
public void testOn(String dna) {
        System.out.println("Function: testOn");
        System.out.println("Testing printAllGenes on: " + dna);
        printAllGenes(dna);
        }

public void test() {
    System.out.println("Function: test");
    //      ATGv   TAAv  ATGv v  v  TGA
    testOn("AATGCTAACTAGCTGACTAAT");
    testOn("");
    //      ATGv  v  v  v  TAAv  v  v  ATGTAA
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }

public void quizCode() {
    System.out.println("Function: quizCode");
    String dna = "CTGCCTGCATGATCGTA";
    int pos = dna.indexOf("TG");
    int count = 0;
    while (pos >= 0) {
        count = count + 1;
        pos = dna.indexOf("TG",pos);
    }
    System.out.println(count);
    }
}
