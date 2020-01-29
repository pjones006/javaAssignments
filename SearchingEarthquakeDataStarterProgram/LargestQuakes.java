import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public static final void print(Object x) { System.out.println(x); }
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largestIdx = 0;
        double largestMag = 0.0;
        for(int k = 0; k < data.size(); k++) {
            QuakeEntry quake = data.get(k);
            double currMag = quake.getMagnitude();
            if( currMag > largestMag ) {
                largestMag = currMag;
                largestIdx = k;
            }
        }
        return largestIdx;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        if(quakeData.size() < howMany) {
            return quakeData;
        }
        for(int k=0; k < howMany; k++) {
            int largest = indexOfLargest(quakeDataCopy);
            ret.add(quakeDataCopy.get(largest));
            quakeDataCopy.remove(largest);
        }
        return ret;
    }
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> result = getLargest(list, 60);
        
        for(int k=0; k < result.size(); k++){
            int rN = (k + 1);
            QuakeEntry entry = result.get(k);
            System.out.print(rN + " "); 
            System.out.println(k + " " + entry);
        }
        System.out.println("read data for "+list.size());
    }
   
}
