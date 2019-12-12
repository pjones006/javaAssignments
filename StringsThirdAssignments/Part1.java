import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    // findStopCodon tested on 12/6/2019 by PSJ and works fine using 13 genes dna code from instructor assistant
    public int findStopCodon(String dnaStr,
                              int startIndex,
                              String stopCodon){
         
         int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
         while (currIndex != -1) {
             if((currIndex - startIndex) % 3 == 0) {    
                 return currIndex;
             } else {
                currIndex = dnaStr.indexOf(stopCodon , currIndex + 1);
             }
         }
         return currIndex;
    }
    
    public String findGene(String dna, int startIndex) {
       print("I am calling findstopcodon with this index: " + startIndex);
       int codonIndex = findStopCodon(dna, startIndex, "TAA");
       int tagIndex = findStopCodon(dna, startIndex, "TAG");
       int tgaIndex = findStopCodon(dna, startIndex, "TGA");    
       int minIndex = 0;
       int minStop = 9;
       int strandLength = dna.length();
       
       if (codonIndex == -1 || (tgaIndex != -1 && tgaIndex < codonIndex)) {
           minIndex = tgaIndex;
           } else {
             minIndex = codonIndex;
           }
       if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
           minIndex = tagIndex;           
                }
       //if (minIndex == -1 || (minIndex - startIndex) < minStop) {
       if (minIndex == -1) {
           return "";
           }
       if (startIndex > strandLength - 5){
            return "";
           } else {
            //startIndex = startIndex + 1;
            return dna.substring(startIndex, minIndex + 3);
            }
       }

    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            print("Sorry no genes!");
        }
        int counter = 0;
        while (startIndex != -1) {
            // find gene returns a gene string
            String currentGene = findGene(dna, startIndex);
            print("current gene: " + currentGene);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = startIndex + (currentGene.length() + 1);
            startIndex = dna.indexOf("ATG" , startIndex);
            print("Revised start index is: " + startIndex);
            //print("dna new is " + dna2);
            //startIndex = startIndex + (currentGene.length() + 1);
            counter++;
        }
        return geneList;
    }
    
    public float cgRatio(String dna) {
        dna = dna.toUpperCase();
        float cgCount = 0;
        int total = dna.length();
        char c = 'C';
        char g = 'G';
        int startIndex = 0;
        for(int i=0; i < dna.length(); i++){    
            if(dna.charAt(i) == c || dna.charAt(i) == g) {
                cgCount++;
            }
        }
        return (cgCount / total);
    }
        
    public int countCTG(String dna) {
        dna = dna.toUpperCase();
        int ctgCount = 0;
        int startIndex = dna.indexOf("CTG");
            if (startIndex == -1) {
                return ctgCount;
            }
        while(true) {
            // find first occurence of ctg, if -1 return 0 for ctgcount
            ctgCount = ctgCount + 1;
            startIndex = dna.indexOf("CTG", startIndex + 3);
            if (startIndex == -1) {
                break;
            }
        }
        return ctgCount;
    }
    
    public void processGenes(StorageResource sr) {
        int overSize = 0;
        int overPct = 0;
        int longestGeneLength = 0;
        int geneCount = 0;
        int over6Only = 0;
        for (String g: sr.data()) {
           geneCount++;
           if (g.length() > 60){
               overSize++;
               print("This gene is over 60 characters: " + g);
           }
           float ratio = cgRatio(g);
           if (ratio > .35) {
               overPct++;
               //print("This string's 'cg' ration is over 0.35 " + g);
            }
            if (g.length() > 6) {
                over6Only++; 
            }
           if (g.length() > longestGeneLength) {
               longestGeneLength = g.length();
           }
        }  
        print("Over 60 count is: " + overSize);
        print("Over .35 cgratio count is: " + overPct);
        print("The longest gene length is: " + longestGeneLength);
        print("The total gene count is: " + geneCount);
        print("The number of genes over a length of 6 is: " + over6Only);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
        
//         print("A GENE LONGER THEN 9");
//         String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGAATGATCTAATTTATGCTGCAACGGTGAAGA";
//         StorageResource genes = getAllGenes(dna);
//         processGenes(genes);
        
//         // no gene longer then 9
//         print("NO GENE LONGER THEN 9");
//         dna = "atgtaabbbcccatgbbbtga";
//         genes = getAllGenes(dna);
//         processGenes(genes);
        
//         // cg ratio greater then .35
//         print("CG RATIO OVER .35");
//         dna = "ATGCCCCCCTGA";
//         genes = getAllGenes(dna);
//         processGenes(genes);
        
//         // cg ratio lower then .35
//         print("CG LOWER THEN .35");
//         dna = "ATGTAA";
//         genes = getAllGenes(dna);
//         processGenes(genes);
        
             
        //for (String g: genes.data()) {
         //   print(g);
        //}
    }
    
    public void testcgRation() {
        String dna = "ATGCCATAG";
        float result = cgRatio(dna);
        print("Ratio of number 0f 'c' and 'g's to total characters is: " + result);
    }
    
    public void testcountCTG() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //String dna = "ctgabdctgctgabd";
        int result = countCTG(dna);
        print(dna + ":" + "The string 'ctg' occurs this many times: " + result);
       
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
     }
     
     public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
     }
     
     public void testOn(String dna) {
        System.out.println("Testing printAllGenes on: " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
        System.out.println("");
    }
    public void test() {
        //      ATGv   TAAv  ATGv v  v  TGA
        //testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        //      ATGv  v  v  v  TAAv  v  v  ATGTAA
        //testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testOn("oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAGC");
        }
    
    public void testJunk() {
        //            01234567890123456789  
        String dna = "axatgbcdbcdtagbcdbcd";
        int startIndex = dna.indexOf("atg");
        int result = findStopCodon(dna, startIndex, "tag");
        print (result);
    }
    public void testFindStopCodonPartTwo() {
        // This is the screen from instructor which has 13 genes in it
        String dna = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAGC";
        dna = "ATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAGC";
        dna = dna.toUpperCase();
        int startIndex = 0;
        startIndex = dna.indexOf("ATG", startIndex);
        
        //int result = findStopCodon(dna , startIndex, "TAA");
        //print("TAA result is: " + result);
        //result = findStopCodon(dna , startIndex, "TAG");
        //print("TAG result is: " + result);
        //result = findStopCodon(dna , startIndex, "TGA");
        //print("TGA result is: " + result);
        
        String result = findGene(dna, startIndex);
        print("The gene returned is: " + result);
    }
    
    public static final void print(Object x) { System.out.println(x); }
}