import java.util.*;
/*
 * getSmallestMagnitude - Return index of smallest magnitude quake in array.
 *      @param  QuakeEntry array
 *      @param  Index to start searching from
 * getLargestDepth - Return index of quake with largest depth
 *      @param  QuakeEntry array
 *      @param  Index to start searching from
 * sortByMagnitude - Void method to sort a QuakeEntry array by its magnitude, smallest to largest
 * sortByLargestDepth - Void method to sort a QuakeEntry array by its depth, from largest to smallest
 * onePassBubbleSort - Do a bubble sort on array, puts largest EQ at end of array
 *      @param  QuakeEntry array
 *      @param  numSorted is how many times this has been run. Should run array.size() - 1
 * sortByMagnitudeWithBubbleSort - Used with onePassBubbleSort. Each time run, increments numSorted.
 *      @param  QuakeEntry array
 * checkInSortedOrder - Return boolean if the array passed is already in sorted order. 
 *      @param  QuakeEntry array
 * sortByMagnitudeWithBubbleSortWithCheck - Same as sortByMagnitudeWithBubbleSort but runs checkInSortedOrder each iteration.
 *                                          Prints how many passes needed to sort.
 * sortByMagnitudeWithCheck - Same as sortByMagnitude but runs checkInSortedOrder each iteration.
 *                            Prints how many passes needed to sort.
 * testSort - Void method to test sortByMagnitude
 */
public class QuakeSortInPlace {
    public static final void print(Object x) { System.out.println(x); }
    
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int  getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i = from +1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        int size = quakeData.size();
        for (int i = 0; i < size-numSorted-1; i++) {
            QuakeEntry q1 = quakeData.get(i);
            QuakeEntry q2 = quakeData.get(i + 1);
            if (q1.getMagnitude() > q2.getMagnitude()) {
                quakeData.set(i, q2);
                quakeData.set(i + 1, q1);
            } 
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        int size = quakes.size();
        for (int i = 0; i < size-1; i++) {
            QuakeEntry q1 = quakes.get(i);
            QuakeEntry q2 = quakes.get(i + 1);
            if (q1.getMagnitude() >  q2.getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        for(int i = 0; i < in.size(); i++) {
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for(int i = 0; i < in.size(); i++) {
            boolean sorted = checkInSortedOrder(in);
            if (sorted) {
                print("There were this many passes needed to sort: " + passes);
                break;
            }
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            passes++;
        }
    }
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
         // for(int i = 0; i < in.size(); i++) {
         for(int i = 0; i < 70; i++) {
             /* find the index of the largest depth quake*/
             int maxIdx = getLargestDepth(in, i);
             /* swap the ith quake with the lIdxth quake */
             QuakeEntry qi = in.get(i);
             QuakeEntry qmax = in.get(maxIdx);
             in.set(i, qmax);
             in.set(maxIdx, qi);
         }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int size = in.size();
        for(int i = 0; i < size - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int size = in.size();
        int passes = 0;
        for(int i = 0; i < size - 1; i++) {
            boolean sorted = checkInSortedOrder(in);
            if (sorted) {
                print("There were this many passes needed to sort: " + passes);
                break;
            }
            onePassBubbleSort(in, i);
            passes++;
        }
    }
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedatasmall.atom";
        String source =  "data/earthQuakeDataWeekDec6sample2.atom";
        String source2 = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> list2  = parser.read(source2);
        print("Number of quakes in data source: " + list.size());
        
        // Test sorting by magnitude - small to large
        // sortByMagnitude(list);
        // for(QuakeEntry qe: list) {
            // System.out.println(qe);
        // }
        // print("The print should be magnitude smallest to largest " + list.size());
        
        // Test sorting by magnitude with check - small to large
        // sortByMagnitudeWithCheck(list2);
        // for(QuakeEntry qe: list) {
            // System.out.println(qe);
        // }
        // print("The print should be magnitude smallest to largest " + list.size());
        // // Test sorting by depth - large to small
        // sortByLargestDepth(list);
        // for(QuakeEntry qe: list) {
            // System.out.println(qe);
        // }
        // sortByMagnitudeWithBubbleSort(list2);
        // sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        print("Earthquakes in sorted order, small to large. # of quakes:" + list.size());
        // for(QuakeEntry qe: list) {
            // System.out.println(qe);
        // }
        
    }
    
}
