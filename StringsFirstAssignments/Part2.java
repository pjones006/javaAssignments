
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String  findSimpleGene(String dna, String startCodon, String stopCodon) {
        // Start codon is ATG
        // End codon is abc
        // StringUtils.isAllUpperCase(" ") =
        String result = "";
        boolean isUpper = false;
        
        String upperDna = dna.toUpperCase();
        if (upperDna == dna) {
            isUpper = true;
        }
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
        
        int startIndex = upperDna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";            // return empty string if not found
        }
        int  stopIndex = upperDna.indexOf(stopCodon, startIndex+3);
        if (stopIndex == -1) {
            return "";            // return empty string if not found
        }
    
        result = upperDna.substring(startIndex , stopIndex+3);
        
        if (result.length() % 3 == 0) {
            if (isUpper == false) { 
                result = result.toLowerCase(); 
            }
            return result;
        } else {
            return "Not valid gene!";
        }
    }
    public void testSimpleGene() {
        String dna = "cccatggggttgtaaataataataggagagagagagagagttt";
        System.out.println("DNA strand is " + dna);
        String gene =  findSimpleGene (dna, "ATG", "TAA");
        System.out.println("Gene is " + gene + " length: " + gene.length());
        
        dna = "CCDSDATGCCCHHHDDDTAALLLKDKLSLC";
        System.out.println("DNA strand is " + dna);
        gene =  findSimpleGene (dna, "ATG", "TAA");
        System.out.println("Gene is " + gene + " length: " + gene.length());
        
        dna = "cccatggggttabcaabcabctaaggagagagagagagagttt";
        System.out.println("DNA strand is " + dna);
        gene =  findSimpleGene (dna, "ATG", "TAA");
        System.out.println("Gene is " + gene + " length: " + gene.length());
        
        dna = "cccatggggttabcaabcabctaggagagagagagagagttt";
        System.out.println("DNA strand is " + dna);
        gene =  findSimpleGene (dna, "ATG", "TAA");
        System.out.println("Gene is " + gene + " length: " + gene.length());
    }
}

