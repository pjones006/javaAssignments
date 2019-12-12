
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    // Use part2 which handle upper and lower case strings
    public String  findSimpleGene(String dna) {
        // Start codon is ATG
        // End codon is abc
        String result = "";
        int startIndex = dna.indexOf("atg");
        if (startIndex == -1) {
            return "";            // return empty string if not found
        }
        int  stopIndex = dna.indexOf("taa", startIndex+3);
        if (stopIndex == -1) {
            return "";            // return empty string if not found
        }
    
        result = dna.substring(startIndex , stopIndex+3);
        if (result.length() % 3 == 0) {
            return result;
        } else {
            return "Not valid gene!";
        }
    }
    public void testSimpleGene() {
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA strand is " + dna);
        String gene =  findSimpleGene (dna);
        System.out.println("Gene is " + gene + " length: " + gene.length());
        
        dna = "cccatggggtttatattaggagagagagagagagtttkjfdsafasgfaftaalk;gnffg";
        System.out.println("DNA strand is " + dna);
        gene =  findSimpleGene(dna);
        System.out.println("Gene is " + gene + " length: " + gene.length());
        
        dna = "cccatggggttabcaabcabctaaggagagagagagagagttt";
        System.out.println("DNA strand is " + dna);
        gene =  findSimpleGene(dna);
        System.out.println("Gene is " + gene + " length: " + gene.length());
        
        dna = "cccatggggttabcaabcabctaggagagagagagagagttt";
        System.out.println("DNA strand is " + dna);
        gene =  findSimpleGene(dna);
        System.out.println("Gene is " + gene + " length: " + gene.length());
    }
}

