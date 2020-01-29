import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public static final void print(Object x) { System.out.println(x); }
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        // Filter depthF1 = new DepthFilter(-12000.0, -10000.0);
        // ArrayList<QuakeEntry> quakesOfDepth1 = filter(list, depthF1);
        // Filter depthF2 = new DepthFilter(-4000.0, -2000.0);
        // ArrayList<QuakeEntry> quakesOfDepth2 = filter(list, depthF2);
        
        // Filter phraseFilter1 = new PhraseFilter("start", "Quarry Blast");
        // ArrayList<QuakeEntry> quakesByPhrase1 = filter(list, phraseFilter1);
        // Filter phraseFilter2 = new PhraseFilter("end", "Alaska");
        // ArrayList<QuakeEntry> quakesByPhrase2 = filter(list, phraseFilter2);
        // Filter phraseFilter3 = new PhraseFilter("any", "Can");
        // ArrayList<QuakeEntry> quakesByPhrase3 = filter(list, phraseFilter3);
        // print("QuakesOfDepth1 " + quakesOfDepth1.size());
        // print("QuakesOfDepth2 " + quakesOfDepth2.size());
        // print("quakesByPhrase1 " + quakesByPhrase1.size());
        // print("quakesByPhrase2 " + quakesByPhrase2.size());
        // print("quakesByPhrase3 " + quakesByPhrase3.size());        
        
        Filter magF = new MagnitudeFilter(3.5, 4.5);
        Filter depthF1 = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> magFiltered = filter(list, magF);
        ArrayList<QuakeEntry> magDepthFiltered = filter(magFiltered, depthF1);
        for (QuakeEntry qe: magDepthFiltered) {
            System.out.println(qe);
        }
        print("total quakes for magDepthFiltered is: " + magDepthFiltered.size());
        
        // Location denver = new Location(39.7392, -104.9903);
        // Filter distF = new DistanceFilter(denver, 1000000);
        // ArrayList<QuakeEntry> distFiltered = filter(list, distF);
        // Filter phraseFilter = new PhraseFilter("end", "a");
        // ArrayList<QuakeEntry> distPhraseFiltered = filter(distFiltered, phraseFilter);
        
        // for (QuakeEntry qe: distPhraseFiltered) {
            // System.out.println(qe);
        // }
        // print("Number that end with a " + distPhraseFiltered.size());
        
           
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+ list.size()+" quakes");
        
       MatchAllFilter maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(1.0, 4.0));
       maf.addFilter(new DepthFilter(-180000.0 ,  -30000.0));
       maf.addFilter(new PhraseFilter("any", "o"));
       ArrayList<QuakeEntry> quakes = filter(list, maf);
       
       for (QuakeEntry qe: quakes) {
            System.out.println(qe);
        }
       System.out.println("Total quakes are: " + quakes.size());
       System.out.println("Filters used are: " + maf.getName() );
    }
    public void testMatchAllFilter2() {
       EarthQuakeParser parser = new EarthQuakeParser(); 
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+ list.size()+" quakes");
        
       Location denmark = new Location(55.7308, 9.1153);
       
       MatchAllFilter maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(0.0, 5.0));
       maf.addFilter(new DistanceFilter(denmark,3000000));
       maf.addFilter(new PhraseFilter("any", "e"));
       ArrayList<QuakeEntry> quakes = filter(list, maf);
       for(QuakeEntry qe : quakes) {
            System.out.println(qe);
        }
       System.out.println("Total quakes are: " + quakes.size());
    }

}
