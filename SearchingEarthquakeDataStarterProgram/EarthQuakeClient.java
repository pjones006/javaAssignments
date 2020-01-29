import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public static final void print(Object x) { System.out.println(x); }
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
        double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
   
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
        double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
        double minDepth, double maxDepth) {
            ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
            System.out.println("Finding quakes between " + minDepth + " and " + maxDepth);
            for (QuakeEntry qe : quakeData) {
                if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                    answer.add(qe);
                }
            }
        
            return answer;
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
        String where, String phrase) {
            ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
            where = where.toLowerCase();
            print("Finding quakes that have " +  phrase + " at " + where + " position");
            
            for (QuakeEntry qe : quakeData) {
                if (where.equals("start")) {
                    if(qe.getInfo().startsWith(phrase)) {
                        answer.add(qe);                        
                    }
                } 
                if (where.equals("end")) {
                    if(qe.getInfo().endsWith(phrase)) {
                         answer.add(qe);  
                    }
                
                }
                if (where.equals("any")) {
                    if(qe.getInfo().contains(phrase)) {
                    answer.add(qe); 
                    }
                }
            }
            return answer;
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe  : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
         
        ArrayList<QuakeEntry> result = filterByMagnitude(list, 5.0);
        for (int k=0; k< result.size(); k++) {
             QuakeEntry entry = result.get(k);
             System.out.println("Record " + k + " is " + entry);
            }
        System.out.println("Number found: " + result.size() + " that match criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
         //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        System.out.println("size: " + close.size());
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
    }
     
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> result = filterByDepth(list, -10000.0, -8000.0);
        for (int k=0; k< result.size(); k++) {
             QuakeEntry entry = result.get(k);
             System.out.println("Record " + k + " is " + entry);
        }
        System.out.println("Number found: " + result.size() + " that match criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> result = filterByPhrase(list, "any", "Creek");
        for (int k=0; k< result.size(); k++) {
             QuakeEntry entry = result.get(k);
             print(entry);
        }
        System.out.println("Found " + result.size() + " quakes that match the given criterion shown above");
    }
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
